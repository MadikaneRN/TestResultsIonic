package za.ac.cput.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.domain.Person;
import za.ac.cput.repositories.PersonRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private PersonRepository repository;

    @Autowired
    public HomeController(PersonRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model) {
        List<Person> persons = repository.findAll();
        model.addAttribute("persons", persons);
        model.addAttribute("insertRecord", new Person());
        return "home";
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getRecord(@PathVariable("id") long id) {
        Person person = repository.getOne(id);
        if (person == null) {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }


    @RequestMapping(value = "/clients/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getOneRecord()
    {
        List<Person> person = repository.findAll();
        if(person.isEmpty())
        {
            return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);//OR HttpStatus.Not_Found
        }

        return new ResponseEntity<List<Person>>(person,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public String insertData(ModelMap model,
                             @ModelAttribute("insertRecord") @Valid Person record,
                             BindingResult result) {
        if (!result.hasErrors()) {
            repository.save(record);
        }
        return home(model);
    }

    @RequestMapping(value = "/client/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createRecord(@RequestBody Person person, UriComponentsBuilder ucBuilder)
    {
        repository.save(person);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/record/{id}").buildAndExpand(person.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/client/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Person> deleteRecord(@PathVariable("id")long id)
    {
        System.out.println("Fetching & Deleting Adoption with id" + id);
        Person person = repository.getOne(id);
        if(person  == null)
        {
            System.out.println("Unable to delete. Adoption with id " + id + " not found");//comment
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);

        }

        repository.delete(person);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

}
