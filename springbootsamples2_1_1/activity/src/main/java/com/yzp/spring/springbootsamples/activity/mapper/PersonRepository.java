package com.yzp.spring.springbootsamples.activity.mapper;

import com.yzp.spring.springbootsamples.activity.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findByUsername(String username);
}
