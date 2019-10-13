package com.google.appinventor.web.controllers.project;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NewYoungAndroidProjectParameters.class, name = "com.google.appinventor.shared.rpc.project.youngandroid.NewYoungAndroidProjectParameters")
})
public interface NewProjectParameters {
}
