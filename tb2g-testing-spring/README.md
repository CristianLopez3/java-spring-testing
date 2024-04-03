# Keep in Mind

In this section we are look into how to test a spring class


### Annotations

_General context:_
* @ComponentScan("package.name")
* @Autowired

_Working with tests:_
* @SpringJUnitConfiguration(classes = {})
* @ContextConfiguration(classes = {})
* @RunWith(SpringRunner.class)


_Working with profiles:_
* @Profile
* @ActiveProfile

_Property sources:_
* @TestPropertySource
* @Value

_Annotations for stereotype classes in spring:_ 
* @Component
* @Primary
* @Configuration
* @Service
* @Bean


### Using Properties values

You can use ".properties" files in order to provide dummy data to the application with th 
annotation *_@Value("${variable.name}")_* in between its brackets provide the variable name in 
the properties file, then you can consume your data with the annotation *_@TestPropertySource_*


>[!IMPORTANT]
> Always take a look into the documentation in order to get clear more concepts.


## Spring Mock Mvc

## standalone
Take a look and the next example to make a configuration for a test:
```java
import org.mockito.junit.jupiter.MockitoExtension;

import static org.springframework.test.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class Test {
    @Mock
    SomeMock someMock;
    @InjectMocks;
    SomeController someController;
    
    MockMvc mockMvc;
    
    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standalone(someController).build();
    }
    
    @Test
    void testView(){
        mockMvc.perform(get("/someUrl.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("someNameForAView"));
    }
}
```


---
---
--- 
---
## Spring Pet Clinic 

All source code examples in the repository are for my [Online Course - Testing Spring Beginner to Guru](https://www.udemy.com/testing-spring-boot-beginner-to-guru/?couponCode=GITHUB_REPO)

This source code repository contains JUnit 5 and Spring Framework Testing examples.

The main source code is a copy of the [Spring Framework Pet Clinic project](https://github.com/spring-petclinic/spring-framework-petclinic). This is a reference project 
using a traditional 3-layer architecture without Spring Boot.

## Setup
### Requirements
* Should use Java 11 or higher. Previous versions of Java are un-tested.
* Use Maven 3.6.0 or higher

### Running Locally
After cloning this repo, from the project root run:
```text
./mvnw jetty:run-war
```

## Support
For questions and help:
* Please post in course
* Or post in the Slack Community exclusive to the course.

GitHub Issues will not be addressed.