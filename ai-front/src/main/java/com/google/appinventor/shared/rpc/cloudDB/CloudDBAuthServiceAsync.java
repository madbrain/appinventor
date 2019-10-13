package com.google.appinventor.shared.rpc.cloudDB;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Interface for the service providing user related information. All
 * declarations in this interface are mirrored in {@link CloudDBAuthServiceAsync}.
 * For further information see {@link CloudDBAuthServiceAsync}.
 *
 * @author joymitro1989@gmail.com (Joydeep Mitra)
 */
@Path("/cloudDBAuth")
public interface CloudDBAuthServiceAsync extends RestService {

    @GET
    @Path("/token")
    void getToken(MethodCallback<String> callback);
}
