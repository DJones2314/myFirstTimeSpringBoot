package com.qa.jones.dave.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.jones.dave.model.mySpringBootDataModel;

@Repository
public interface personRepository extends JpaRepository<mySpringBootDataModel, Long>{

}
