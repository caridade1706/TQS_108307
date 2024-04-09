package tqs.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.example.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

    Person findById(int id);
    Person findByName(String name);

}