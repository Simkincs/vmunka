/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vizsga.vizsgaprojekt.service;

import com.vizsga.vizsgaprojekt.modell.Courses;
import com.vizsga.vizsgaprojekt.modell.Users;
import org.json.JSONObject;

/**
 *
 * @author asd
 */
public class CoursesService {
    private Courses layer = new Courses();

    public JSONObject addCourses(Courses c, Users u){
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        Users currentUser = Users.getUserById();
        
        // Csak admin felhasználók adhatnak hozzá kurzust
        if(currentUser == null){
            status = "UserNotFound";
            statusCode = 404;
        }else{
            if(!currentUser.getIsAdmin()){
                status = "Unauthorized";
                statusCode = 403;
            }else{
                if(c.getName().isEmpty()){
                    status = "CoursesNameEmpty";
                    statusCode = 417;
                }else{
                    if(c.getDescription().isEmpty()){
                        status = "CoursesDescriptionEmpty";
                        statusCode = 417;
                    }else{
                        boolean addSuccess = layer.addCourses(c);
                        if(!addSuccess){
                            status = "fail";
                            statusCode = 417;
                        }
                    }
                }
            }
        }
        
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
}
