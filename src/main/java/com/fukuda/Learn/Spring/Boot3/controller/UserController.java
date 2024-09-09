// Tiếp nhận các request

// @RequestBody UserCreationRequest request: mapping data của body vào biến request




package com.fukuda.Learn.Spring.Boot3.controller;

import com.fukuda.Learn.Spring.Boot3.dto.request.ApiRespone;
import com.fukuda.Learn.Spring.Boot3.dto.request.UserCreationRequest;
import com.fukuda.Learn.Spring.Boot3.dto.request.UserUpdateRequest;
import com.fukuda.Learn.Spring.Boot3.dto.respone.UserRespone;
import com.fukuda.Learn.Spring.Boot3.entity.User;
import com.fukuda.Learn.Spring.Boot3.mapper.UserMapper;
import com.fukuda.Learn.Spring.Boot3.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiRespone<UserRespone> createUser(@RequestBody @Valid  UserCreationRequest request){
        // @RequestBody map data của body vào object request

        ApiRespone<UserRespone> apiRespone = new ApiRespone<>();

        apiRespone.setResult(userService.createUser(request));

        return apiRespone;
    }

    @GetMapping
    List<UserRespone> getUser(){
        return userService.getUser();
    }

    @GetMapping("/{userID}")
    UserRespone getUser(@PathVariable String userID){
    // PathVariable map biến truyền trên path vào biến của hàm
        return userService.getUser(userID);
    }

    @PutMapping("/{userID}")
    UserRespone updateUser(@PathVariable String userID, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userID, request);
    }

    @DeleteMapping("/{userID}")
    String deleteUser(@PathVariable String userID){
        return userService.deleteUser(userID);
    }
}
