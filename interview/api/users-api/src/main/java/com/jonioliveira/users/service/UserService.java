package com.jonioliveira.users.service;

import com.jonioliveira.users.domain.model.User;
import com.jonioliveira.users.domain.model.UserType;
import com.jonioliveira.users.domain.repository.UserRepository;
import com.jonioliveira.users.domain.repository.UserTypeRepository;
import com.jonioliveira.users.exception.UserAlreadyExistsException;
import com.jonioliveira.users.exception.UserNotFoundException;
import com.jonioliveira.users.exception.UserTypeNotFoundException;
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
    public User addUser(String name, int userType) throws UserTypeNotFoundException, UserAlreadyExistsException {
        UserType type = Optional.ofNullable(userTypeRepository.findById((long) userType)).orElseThrow(() -> new UserTypeNotFoundException(userType));
        if(userRepository.findByName(name) == null){
            User user = new User(name.toLowerCase(), type);
            userRepository.persist(user);
            return user;
        } else {
            throw new UserAlreadyExistsException(name);
        }
    }

    public User login(String name) throws UserNotFoundException {
        return Optional.ofNullable(userRepository.findByName(name)).orElseThrow(() -> new UserNotFoundException(name));
    }

    public User getUserById(long id) throws  UserNotFoundException{
        return Optional.ofNullable(userRepository.findUserById(id)).orElseThrow(() -> new UserNotFoundException(id));
    }
}
