package kz.sayat.dao;

import kz.sayat.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {

    private List<Person> people;
    private static int PEOPLE_COUNT;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom",11,"tom2009@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob",32,"bob@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike",3,"mike222@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Katy",9,"katy@gmail.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setMail(updatePerson.getMail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId()==id);
    }
}