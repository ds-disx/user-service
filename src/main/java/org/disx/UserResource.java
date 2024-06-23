package org.disx;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.Set;

@Path("/users")
public class UserResource {

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Path("/isAdmin")
    @Authenticated
    public Response isAdminAuthorized(){
        Set<String> s = securityIdentity.getRoles();
        if (s.contains("admin")){

            return Response.ok(s).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
