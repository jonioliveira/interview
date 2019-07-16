package com.jonioliveira.interview;

import com.jonioliveira.interview.models.User;
import com.jonioliveira.interview.services.UserService;
import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

@Mock
@RestClient
@ApplicationScoped
public class MockUserService implements UserService {
    @Override
    public User getUserById(int id) {
        User user = new User();
        user.setId(1);
        user.setName("test");
        user.setType(1);
        return user;
    }
}
