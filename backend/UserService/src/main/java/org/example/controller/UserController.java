package org.example.controller;

import org.example.entity.UserEntity;
import org.example.model.User;
import org.example.request.AdminRegistrationRequest;
import org.example.request.LoginRequest;
import org.example.request.RegisterRequest;
import org.example.request.UserRoleRequest;
import org.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("users/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {

        var userEntity = userService.register(registerRequest);
        userService.addRoleToUser(userEntity.getId(), 2L);

        return ResponseEntity.ok(User.toModel(userEntity));
    }

    @PostMapping("users/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {

        var userEntity = userService.authenticate(loginRequest);
        return ResponseEntity.ok(User.toModel(userEntity));

    }

    @GetMapping("get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userService.getAll();

        return ResponseEntity.ok(users.stream().map(User::toModel).toList());
    }

    @PostMapping("users/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        var id = (Long) request.getAttribute("userId");
        userService.logout(id);
        return ResponseEntity.ok("Bye bye");
    }

    @PutMapping("users/add-role")
    public ResponseEntity<String> giveRoleToUser(@RequestBody UserRoleRequest userRoleRequest) {
        userService.addRoleToUser(userRoleRequest.getUserId(), userRoleRequest.getRoleId());
        return ResponseEntity.ok("Role has been given to use");
    }

    @PostMapping("users/send-email")
    public ResponseEntity<String> sendRegistrationMail(@RequestBody AdminRegistrationRequest registrationRequest) throws Exception {
        var response = userService.sendLinkToUser(registrationRequest.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("users/check-link/{hash}")
    public ResponseEntity<String> chechHashOfRegistrationLink(@PathVariable String hash) {
        userService.checkIfHashExists(hash);
        return ResponseEntity.ok("Hash is past check");
    }

    @PostMapping("client/register")
    public ResponseEntity<User> registerClient(@RequestBody RegisterRequest registerRequest) {
        var userEntity = userService.register(registerRequest);
        userService.addRoleToUser(userEntity.getId(), 3L);

        return ResponseEntity.ok(User.toModel(userEntity));
    }

    @GetMapping("status")
    public String getStatus() {
        System.out.println("here");
        return "Working";
    }
}
