package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.model.User;
import org.example.request.AdminRegistrationRequest;
import org.example.request.LoginRequest;
import org.example.request.RegisterRequest;
import org.example.request.UserRoleRequest;
import org.example.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {

        var userEntity = userService.register(registerRequest);
        userService.addRoleToUser(userEntity.getId(), 2L);

        return ResponseEntity.ok(User.toModel(userEntity));
    }

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {

        var userEntity = userService.authenticate(loginRequest);
        return ResponseEntity.ok(User.toModel(userEntity));

    }

    @GetMapping("get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userService.getAll();

        return ResponseEntity.ok(users.stream().map(User::toModel).toList());
    }

    @PostMapping("logout")
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

    @PostMapping("send-email")
    public ResponseEntity<String> sendRegistrationMail(@RequestBody AdminRegistrationRequest registrationRequest) throws Exception {
        var response = userService.sendLinkToUser(registrationRequest.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("check-link/{hash}")
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

    @GetMapping("get-user-roles")
    public ResponseEntity<List<String>> getRoles(@Param("token") String token) {
        return ResponseEntity.ok(userService.getRolesViaToken(token));
    }

    @GetMapping("status")
    public String getStatus() {
        System.out.println("here");
        return "Working";
    }
}
