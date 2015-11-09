package com.example.mortuza.testapi;

/**
 * Created by Mortuza on 11/9/2015.
 */
public class UserModel {
    String id,name,phone,email;

    public UserModel(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
