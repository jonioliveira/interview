package com.jonioliveira.users.domain.repository;

import com.jonioliveira.users.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Long> {

    public User findByName(String name){
        return find("name", name).firstResult();
    }

    public long findByNameAndType(String name, long type){
        return find("name = ?1 and usertypeid = ?2", name, type).count();
    }


}
