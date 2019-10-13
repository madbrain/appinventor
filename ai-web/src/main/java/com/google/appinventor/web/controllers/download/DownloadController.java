package com.google.appinventor.web.controllers.download;

import com.google.appinventor.web.services.FileExporter;
import com.google.appinventor.web.services.RawFile;
import com.google.appinventor.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/download")
public class DownloadController {

    @Autowired
    private FileExporter fileExporter;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/project-source/{projectId}")
    public ResponseEntity<byte[]> downloadSource(@PathVariable("projectId") Long projectId) {
        RawFile rawFile = fileExporter.exportProjectToZip(userService.getCurrentUser(), projectId, false);
        return makeResponse(rawFile);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/project-output/{projectId}/{target}")
    public ResponseEntity<byte[]> downloadOutput(@PathVariable("projectId") Long projectId,
                                                 @PathVariable("target") String target) {
        RawFile rawFile = fileExporter.exportProjectOutputFile(userService.getCurrentUser(), projectId, target);
        return makeResponse(rawFile);
    }

    private ResponseEntity<byte[]> makeResponse(RawFile rawFile) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(getContentTypeForFilePath(rawFile.getFilename()));
        headers.setContentLength(rawFile.getContent().length);
        headers.setContentDisposition(ContentDisposition.builder("attachement")
                .filename(rawFile.getFilename())
                .build());

        return new ResponseEntity<byte[]>(rawFile.getContent(), headers, HttpStatus.OK);

    }

    private MediaType getContentTypeForFilePath(String filename) {
        if (filename.endsWith(".aia")) {
            return MediaType.valueOf("application/zip");
        }
        return MediaType.valueOf("text/plain; charset=utf-8");
    }
}
