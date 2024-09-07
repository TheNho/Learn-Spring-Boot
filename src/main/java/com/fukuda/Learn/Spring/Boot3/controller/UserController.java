// Tiếp nhận các request

// @RequestBody UserCreationRequest request: mapping data của body vào biến request




package com.fukuda.Learn.Spring.Boot3.controller;

import com.fukuda.Learn.Spring.Boot3.dto.request.ApiRespone;
import com.fukuda.Learn.Spring.Boot3.dto.request.UserCreationRequest;
import com.fukuda.Learn.Spring.Boot3.dto.request.UserUpdateRequest;
import com.fukuda.Learn.Spring.Boot3.entity.User;
import com.fukuda.Learn.Spring.Boot3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiRespone<User> createUser(@RequestBody @Valid  UserCreationRequest request){
        // @RequestBody map data của body vào object request

        ApiRespone<User> apiRespone = new ApiRespone<>();

        apiRespone.setResult(userService.createUser(request));

        return apiRespone;
    }

    @GetMapping
    List<User> getUser(){
        return userService.getUser();
    }
    @GetMapping("/{userID}")
    User getUser(@PathVariable String userID){
    // PathVariable map biến truyền trên path vào biến của hàm
        return userService.getUser(userID);
    }

    @PostMapping("/{userID}")
    User updateUser(@PathVariable String userID, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userID, request);
    }

    @DeleteMapping("/{userID}")
    String deleteUser(@PathVariable String userID){
        return userService.deleteUser(userID);
    }
}
