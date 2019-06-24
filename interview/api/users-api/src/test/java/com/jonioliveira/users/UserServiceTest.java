package com.jonioliveira.users;

import com.jonioliveira.users.domain.model.User;
import com.jonioliveira.users.exception.UserAlreadyExistsException;
import com.jonioliveira.users.exception.UserNotFoundException;
import com.jonioliveira.users.exception.UserTypeNotFoundException;
import com.jonioliveira.users.service.UserService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class UserServiceTest {

    @Inject
    UserService service;

    @Test
    public void userServiceAddUser(){
        try {
            User user = service.addUser("user7", 2);
            Assertions.assertEquals(user.getName(), "user7");
            Assertions.assertEquals(user.getType().getId(), 2);
        } catch (UserTypeNotFoundException | UserAlreadyExistsException e) {
            Assertions.fail();
        }
    }

    @Test
    public void userServiceAddUserFailExists(){
        try {
            User user = service.addUser("user8", 2);
            Assertions.assertEquals(user.getName(), "user8");
            Assertions.assertEquals(user.getType().getId(), 2);
        } catch (UserTypeNotFoundException | UserAlreadyExistsException e) {
            Assertions.fail();
        }

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
            service.addUser("user8", 2);
        });
    }

    @Test
    public void userServiceAddUserFailType(){
        Assertions.assertThrows(UserTypeNotFoundException.class, () -> {
            service.addUser("user9", 35);
        });
    }

    @Test
    public void userServiceLogin(){
        try {
            service.addUser("user9", 2);
            User user = service.login("user9");
            Assertions.assertEquals(user.getName(), "user9");
            Assertions.assertEquals(user.getType().getId(), 2);
        } catch (UserNotFoundException | UserTypeNotFoundException | UserAlreadyExistsException e) {
            Assertions.fail();
        }
    }

    @Test
    public void userServiceLoginFail(){
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            service.login("ze");
        });
    }

    @Test
    public void userServiceGetById(){
        try {
            User create = service.addUser("user10", 2);

            User user = service.getUserById(create.getId());
            Assertions.assertEquals(user.getName(), create.getName());
            Assertions.assertEquals(user.getId(), create.getId());
            Assertions.assertEquals(user.getType().getId(), create.getType().getId());
        } catch (UserNotFoundException | UserTypeNotFoundException | UserAlreadyExistsException e) {
            Assertions.fail();
        }
    }

    @Test
    public void userServiceGetByIdFail(){
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            service.getUserById(123);
        });
    }


}
