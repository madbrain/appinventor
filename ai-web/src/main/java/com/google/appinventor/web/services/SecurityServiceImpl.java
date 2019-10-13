package com.google.appinventor.web.services;

import com.google.appinventor.web.model.UserProjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class SecurityServiceImpl implements SecurityService {

    private static final int ID_DIGITS = Long.SIZE / 4;

    private String urlEncryptionkey;

    private SecretKeySpec secretKey;

    public SecurityServiceImpl(@Value("${url.encryption.key}") String urlEncryptionkey) {
        this.urlEncryptionkey = urlEncryptionkey;
    }

    @PostConstruct
    public void iniKey() {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = sha.digest(urlEncryptionkey.getBytes("UTF-8"));
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new EncryptionException(e);
        }
    }

    @Override
    public UserProjectId decryptIds(String idEnc) {
        try {
            String decryptedString = decrypt(idEnc);
            return new UserProjectId(
                    Long.parseLong(decryptedString.substring(0, ID_DIGITS), 16),
                    decryptedString.substring(ID_DIGITS));
        } catch (NumberFormatException e) {
            throw new EncryptionException(e);
        }
    }

    @Override
    public String encryptUserAndProjectId(String userId, long projectId) throws EncryptionException {
        if ((userId == null) || (userId.isEmpty())) {
            throw new EncryptionException("Trying to encrypt a null userId");
        }
        String plain = String.format("%1$0" + ID_DIGITS + "x", projectId) + userId;
        return encrypt(plain.getBytes());
    }

    private String encrypt(byte[] content) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getUrlEncoder().encodeToString(cipher.doFinal(content));
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException e) {
            throw new EncryptionException(e);
        }
    }

    private String decrypt(String strToDecrypt) {
        try {
            Cipher cipher =  Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getUrlDecoder().decode(strToDecrypt)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException e) {
            throw new EncryptionException(e);
        }
    }
}
