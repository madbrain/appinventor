// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.shared.rpc.project;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.appinventor.shared.rpc.project.youngandroid.NewYoungAndroidProjectParameters;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Interface for classes that define additional parameters to the
 * {@link ProjectServiceAsync#newProject(String, String, NewProjectParameters)}
 * RPC.
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(NewYoungAndroidProjectParameters.class)
})
public interface NewProjectParameters extends IsSerializable {
}
