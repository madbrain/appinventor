package com.google.appinventor.web.controllers.admin;

import com.google.appinventor.web.model.User;
import com.google.appinventor.web.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/adminInfo")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/storeUser")
    public void storeUser(@RequestBody NewUser newUser) {
        User user = new User();
        user.setUsername(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setAdmin(newUser.getIsAdmin());
        userRepository.save(user);
    }

    @PostMapping("/searchUsers")
    public List<NewUser> searchUsers(@RequestBody(required = false) String query) {
        if (StringUtils.isEmpty(query)) {
            return userRepository.findAll().stream()
                    .map(this::toUser)
                    .collect(Collectors.toList());
        } else {
            return userRepository.findByUsername(query).stream()
                    .map(this::toUser)
                    .collect(Collectors.toList());
        }
    }

    private NewUser toUser(User user) {
        return new NewUser(user.getUsername(), user.isAdmin());
    }
}
