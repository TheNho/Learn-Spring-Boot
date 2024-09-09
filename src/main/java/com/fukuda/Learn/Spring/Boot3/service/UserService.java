// Các hàm tác động tới database

// Service là nơi xử lý các logic của các api nhận vào

package com.fukuda.Learn.Spring.Boot3.service;

import com.fukuda.Learn.Spring.Boot3.dto.request.UserCreationRequest;
import com.fukuda.Learn.Spring.Boot3.dto.request.UserUpdateRequest;
import com.fukuda.Learn.Spring.Boot3.dto.respone.UserRespone;
import com.fukuda.Learn.Spring.Boot3.entity.User;
import com.fukuda.Learn.Spring.Boot3.exception.AppException;
import com.fukuda.Learn.Spring.Boot3.exception.ErrorCode;
import com.fukuda.Learn.Spring.Boot3.mapper.UserMapper;
import com.fukuda.Learn.Spring.Boot3.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserRespone createUser(UserCreationRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw  new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        return userMapper.toUserRespone(userRepository.save(user));
    }

    public List<UserRespone> getUser(){
        List<UserRespone> listUserRespone = new ArrayList<>();
        userRepository.findAll().forEach(user -> listUserRespone.add(userMapper.toUserRespone(user)));
        return listUserRespone ;
    }

    public UserRespone getUser(String userID) {
        return userMapper.toUserRespone(userRepository.findById(userID).
                orElseThrow(()-> new RuntimeException("User not found!")));
    }

    public UserRespone updateUser(String userID, UserUpdateRequest request){
        User user = userRepository.findById(userID).
                orElseThrow(()-> new RuntimeException("User not found!"));
        userMapper.updateUser(user, request);
        return userMapper.toUserRespone(userRepository.save(user));
    }

    public String deleteUser(String userID){
        userRepository.deleteById(userID);
        return "User has been deleted.";
    }
}
