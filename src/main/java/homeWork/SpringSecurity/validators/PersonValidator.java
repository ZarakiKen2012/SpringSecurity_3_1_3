package homeWork.SpringSecurity.validators;

import homeWork.SpringSecurity.model.Person;
import homeWork.SpringSecurity.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PersonServiceImpl personService;
    @Autowired
    public PersonValidator(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Optional<Person> person = personService.getPersonByName(((Person) target).getFirstName());
        if (person.isEmpty()){
            return;
        }
        errors.rejectValue("firstName","", "Человек с таким именем уже существует");
    }
}
