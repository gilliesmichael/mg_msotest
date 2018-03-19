package com.mgillies.test.mso.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mgillies.test.mso.controller.PersonController;
import com.mgillies.test.mso.model.Person;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)

public class personControllerTest {
	
	@Autowired
	WebApplicationContext wac;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PersonController personController;
	
	private Person testPerson;
	
	@Before
	public void setup() throws Exception
	{
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
		testPerson = new Person("Smith","Jane","jane@xyz.com");
	}
	
	@Test
	public void testCreatePerson() throws Exception {
		
		mvc.perform(post("/api/persons/add")
				.contentType(APPLICATION_JSON)
				.content(ObjectToJSONString(testPerson)) )
				.andExpect(status().is2xxSuccessful()	);
	}

	@Test
	public void testGetPersonById() throws Exception {
		
		given(personController.getPersonById(testPerson.getId())).willReturn(testPerson);
		
		mvc.perform(get("/api/persons/get/"+testPerson.getId())
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("forename", is(testPerson.getForename())))
				.andExpect(jsonPath("surname", is(testPerson.getSurname())))
				.andExpect(jsonPath("email", is(testPerson.getEmail()))
				);
	}

	@Test
	public void testGetAllPersons() throws Exception{
		List<Person> allPersons = Collections.singletonList(testPerson);
		
		given(personController.getAllPersons()).willReturn(allPersons);
		
		mvc.perform(get("/api/persons/get")
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].forename", is(testPerson.getForename())))
				.andExpect(jsonPath("$[0].surname", is(testPerson.getSurname())))
				.andExpect(jsonPath("$[0].email", is(testPerson.getEmail()))
				);
	}

	@Test
	public void testUpdatePerson() throws Exception {
		
	    Person updPerson = new Person("Doe","John","jd@xyz.com");
	    
	    given(personController.getPersonById(testPerson.getId())).willReturn(testPerson);
	    
	    mvc.perform(put("/api/persons/upd/{id}", testPerson.getId())
                .contentType(APPLICATION_JSON)
                .content(ObjectToJSONString(updPerson)))
	            .andExpect(status().isOk());

	}
	
	@Test
	public void testDeletePerson() throws Exception {
given(personController.getPersonById(testPerson.getId())).willReturn(testPerson);
	    
	    mvc.perform(delete("/api/persons/rem/{id}", testPerson.getId())
                .contentType(APPLICATION_JSON))
	            .andExpect(status().isOk());
	}

	/**
	 * Convert an Object to JSON string
	 * @param obj An Object
	 * @return JSON String representation of object
	 */
	private String ObjectToJSONString(Object obj)
	{
		String jsonString="";
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			jsonString=ow.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
		
	}
}
