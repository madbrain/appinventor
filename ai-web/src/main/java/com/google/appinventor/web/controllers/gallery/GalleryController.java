package com.google.appinventor.web.controllers.gallery;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

    @RequestMapping(method = RequestMethod.GET, value = "/loadGallerySettings")
    public GallerySettings loadGallerySettings() {
        return new GallerySettings();
    }

}
