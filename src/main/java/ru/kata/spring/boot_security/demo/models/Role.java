package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return getName();
    }

    public String getNameRole() {
        return name.substring("ROLE_".length());
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")

    private String name;
    @ManyToMany(mappedBy = "roleList", cascade = CascadeType.ALL)
    private List<User> users;


    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return name;
    }
}
