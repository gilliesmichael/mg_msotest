package com.mgillies.test.mso.controller;

import com.mgillies.test.mso.exception.ResourceNotFoundException;
import com.mgillies.test.mso.model.Person;
import com.mgillies.test.mso.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	PersonRepository personRepo;
	
	/**create a new person object
	 *@param Person person object to be added 
	 */
	@PostMapping("/persons/add")
	public Person createPerson(@Valid @RequestBody Person p)
	{
		return personRepo.save(p);
	}
	
	/**
	 * return a person by id
	 * @param pid person id
	 * @return person object, or throw exception
	 */
	@GetMapping("/persons/get/{id}")
	public Person getPersonById(@PathVariable(value="id") Long pid)
	{
		
		return personRepo.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException ("person","id", pid));
	}
	
	/**
	 * return all persons
	 * @return List of all person objects stored
	 */
	@GetMapping("/persons/get")
	public List<Person> getAllPersons()
	{
		return personRepo.findAll();
	}
	
	/**
	 * update a person object
	 * @param pid id of person to update
	 * @param pDetails person object with new details to overwrite current person
	 * @return person object with details updated
	 */
	@PutMapping("/persons/upd/{id}")
	public Person updatePerson(@PathVariable(value = "id") Long pid,
            @Valid @RequestBody Person pDetails)
	{
		Person p = personRepo.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("person","id",pid));
		p.setForename(pDetails.getForename());
		p.setSurname(pDetails.getSurname());
		p.setEmail(pDetails.getEmail());
		
		return personRepo.save(p);
	}
	
	/**
	 * delete a person
	 * @param pid the ID of the object to be deleted, throws exception if fails.
	 * @return HTTP ResponseEntity status code 'OK'.
	 */
	@DeleteMapping("/persons/rem/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long pid)
	{
		Person p = personRepo.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("person","id",pid));
		personRepo.delete(p);
		
		return ResponseEntity.ok().build();
	}
	
	
}
