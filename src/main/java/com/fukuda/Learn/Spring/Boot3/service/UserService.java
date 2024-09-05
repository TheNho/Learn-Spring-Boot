// Service là nơi xử lý các logic của các api nhận vào

package com.fukuda.Learn.Spring.Boot3.service;

import com.fukuda.Learn.Spring.Boot3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
