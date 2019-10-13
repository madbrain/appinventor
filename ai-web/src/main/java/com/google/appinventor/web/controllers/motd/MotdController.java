package com.google.appinventor.web.controllers.motd;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/motd")
public class MotdController {

    @RequestMapping(method = RequestMethod.GET, value = "/interval")
    public int getInterval() {
        return 0;
    }
}
