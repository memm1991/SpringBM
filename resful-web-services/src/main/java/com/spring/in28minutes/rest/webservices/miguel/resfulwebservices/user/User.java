package com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.user;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")
public class User {

    protected User() {}
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=2, message = "name is too short")
    @JsonProperty("user_name")
    private String name;
    @Past(message = "Birthdate should be in the pass")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> post;

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}
