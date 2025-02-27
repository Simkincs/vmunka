/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vizsga.vizsgaprojekt.service;

import com.vizsga.vizsgaprojekt.config.JWT;
import com.vizsga.vizsgaprojekt.modell.Tests;
import org.json.JSONObject;

/**
 *
 * @author asd
 */
public class TestService {
    private Tests layer = new Tests();
    
    public JSONObject addTest(Tests t, String jwt){
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
            if(t.getTitle().isEmpty()){//Ha a teszt címét üresen hagyák
                status = "TestTitleEmpty";
                statusCode = 417;
            }else{
                if(t.getQuestin().isEmpty()){//Ha a teszt tartalma üres
                    status = "TestQuestinEmpty";
                    statusCode = 417;
                }else{
                    boolean addSuccess = layer.addTest(t);//Sikeres teszt hozzáadás
                    if(!addSuccess){//Ha a tesztet nem sikerült hozzá adni
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
