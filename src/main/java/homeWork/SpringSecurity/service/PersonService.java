package homeWork.SpringSecurity.service;



import homeWork.SpringSecurity.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    void addNewPerson(Person person);
    Person getPersonById(int id);
    Optional<Person> getPersonByName(String name);
    public List<Person> getAllPersons();
    void changePersonById(int id, Person person);
    void deletePersonById(int id);
}
