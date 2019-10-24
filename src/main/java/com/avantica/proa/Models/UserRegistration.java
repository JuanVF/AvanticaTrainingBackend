package com.avantica.proa.Models;

import java.util.ArrayList;
import java.util.List;

public class UserRegistration {
    private List<User> userRecords;

    private static UserRegistration userReg = null;

    private UserRegistration(){
        userRecords = new ArrayList<User>();
    }

    public static UserRegistration getInstance(){
        if(userReg == null){
            userReg = new UserRegistration();
            return userReg;
        }
        return userReg;
    }

    public void add(User user){
        userRecords.add(user);
    }

    public String updateUser(User user){
        for(int i = 0; i < userRecords.size(); i++){
            User userN = userRecords.get(i);
            if(userN.getEmail().equals(user.getEmail())){
                userRecords.set(i,user);
                return "Succesful update";
            }
        }

        return "Unsucessful update";
    }

    public String deleteUser(String email){
        for(int i = 0; i < userRecords.size();i++){
            User userN = userRecords.get(i);

            if(userN.getEmail().equals(email)){
                userRecords.remove(i);
                return "Succesful detele";
            }
        }
        return "Unsuccesful delete";
    }

    public List<User> getUserRecords(){
        return userRecords;
    }
}
