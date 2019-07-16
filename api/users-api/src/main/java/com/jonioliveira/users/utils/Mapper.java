package com.jonioliveira.users.utils;

import com.jonioliveira.users.domain.model.User;
import com.jonioliveira.users.resource.model.response.UserResponse;

public class Mapper {

    public static UserResponse userToUserResponse(User user){
        return new UserResponse(user.getId(), user.getName(), user.getType().getId());
    }
}
