package curso.java.hibernate;

import curso.java.hibernate.data.EmployeeRepository;
import curso.java.hibernate.data.entity.Employee;
import curso.java.hibernate.data.entity.Scope;
import curso.java.hibernate.data.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class HibernateExampleApp implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  EmployeeRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(HibernateExampleApp.class, args);
  }

  @Override
  public void run(String... args) throws Exception
  {

    Scope defaultScope1 = createScope("Default Scope", "This is a default scope for tasks");
    Scope defaultScope2 = createScope("Marvin Scope", "Este es mi scope de prueba");


    Employee emp2 = new Employee();
    emp2.setEmail("new Employee email");
    emp2.setFirstName("Bart");
    emp2.setLastName("Simpson");

    emp2.setTasks(getTasks(defaultScope1));


    Employee emp3 = new Employee();
    emp3.setEmail("marvin@mail");
    emp3.setFirstName("Marvin");
    emp3.setLastName("Berrio");

    emp3.setTasks(getTasks(defaultScope2));


    repository.save(emp2);
    repository.save(emp3);


    Optional<Employee> emp = repository.findById(2L);
    emp.ifPresent(employee -> logger.info("Employee id 2 -> {}", emp.get()));

    repository.findAll().forEach(System.out::println);
  }

  private Set<Task> getTasks(Scope scope) {

    Set<Task> tasks = new HashSet<>();
    Task task1 = new Task();
    task1.setTaskName("report generation");
    task1.setTaskDescription("Daily report generation");
    task1.setScope(scope);
    tasks.add(task1);


    Task task2 = new Task();
    task2.setTaskName("view generation");
    task2.setTaskDescription("Daily view generation");
    task2.setScope(scope);
    tasks.add(task2);


    return tasks;
  }

  private Scope createScope(String name, String description) {
    Scope scope = new Scope();
    scope.setScopeName(name);
    scope.setScopeDescription(description);
    return scope;
  }


}
