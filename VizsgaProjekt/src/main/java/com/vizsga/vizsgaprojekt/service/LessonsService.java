/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vizsga.vizsgaprojekt.service;

import com.vizsga.vizsgaprojekt.config.JWT;
import com.vizsga.vizsgaprojekt.modell.Lessons;
import org.json.JSONObject;

/**
 *
 * @author asd
 */
public class LessonsService {
    private Lessons layer = new Lessons();
    
    public JSONObject addLesson (Lessons l, String jwt){
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        if(JWT.validateJWT(jwt) != 1){ //Megvizsgálom, hogy érvényes a token
            status = "IvalidToken";
            statusCode = 404;
        }else if(!JWT.isAdmin(jwt)){ // Csak admin felhasználók adhatnak hozzá kurzust
            status = "Unauthorized";
            statusCode = 403;
        }else{
            if(l.getTitles().isEmpty()){//Ha a lecke címét üresen hagyák
                status = "LessonTitleEmpty";
                statusCode = 417;
            }else{
                if(l.getContens().isEmpty()){//Ha a lecke tartalma üres
                    status = "LessonContentEmpty";
                    statusCode = 417;
                }else{
                    boolean addSuccess = layer.addLesson(l);//Sikeres lecke hozzáadás
                    if(!addSuccess){//Ha a leckét nem sikerült hozzá adni
                        status = "fail";
                        statusCode = 417;
                    }
                }
            }
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
}
