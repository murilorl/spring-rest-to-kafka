package com.mrl.model.jsonplaceholder;

import lombok.Data;

@Data
public class User {

    private long id;
    private String name;
    private String username;
    private String email;
    private Address address;
}
