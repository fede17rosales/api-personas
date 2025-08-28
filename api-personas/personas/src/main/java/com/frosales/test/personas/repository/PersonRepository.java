package com.frosales.test.personas.repository;

import com.frosales.test.personas.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>, JpaSpecificationExecutor<Person> {
}
