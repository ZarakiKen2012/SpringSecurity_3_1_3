package homeWork.SpringSecurity.repositories;

import homeWork.SpringSecurity.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByFirstName(String name);
    @Modifying
    @Query("UPDATE Person person SET person.firstName = :#{#person.firstName}, person.lastName = :#{#person.lastName}, person.age = :#{#person.age} WHERE person.id = :id")
    void updateUserById(@Param("id") int id, @Param("person") Person person);
}
