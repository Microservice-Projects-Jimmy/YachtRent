package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.UserEntity;

import java.util.List;

@Setter
@Getter
public class User {


    private String fullName;
    private String username;
    private String token;
    private List<String> roles;

    public static User toModel(UserEntity userEntity) {
        var user = new User();
        user.setFullName(userEntity.getFirstName() + " " + userEntity.getLastName());
        user.setUsername(userEntity.getUsername());
        user.setToken(userEntity.getId()+"_"+userEntity.getToken());

        return user;
    }
}
