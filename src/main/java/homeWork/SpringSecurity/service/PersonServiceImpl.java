package homeWork.SpringSecurity.service;


import homeWork.SpringSecurity.model.Person;
import homeWork.SpringSecurity.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class PersonServiceImpl implements PersonService {

    private final PeopleRepository peopleRepository;
    @Autowired
    public PersonServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    @Override
    public void addNewPerson(Person person) {
        peopleRepository.save(person);
    }

    @Override
    public Person getPersonById(int id) { return peopleRepository.getById(id); }

    @Override
    public Optional<Person> getPersonByName(String name){ return peopleRepository.findByFirstName(name); }
    @Override
    public List<Person> getAllPersons() { return peopleRepository.findAll(); }

    @Transactional
    @Override
    public void changePersonById(int id, Person person) { peopleRepository.updateUserById(id, person); }

    @Transactional
    @Override
    public void deletePersonById(int id){ peopleRepository.deleteById(id); }
}
