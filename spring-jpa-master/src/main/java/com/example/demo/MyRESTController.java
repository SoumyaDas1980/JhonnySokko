package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyRESTController {

	@Autowired
	ContactRepository repository;

	@GetMapping("/contacts")
	public Iterable<Contact> getContacts() {
		return repository.findAll();
	}


	@PostMapping(path = "/addcontact", consumes = "application/json")
	public void addContact(@RequestBody Contact contact) {
		repository.save(contact);
	}

	@DeleteMapping(path = "/deletecontact", consumes = "application/json")
	public void deleteContact(@RequestBody String emailid) {
		/*for(Contact contact : getContacts()){
			if(emailid.equalsIgnoreCase(contact.getEmailid())){
				repository.delete(contact);
			}
		}*/
		Contact mycontact = new Contact();
		mycontact.setEmailid(emailid);
		repository.delete(mycontact);
	}

	@GetMapping("/contactsbyplace")
	//@Query("select from Contact c where c.=:name)
	public Iterable<Contact> getContactsByPlace(@RequestBody Place place) {
		return repository.findByPlaceIn(place);
	}

}
