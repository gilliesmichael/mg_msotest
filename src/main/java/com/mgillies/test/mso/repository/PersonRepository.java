package com.mgillies.test.mso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgillies.test.mso.model.Person;

@Repository("PersonRepository")
public interface PersonRepository extends JpaRepository<Person, Long>{

}
