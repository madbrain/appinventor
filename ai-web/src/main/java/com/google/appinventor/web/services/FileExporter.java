package com.google.appinventor.web.services;

public interface FileExporter {
    RawFile exportProjectToZip(String currentUser, Long projectId, boolean includeYail);

    RawFile exportProjectOutputFile(String currentUser, Long projectId, String target);
}
