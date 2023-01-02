package com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.jpa;

import com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
