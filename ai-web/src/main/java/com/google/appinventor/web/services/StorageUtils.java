package com.google.appinventor.web.services;

public class StorageUtils {

    public static String basename(String path) {
        if (path.length() == 0) {
            return path;
        }
        int pos = path.lastIndexOf("/");
        if (pos == -1) {
            return path;
        } else {
            return path.substring(pos + 1);
        }
    }

}
