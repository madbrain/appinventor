package com.google.appinventor.web.controllers.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/userService")
public class UserInfoController {

    @Value("${rendezvousserver.url}")
    private String rendezvousServer;

    @RequestMapping(method = RequestMethod.GET, value = "/systemConfig/{sessionId}")
    public Config getSystemConfig(@PathParam("sessionId") String sessionId) {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean isAdmin = principal.getAuthorities().stream()
                .anyMatch(u -> u.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));
        User user = new User(principal.getUsername(), principal.getUsername(), principal.getUsername(), "",
                User.DEFAULT_EMAIL_NOTIFICATION_FREQUENCY,
                true, isAdmin, User.USER, sessionId);
        user.setReadOnly(false);

        Config config = new Config();
        config.setUser(user);
        config.setRendezvousServer(rendezvousServer);
        config.setSplashConfig(new SplashConfig(0, 200, 200, ""));

        return config;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/userBackpack")
    public String getUserBackpack() {
        return "{}"; // JSON
    }

    @RequestMapping(method = RequestMethod.POST, value = "/userBackpack")
    public void storeUserBackpack(String backpack) {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/userById/{userId}")
    public User getUserInformationByUserId(@PathParam("userId") String userId) {
        throw new RuntimeException("getUserInformationByUserId");
    }

    /**
     * Voir la classe {see UserSettings}
     * @return les settings sous la forme du chaine JSON
     */
    @RequestMapping(method = RequestMethod.GET, value = "/userSettings")
    public String loadUserSettings() {
        return "\"{}\""; // JSON
    }

    @RequestMapping(method = RequestMethod.POST, value = "/userSettings")
    public void storeUserSettings(String settings) {

    }

    @RequestMapping(method = RequestMethod.POST, value = "/userName")
    public void storeUserName(String name) {

    }

    @RequestMapping(method = RequestMethod.POST, value = "/userLink")
    public void storeUserLink(String link) {

    }

    @RequestMapping(method = RequestMethod.POST, value = "/userEmailFrequency")
    public void storeUserEmailFrequency(Integer emailFrequency) {

    }

    @RequestMapping(method = RequestMethod.POST, value = "/userFile")
    public boolean hasUserFile(String fileName) {
        return false;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/userFile")
    public void deleteUserFile(String fileName) {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/noop")
    public void noop() {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/sharedBackpack/{backPackId}")
    public String getSharedBackpack(@PathParam("backPackId") String backPackId) {
        return "<sharedBackpack>";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sharedBackpack/{backPackId}")
    public void storeSharedBackpack(@PathParam("backPackId") String backPackId, String content) {

    }

}
