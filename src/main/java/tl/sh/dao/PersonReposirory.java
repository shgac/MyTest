package tl.sh.dao;
/**
 * Created by root on 2017/4/13.
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tl.sh.domain.Person;
import tl.sh.support.CustomRepository;

import java.util.List;

public interface PersonReposirory extends CustomRepository<Person,Long> {
    //JpaRepository<Person,Integer>{

    List<Person> findByName(String name);
    List<Person> findByAddress(String address);
    Person findByNameAndAddress(String name,String address);

    //@Query("select p from  Person p where p.name like % ?1 ")
    List<Person> findAllByNameContains(String name);
}
