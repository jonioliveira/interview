package com.jonioliveira.users.domain.repository;

import com.jonioliveira.users.domain.models.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Long> {

    public User findByName(String name){
        return find("name", name).firstResult();
    }


}
