package com.fukuda.Learn.Spring.Boot3.mapper;

import com.fukuda.Learn.Spring.Boot3.dto.request.UserCreationRequest;
import com.fukuda.Learn.Spring.Boot3.dto.request.UserUpdateRequest;
import com.fukuda.Learn.Spring.Boot3.dto.respone.UserRespone;
import com.fukuda.Learn.Spring.Boot3.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(source = "firstName", target = "firstName")
//    @Mapping(source = "username", ignore = true)
    User toUser(UserCreationRequest request);

    UserRespone toUserRespone(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
