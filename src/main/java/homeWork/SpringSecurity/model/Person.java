package homeWork.SpringSecurity.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "password")
    @Size(min = 5, max = 255, message = "Пароль от 5 до 255 символов")
    private String password;

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }

    @ManyToMany(fetch = FetchType.EAGER)//Тим связи и параметр означающий немедленное извлечение данных при получении объекта
    @JoinTable(//Настройка таблицы для хранения инфы о связи
            name = "user_role",//имя таблицы
            joinColumns = @JoinColumn(name = "user_id"),//Столбец для связи из таблицы Юзер
            inverseJoinColumns = @JoinColumn(name = "role_id")//Столбец для связи из таблицы Роли
    )
    private Set<Role> roles = new HashSet<>();
    public Person() {
    }

    public Person(String firstName, String lastName, int age, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() { return password; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
