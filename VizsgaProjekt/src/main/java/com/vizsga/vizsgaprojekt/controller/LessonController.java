/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.vizsga.vizsgaprojekt.controller;

import com.vizsga.vizsgaprojekt.modell.Lessons;
import com.vizsga.vizsgaprojekt.service.LessonsService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
@Path("lesson")
public class LessonController {
    private LessonsService layer = new LessonsService();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LessonController
     */
    public LessonController() {
    }

    /**
     * Retrieves representation of an instance of com.vizsga.vizsgaprojekt.controller.LessonController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of LessonController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @POST
    @Path("addLesson")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLesson(@HeaderParam("token") String jwt, String bodyString){
        JSONObject body = new JSONObject(bodyString);
        
        Lessons l = new Lessons(
                body.getInt("coursesId"),
                body.getString("title"),
                body.getString("description")
                
        );
        
        JSONObject obj = layer.addLesson(l, jwt);
        
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
}
