package com.jonioliveira.users.services;

import com.jonioliveira.users.domain.models.User;
import com.jonioliveira.users.domain.models.UserType;
import com.jonioliveira.users.domain.repository.UserRepository;
import com.jonioliveira.users.domain.repository.UserTypeRepository;
import com.jonioliveira.users.exceptions.UserNotFoundException;
import com.jonioliveira.users.exceptions.UserTypeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Inject
    UserRepository userRepository;
    @Inject
    UserTypeRepository userTypeRepository;

    @Transactional
    public User addUser(String name, int userType) throws UserTypeNotFoundException {
        UserType type = Optional.ofNullable(userTypeRepository.findById((long) userType)).orElseThrow(() -> new UserTypeNotFoundException(userType));
        User user = new User(name.toLowerCase(), type);
        userRepository.persist(user);
        return user;
    }

    public User login(String name) throws UserNotFoundException {
        return Optional.ofNullable(userRepository.findByName(name)).orElseThrow(() -> new UserNotFoundException(name));
    }
}
