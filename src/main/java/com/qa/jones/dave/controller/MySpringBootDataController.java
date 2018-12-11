package com.qa.jones.dave.controller;

import java.util.List;

import javax.validation.Valid;

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.jones.dave.Repository.personRepository;
import com.qa.jones.dave.exception.ResourceNotFoundException;
import com.qa.jones.dave.model.mySpringBootDataModel;

@RestController
@RequestMapping("/api")
public class MySpringBootDataController {

	@Autowired
	personRepository myRepository;

	// method to create a person

	@PostMapping("/MySpringDataModel")
	public mySpringBootDataModel createPerson(@Valid @RequestBody mySpringBootDataModel mSDM) {
		return myRepository.save(mSDM);
	}

	// Method to get a person
	public mySpringBootDataModel getPersonByID(@PathVariable(value = "id") Long personID) {
		return myRepository.findById(personID)
				.orElseThrow(() -> new ResourceNotFoundException("mySpringBootDataModel", "id", personID));
	}

	// Method to get all people
	@GetMapping("/person")
	public List<mySpringBootDataModel> getAllPeople() {
		return myRepository.findAll();

	}

	// Method to update a person
	@PutMapping("/person/{id}")
	public mySpringBootDataModel updatePerson(@PathVariable(value = "id") Long personID,
			@Valid @RequestBody mySpringBootDataModel personDetails) {
		mySpringBootDataModel mSDM = myRepository.findById(personID)
				.orElseThrow(() -> new ResourceNotFoundException("Person", "id", personID));

		mSDM.setName(personDetails.getName());
		mSDM.setAddress(personDetails.getAddress());
		mSDM.setAge(personDetails.getAge());

		mySpringBootDataModel updateData = myRepository.save(mSDM);
		return updateData;

	}

	// Method to remove a person
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long personID) {
		mySpringBootDataModel mSDM = myRepository.findById(personID)
				.orElseThrow(() -> new ResourceNotFoundException("Person", "id", personID));

		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();

	}

	
	
}
