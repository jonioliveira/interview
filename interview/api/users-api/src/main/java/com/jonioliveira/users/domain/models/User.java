package com.jonioliveira.users.domain.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    @Id
    @SequenceGenerator(name="users_id_seq",sequenceName="users_id_seq", initialValue=0, allocationSize=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    private Long id;


    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "userTypeId")
    private UserType type;

    public User(String name, UserType type) {
        this.name = name;
        this.type = type;
    }

    public User() {
        //used for panache
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
