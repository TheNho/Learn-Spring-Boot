// Các hàm tác động tới database

// Service là nơi xử lý các logic của các api nhận vào

package com.fukuda.Learn.Spring.Boot3.service;

import com.fukuda.Learn.Spring.Boot3.dto.request.UserCreationRequest;
import com.fukuda.Learn.Spring.Boot3.dto.request.UserUpdateRequest;
import com.fukuda.Learn.Spring.Boot3.entity.User;
import com.fukuda.Learn.Spring.Boot3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public User getUser(String userID)
    {
        return userRepository.findById(userID).orElseThrow(()-> new RuntimeException("User not found"));
    }

    public User updateUser(String userID, UserUpdateRequest request){
        User user = getUser(userID);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public String deleteUser(String userID){
        userRepository.deleteById(userID);
        return "User has been deleted.";
    }
}
