package tl.sh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * Created by root on 2017/4/13.
 */
@Entity
//@NamedQuery(name="Person.withNameAndAge",query = "select p from Person p where p.name=?1 and p.age=?2")
public class Person {
    @Id
    @GeneratedValue
    private Integer ID;


    private String name;
    private Integer age;
    private String address;

    public Person(){
        super();
    }
    public Person(Integer id, String name, Integer age,String address) {
        super();
        ID = id;
        this.name = name;
        this.age = age;
        this.address=address;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
