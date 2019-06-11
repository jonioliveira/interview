package com.jonioliveira.users.domain.repository;

import com.jonioliveira.users.domain.model.UserType;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserTypeRepository implements PanacheRepositoryBase<UserType, Long> {
}
