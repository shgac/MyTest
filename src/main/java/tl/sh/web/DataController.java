package tl.sh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tl.sh.dao.PersonReposirory;
import tl.sh.domain.Person;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by root on 2017/4/13.
 */
@Controller
public class DataController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
  /*  PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save(String name,Integer age){
       Person person=(Person) personRepository.save(new Person(null,name,age));
       return person;
    }*/
   @RequestMapping("/")
    public String name(){
       return "hello2";
   }
   @Autowired
   private PersonReposirory personReposirory;
    @RequestMapping("/save")
    public Person save(String name,Integer age, String address){
        Person person= personReposirory.save(new Person(null,name,age,address));
        return person;
    }
    @RequestMapping("/q1")
    public List<Person> q1(String name){
        List<Person> people = personReposirory.findByName(name);
        return people;
    }
    @RequestMapping("/sort")
    public List<Person> sort(){
        List<Person> people=personReposirory.findAll(new Sort(Sort.Direction.ASC,"ID"));
        return people;
    }
    @RequestMapping("/auto")
    public Page<Person> auto(Person person){
        Page<Person> personPage=personReposirory.findByAuto(person,new PageRequest(0,10));
        return personPage;
    }
    @RequestMapping("/q2")
    public List<Person> q2(String name){
        List<Person> people=personReposirory.findAllByNameContains(name);
        return people;
    }
}
