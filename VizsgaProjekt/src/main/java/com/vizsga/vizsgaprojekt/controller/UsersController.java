/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.vizsga.vizsgaprojekt.controller;

import com.vizsga.vizsgaprojekt.config.JWT;
import com.vizsga.vizsgaprojekt.modell.Users;
import com.vizsga.vizsgaprojekt.service.UsersService;
import javax.persistence.Query;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
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
    
    /*
    @POST
    @Path("registerAdmin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerAdmin(@HeaderParam("token") String jwt, String bodyString){
        JSONObject body = new JSONObject(bodyString);
        
        Users u = new Users(
                body.getString("email"),
                body.getString("firtName"),
                body.getString("lastName"),
                body.getString("password")
        );
        
        JSONObject obj = layer.registerAdmin(u, jwt);
        
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    } 
    */

    @POST
    @Path("registerUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(String bodyString){
        JSONObject body = new JSONObject(bodyString);
        
        
        
        
        
        Users u = new Users(
                body.getString("email"),
                body.getString("firstName"),
                body.getString("lastName"),
                body.getString("password")
        );
        
        JSONObject obj = layer.registerUser(u);
        
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAllUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllUser(@HeaderParam("token") String jwt){
        int isValid = JWT.validateJWT(jwt);
        
        if(isValid == 1){
            JSONObject obj = layer.getAllUser();
            return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }else if(isValid == 2){
            return Response.status(400).entity("InvalidToken").type(MediaType.APPLICATION_JSON).build();
        }else{
            return Response.status(400).entity("TokenExpireds").type(MediaType.APPLICATION_JSON).build();
        }
        
    }
    
    @PUT
    @Path("changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changePassword(@HeaderParam("token") String jwt, @QueryParam("userId") Integer userId, String bodyString){
        int isValid = JWT.validateJWT(jwt);
        
        if(isValid == 1){
            JSONObject obj = layer.getAllUser();
            return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }else if(isValid == 2){
            return Response.status(400).entity("InvalidToken").type(MediaType.APPLICATION_JSON).build();
        }else{
            return Response.status(400).entity("TokenExpireds").type(MediaType.APPLICATION_JSON).build();
        }
        
    }
    
}
