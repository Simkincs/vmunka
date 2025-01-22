/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vizsga.vizsgaprojekt.service;

import com.vizsga.vizsgaprojekt.config.JWT;
import com.vizsga.vizsgaprojekt.modell.Users;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
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
        if (password.length() < 8) {
            return false;
        }

        boolean hasNumber = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;
        
        for(char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if ("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[\\S]).{8,}$".indexOf(c) != -1) {
                hasSpecialChar = true;
            }
        }

        return hasNumber && hasUpperCase && hasLowerCase && hasSpecialChar;
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
    
    /*
    public JSONObject registerAdmin(Users u, String jwt){
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        if (JWT.isAdmin(jwt)) {
            if(isValidEmail(u.getEmail()))
             {if (isValidPassword(u.getPassword()))
                {
                    boolean userIsExists = Users.isUserExists(u.getEmail());
                    
                    if (Users.isUserExists(u.getEmail()) == null) {
                        status = "ModelExeption";
                        statusCode = 500;
                    }else if(userIsExists == true){
                        status = "UserAlreadyExists";
                        statusCode = 417;
                    }else{
                        boolean registerAdmin = layer.registerAdmin(u);
                        if(registerAdmin == false){
                            status = "fail";
                            statusCode = 417;
                        }
                    }
                }else{
                   status = "InvalidPassword";
                   statusCode = 417; 
                }
            }else{
                status = "InvalidEmail";
                statusCode = 417;
            }
        }else{
            status = "PermissonError";
            statusCode = 417;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }

*/
    
    public JSONObject registerUser(Users u){
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        
        
        
        //szükséges validációk
        //Email cím van-e a db-ben
        //Valid-e az email cím
        //Valid-e a jelszó
        
        if(isValidEmail(u.getEmail())){
            if(isValidPassword(u.getPassword())){
               
                boolean userIsExists = Users.isUserExists(u.getEmail());
               
               if(Users.isUserExists(u.getEmail()) == null){
                   status = "ModelExeption";
                   statusCode = 500;
               }else if(userIsExists == true){
                   status = "UseAlredyExists";
                   statusCode = 417;
               }else{
                   boolean registerUser = layer.registerUser(u);
                   if(registerUser == false){
                       status = "fail";
                       statusCode = 417;
                   }
               }
            }else{
                status = "InvalidPassword";
                statusCode = 417;
            }
        }else{
            status = "IvalidEmail";
            statusCode = 417;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject getAllUser(){
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        List<Users> moselResult = layer.getAllUser();
        
        if(moselResult == null){
            status = "ModelException";
            statusCode = 500;
        }else if(moselResult.isEmpty()){
            status = "NoUserFound";
            statusCode = 417;
        }else{
            JSONArray result = new JSONArray();
            
            for(Users actualUser : moselResult){
                JSONObject toAdd = new JSONObject();
                
                toAdd.put("id", actualUser.getId());
                toAdd.put("email", actualUser.getEmail());
                toAdd.put("firstName", actualUser.getFirstName());
                toAdd.put("lastName", actualUser.getLastName());
                toAdd.put("isAdmin", actualUser.getIsAdmin());
                toAdd.put("isDeleted", actualUser.getIsDeleted());
                toAdd.put("createdAt", actualUser.getCreatedAt());
                toAdd.put("deletedAt", actualUser.getDeletedAt());
                
                result.put(toAdd);
            }
            
            toReturn.put("result", result);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject changePassword(Integer userId, String newPassword, Integer creator){
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        if(userId == creator){
            Boolean modelResult = layer.changePassword(userId, newPassword, creator);
            if(!modelResult){
                status = "ModelException";
                statusCode = 500;
            }
        }else{
            status = "PermissionError";
            statusCode = 417;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
}
