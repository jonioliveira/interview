package com.jonioliveira.users.domain.repository;

import com.jonioliveira.users.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Integer> {

    public User findByName(String name){
        return find("name", name).firstResult();
    }

    public int findByNameAndType(String name, int type){
        return (int) find("name = ?1 and usertypeid = ?2", name, type).count();
    }

    public User findUserById(int id){
        return findById(id);
    }


}
