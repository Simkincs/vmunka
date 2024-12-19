/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.vizsga.vizsgaprojekt.controller;

import com.vizsga.vizsgaprojekt.service.UsersService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author asd
 */
@Path("users")
public class UsersController {
    private UsersService layer = new UsersService();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsersController
     */
    public UsersController() {
    }

    /**
     * Retrieves representation of an instance of com.vizsga.vizsgaprojekt.controller.UsersController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UsersController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String bodyString){
        JSONObject body = new JSONObject(bodyString);
        
        JSONObject obj = layer.login(body.getString("email"), body.getString("password"));
        
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
