# Lab3_1 - Employee Manager 

## 3.1

### a) Identify a couple of examples that use AssertJ expressive methods chaining.
* A_EmployeeRepositoryTest:
 ```java
 assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
 ```
* B_EmployeeService_UnitTest:
```java
assertThat(doesEmployeeExist).isFalse();
```
* D_EmployeeRestControllerIT:
```java
 assertThat(found).extracting(Employee::getName).containsOnly("bob");
```
* E_EmployeeRestControllerTemplateIT:
``` java
 assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
```

### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database). 
On B_EmployeeService_UnitTest we have the mock of the behavior of the repository.

```java
    @Mock( lenient = true)
    private EmployeeRepository employeeRepository;


    @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }
```


### c) What is the difference between standard @Mock and @MockBean?
The key difference between `@Mock` and `@MockBean` lies in the context in which they are used. `@Mock` is an annotation from the Mockito library, ideal for isolated unit tests where the Spring context is not required. It creates a simulated instance of the object that is not added to the Spring context. On the other hand, `@MockBean` is an annotation from Spring Boot Test, designed for integration tests that need the Spring context. It can add or replace a bean in the Spring context with a mock, making it easier to simulate the behaviors of real beans without needing their complete implementations. While `@Mock` is more suited for tests focusing on the logic of a single class, isolating it from its dependencies, `@MockBean` is used when it's essential to interact with the Spring context, such as in Web controller tests or integration tests that involve multiple layers of the application.


### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The file `apllication-integrationstest.properties` has the properties to connect to a database and configure persistence. 
It would be used if we uncommenting the line ` @TestPropertySource(locations = "application-integrationtest.properties")` on the `D_EmployeeRestControllerIT` in order to use sed the actual database where project data is stored.

### e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences? 

In the scenario C, the application isn't completely initialized, only the controller is under test, eliminating the necessity to initialize services and repositories. MockMvc is utilized to simulate the remaining components.

For scenarios D and E, the application is completely initialized. Yet, in D, interaction with the server context occurs via a specialized testing servlet (the MockMvc object), whereas in E, the interaction is facilitated through a REST client (using TestRestTemplate).