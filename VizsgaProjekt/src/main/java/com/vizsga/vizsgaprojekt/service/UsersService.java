/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vizsga.vizsgaprojekt.service;

import com.vizsga.vizsgaprojekt.config.JWT;
import com.vizsga.vizsgaprojekt.modell.Users;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/**
 *
 * @author asd
 */
public class UsersService {
    private Users layer = new Users();
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    
    public static boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean isValidPassword(String password) {
        // Ellenőrzi a jelszó hosszát
        if (password.length() < 8) {
            return false;
        }

        // Ellenőrzi, hogy van-e benne kisbetű
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Ellenőrzi, hogy van-e benne nagybetű
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Ellenőrzi, hogy van-e benne speciális karakter
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            return false;
        }

        // Ha minden feltétel teljesült
        return true;
    }
    
    public JSONObject login(String email, String password){
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        if(isValidEmail(email)){
            Users modelResult = layer.login(email, password);
            if(modelResult == null){
                status = "modelException";
                statusCode = 500;
            }else{
                if(modelResult.getId() == null){
                    status = "userNotFound";
                    statusCode = 417;
                }else{
                    JSONObject result = new JSONObject();
                    result.put("id", modelResult.getId());
                    result.put("email", modelResult.getEmail());
                    result.put("firstName", modelResult.getFirstName());
                    result.put("lastName", modelResult.getLastName());
                    result.put("isAdmin", modelResult.getIsAdmin());
                    result.put("isDelete", modelResult.getIsDeleted());
                    result.put("jwt", JWT.createJWT(modelResult));
                    
                    toReturn.put("result", result);
                }
            }
        }else{
            status = "invalidEmail";
            statusCode = 417;
        }
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
}
