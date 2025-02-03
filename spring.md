# What is spring?

Spring is an open source development framework for development of Java applications

Name some of the important Spring Modules?

Some of the important Spring Framework modules are:
* Spring Context – for dependency injection.
* Spring AOP – for aspect oriented programming.
* Spring DAO – for database operations using DAO pattern
* Spring JDBC – for JDBC and DataSource support.
* Spring ORM – for ORM tools support such as Hibernate
* Spring Web Module – for creating web applications.
* Spring MVC – Model-View-Controller implementation for creating web applications, web services etc

The Core Container consists of the spring-core, spring-beans, spring-context, spring-context-support, and spring-expression (Spring Expression Language) modules.


# What is the purpose of application.properties file?
application.properties file is used to configure property values to run the application in different environments. 
It can be used to configure database details and log generation etc.
application.properties file is present in src/main/resources directory.

# What is pom.xml file?
POM stands for Project Object Model. It is an XML file in which the configuration of a Maven project is defined. 
The <dependency> tag lists all the dependencies with <groupid>, <artifactid>, <version>, and <packaging> tags providing necessary details about the dependencies.
Maven uses the information in pom.xml to build the project

# What design patterns are used in the Spring framework?
Spring framework uses a number of design patterns:

- Factory Pattern: BeanFactory and ApplicationContext classes
- Singleton Pattern: Singleton-scoped beans
- Prototype Pattern: Prototype-scoped beans
- Proxy Pattern: Spring Aspect Oriented Programming support
- Template Method Pattern: JdbcTemplate, JmsTemplate, JpaTemplate etc.
- Data Access Object Pattern: Spring DAO support
- Model View Controller Pattern: Spring MVC
- Front Controller Pattern: DispatcherServlet in Spring MVC
- View Helper Pattern: custom JSP tags separate code from presentation in views.
- Adapter Pattern: JMS adapters and JDBC adapters in Spring Integration

# What is Loose Coupling?
Loose Coupling is a design principle used in software development that promotes reducing the dependencies between different components, modules, or classes in a system. 
When two components are loosely coupled, they can function independently and have minimal knowledge about each other's internal implementation.

## Example in Code: Loose Coupling ##
Imagine a payment system where you want to support multiple payment methods (e.g., credit card, PayPal, or Google Pay). Instead of tightly coupling the code to each payment provider, you define an interface:
```java
// Interface for payment method
public interface PaymentProcessor {
    void processPayment(double amount);
}

// Implementation for CreditCard payment
public class CreditCardProcessor implements PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}

// Implementation for PayPal payment
public class PayPalProcessor implements PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

// Payment service using loose coupling
public class PaymentService {
    private PaymentProcessor processor;

    public PaymentService(PaymentProcessor processor) {
        this.processor = processor;
    }

    public void pay(double amount) {
        processor.processPayment(amount);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        PaymentProcessor creditCardProcessor = new CreditCardProcessor();
        PaymentService paymentService = new PaymentService(creditCardProcessor);

        paymentService.pay(100.0);

        // Switch to PayPal without changing PaymentService
        PaymentProcessor paypalProcessor = new PayPalProcessor();
        paymentService = new PaymentService(paypalProcessor);
        paymentService.pay(200.0);
    }
}

```
Why is this loose coupling?
The PaymentService doesn’t directly depend on CreditCardProcessor or PayPalProcessor. It depends on the PaymentProcessor interface.
You can easily add new payment methods (e.g., Google Pay) without modifying the PaymentService.

# What is Inversion of Control (IoC)?
Inversion of Control (IoC) is a design principle where the control of object creation, configuration, and lifecycle management is transferred from the application code to a container or framework. In simpler terms, IoC means that objects do not create other objects on which they depend. Instead, they get the objects they need from an external source (the IoC container).
IoC helps in decoupling the application components, making the code more modular, testable, and maintainable. Spring’s IoC container is responsible for instantiating, configuring, and assembling objects known as beans.

# What is the responsibility of an IoC container?
An IoC container performs the following tasks:

It instantiates the application class.
It identifies the beans along with their dependencies and wires the dependencies.
It manages the lifecycle of the beans from the time they are created till the time they are destroyed.
The IoC container uses the configuration metadata, in the form of an XML file or Java annotations, which contains instructions about the objects and their dependencies.

# Describe the two types of IoC containers.
The two types of IoC containers are BeanFactory and ApplicationContext. These are interfaces with various implementations that act as the IoC container.

BeanFactory provides the basic functionality of an IoC container while ApplicationContext adds extra functionality like AOP, message resource handling for internationalization, and WebApplicationContext for web applications.

# Give an example of the BeanFactory implementation.
The most commonly used implementation of BeanFactory is the XmlBeanFactory class. This container reads the metadata from an XML config file to create a fully configured application.

# What is ApplicationContext?
ApplicationContext is a type of IoC container. It extends the BeanFactory interface.

Similar to the BeanFactory, the ApplicationContext can load bean definitions, wire beans together, and return beans upon request. Additional features of ApplicationContext that are not part of the BeanFactory support for AOP and internationalization, publishing events, and application layer-specific contexts like WebApplicationContext.

# Give examples of the ApplicationContext implementations.
Commonly used implementations of ApplicationContext are:

**ClassPathApplicationXmlContext**: reads the configuration from an XML file for standalone Java applications.
**AnnotationConfigApplicationContext**: uses annotation-based configuration for standalone Java applications.
**AnnotationConfigWebApplicationContext** and **WebXmlApplicationContext**: for web applications.

# Difference Between BeanFactory and ApplicationContext

The default implementation of BeanFactory uses lazy initialization. It instantiates beans when the getBean() method is called. ApplicationContext extends the BeanFactory interface but the default implementation uses eager initialization. The beans are instantiated when the application starts. However, this behavior can be overridden.

BeanFactory does not support annotation-based dependency injection. This feature was included in ApplicationContext.

| **Feature**                                       | **ApplicationContext**                                           | **BeanFactory**                               |
|---------------------------------------------------|-------------------------------------------------------------------|-----------------------------------------------|
| Configuration Files                               | More than one configuration file possible                        | Only one configuration file or .xml file      |
| Event Publication (ApplicationEvent publication) | Supports event publication to beans registered as listeners      | Does not support                              |
| Internationalization (I18N)                      | Supports convenient MessageSource access for I18N                | Does not support                              |
| Application Life-Cycle Events                    | Supports application life-cycle events and validation            | Does not support                              |
| Enterprise Services                               | Supports many enterprise services such as JNDI access, EJB integration, remoting | Does not support                              |



# How is ApplicationContext configured in Spring?
There are multiple ways to configure application context:

Application context can be configured using XML. The context can be created using the ClassPathXmlApplicationContext, FileSystemXmlApplicationContext, or GenericXmlApplicationContext class which looks for an XML file defining the configuration.

Annotations can also be used to automatically register classes in the application context. The @Component annotation (along with @Controller, @Service, and @Repository annotations) is used on classes, and the AnnotationConfigApplicationContext class is used to create the application context.

Java configuration using the @Configuration annotation on classes and @Bean annotation on methods is another way to configure the application context. The AnnotationConfigApplicationContext class is used to create the context by scanning the annotations.

# What is WebApplicationContext?
WebApplicationContext interface extends ApplicationContext interface. It provides configuration for web applications.

WebApplicationContext defines request, session, and global application scopes in addition to the singleton and prototype scopes in ApplicationContext.

It has the ability to resolve themes and decide which servlet it is associated with.

# What happens if the context is not closed?#
Not closing the context leads to resource leaks. The close() method destroys all beans, releases the locks and closes the bean factory. Similarly, using a try-with-resources block also ensures that each resource is closed when the block exits.

# What is dependency injection?#
Dependency injection is a concept that states that the developer should not create objects manually in the code but specify how the objects should be created. The IoC container reads this information and instantiates the object with the required dependencies.

Dependency injection is the process of finding a bean to be autowired. If class A has a dependency on class B, then the process of identifying the dependency, creating an instance of class B, and autowiring the object of class B in class A is called dependency injection.

# How is dependency injection related to inversion of control?
Inversion of control (IoC) is a general concept which can be expressed in different ways. Dependency injection is an example of IoC.

IoC concept is that the control of creating and wiring the dependencies shifts from the developer to the framework. Dependency injection is the process by which the framework identifies the dependencies of an object, finds a match, and wires the dependency in the object.

# What are the types of dependency injection?
A dependency can be injected in several ways:

- Field injection
- Setter injection
- Constructor injection

# What is constructor injection?
In constructor injection, the IoC container creates the object by calling a constructor with a number of arguments, where each argument represents a dependency on another class.

The following code example uses a constructor to inject the Engine dependency in Vehicle class.
```java
@Component
Class Vehicle {
  private Engine engine;
  Vehicle(Engine engine) {
      this.engine = engine;
  }
  //...
}
```
If the class contains more than one constructor, then the @Autowired annotation must be used on a constructor to tell the spring container that this constructor is to be used for dependency injection. For a class with one constructor, like the one shown above, the @Autowired annotation is optional.

# How does setter injection work?
Setter injection works by calling setter methods to set the property values. Spring container creates the object by calling the no-argument constructor and then calls setter methods to populate the dependencies.
The Engine dependency is injected using the setEngine() method. When Spring finds the @Autowired annotation, it will call the setter method for dependency injection.
```java
@Component
Class Vehicle {

  private Engine engine;

    @Autowired
    void setEngine (Engine engine) {
        this.engine = engine;
    }

    Engine getEngine ( ) {
        return engine;
    }
    //...
}
```
# What is the difference between constructor and setter injection?
- Constructor injection is not partial while setter injection offers partial dependency injection. If an object has 5 fields, it is not possible to pass just 1 in a 5 argument constructor.
- Constructor injection does not override setter, whereas setter injection overrides constructor if both are defined. The IoC container by default chooses setter injection.
- Constructor injection works well if the number of properties is large, whereas setter injection would make the code longer in such a scenario.
- Setter injection is flexible because it is possible to change the value of the property without creating a new bean instance. In case of constructor injection, a new bean is needed if a property is modified.

# Which dependency injection approach is better?
All dependency injection approaches have the same outcome. This is a very debatable question with some people favoring one style while others touting for another.

The documentation for older versions of Spring suggested that constructor injection be used for all mandatory dependencies while setter injection for optional dependencies. However the @Required annotation on a setter method can be used to make it a mandatory dependency.

# What is method injection?
Any method can be used for setting the dependency if the @Autowired annotation is used on it. This is referred to as method injection. The method can have any name. As long as it has the @Autowired annotation, Spring will find a matching dependency to inject.

# What is a circular dependency, and how should it be resolved?
When beanA has a dependency on beanB and benB has a dependency on beanA, it results in a circular dependency. In this case both beans try to inject each other via constructor and Spring throws **BeanCurrentlyInCreationException**.

More than two beans can also result in a circular dependency as follows:

beanA => beanB => beanC => beanD => beanA

Spring creates beans in order in which they are needed. If a bean has a dependency, then the dependency is created first and then injected to complete the creation of the bean. In case of circular dependency, spring cannot decide which bean to create first.

Circular dependency issue arises when using constructor injection because the beans are created when the context is loaded. If using setter or field injection, the beans are created but their dependencies are injected only when they are needed. Thus the circular dependency issue can be avoided.

When using constructor injection, @Lazy annotation can be used. This tells spring that when initializing the bean, inject a proxy. The bean is fully created only when it is needed.

```java
@Component
public class ClassA {

  private ClassB classB;

  @Autowired
  public ClassA(@Lazy ClassB classB) {
      this.classB = classB;
  }
}
```


# What is a profile in Spring?
A profile is a logical grouping of beans and configurations that can be activated based on the environment, such as dev, test, prod, etc.

# How do you define a profile in Spring?
Profiles can be defined using the @Profile annotation or within configuration files like application.properties.

# How do you activate a specific profile in Spring Boot?
- By using the spring.profiles.active property in:
- application.properties or application.yml
- Command-line arguments (--spring.profiles.active=dev)
- Programmatically in the code.

# What is the difference between spring.profiles.active and spring.profiles.default?
spring.profiles.active explicitly activates profiles, overriding any defaults.
spring.profiles.default specifies the default profile to use when no active profile is set.

# How can profiles be used with @Configuration?
Use @Profile("profile-name") to define beans that are created only when a specific profile is active.

# Practical Questions
# How do you handle multiple profiles in a single application?

Define separate configuration files (e.g., application-dev.properties, application-prod.properties) and activate the appropriate profile at runtime.
How do you use profiles with Spring Boot's YAML configuration?

```yaml
spring:
  profiles:
    active: dev
---
spring:
  profiles: dev
  datasource:
    url: jdbc:mysql://localhost/dev_db
---
spring:
  profiles: prod
  datasource:
    url: jdbc:mysql://prod-server/prod_db

```
# How do you set profiles in a JUnit test?

Use the @ActiveProfiles annotation:
```java
@ActiveProfiles("test")
@SpringBootTest
public class MyTest {
    // Test code here
}

```
# What happens if no profile is active in Spring Boot?

Spring Boot will load properties from the default application.properties or application.yml file.

# Can you activate multiple profiles at the same time?

Yes, by separating them with commas:
```yaml
spring.profiles.active=dev,qa
```

Scenario-Based Questions
# How would you configure a database URL for multiple environments using profiles?

Define environment-specific configurations:

```yaml
# application-dev.properties
spring.datasource.url=jdbc:mysql://localhost/dev_db

# application-prod.properties
spring.datasource.url=jdbc:mysql://prod-server/prod_db
```

# How do you debug profile-related issues when configurations are not being picked up?

- Verify the active profile using the /actuator/env endpoint (if Actuator is enabled).
- Ensure the correct profile is set via logs or spring.profiles.active.

# How would you write a custom @Profile condition?
Implement a custom Condition class and use it with @Conditional.


# What is a component scan?
The component scan is the process in which Spring searches for beans. Spring needs to know the location (package) to search for beans. Component scan can be defined in two ways:

Using the @Component annotation and its sub-classes in which case Spring searches all the packages and sub-packages containing this annotation.
Using XML configuration with <context:component-scan> tag specifying the base package.

# How is a component scan done in Spring Boot?
In Spring Boot, the @SpringBootApplication annotation triggers a component scan on the package and its sub-packages where it is used.

# Annotations related Questions

# What is the purpose of the @Component annotation?
@Component annotation is used to define a bean. This annotation is placed on a Java class. The XML file contains the <context: component-scan> tag to enable component scanning. Spring container scans the package (and its sub-packages) specified in the component-scan tag. It will register all classes marked with the @Component annotation as beans.
Using the @Component annotation can save a lot of time spent in writing lengthy bean definition code in XML.

# What is the difference between @Component, @Service, @Repository, and @Controller?

All are specializations of @Component, but they are used in different layers of an application:
@Component: Generic stereotype for any Spring component.
@Service: Marks service layer classes for business logic.
@Repository: Marks data access objects (DAO) and adds exception translation.
@Controller: Used in the Spring MVC web layer to handle requests.

# Why is @Primary annotation used?

The @Primary annotation in Spring is used to indicate which bean should be given preference when multiple beans of the same type are available in the application context. 
It resolves ambiguity during dependency injection when Spring encounters more than one matching bean.

Why is @Primary Used?
When multiple beans of the same type exist, Spring cannot decide which bean to inject automatically, leading to a NoUniqueBeanDefinitionException. To avoid this, you can use:

@Primary: Mark one of the beans as the primary/default choice.
@Qualifier: Specify exactly which bean to inject.

# Why is @Qualifier annotation used?

The @Qualifier annotation in Spring is used to resolve ambiguity when multiple beans of the same type are defined in the Spring application context. 
It allows you to specify exactly which bean should be injected, even when multiple beans match the required type.

# Which annotations takes precedence: @Primary or @Qualifier?
When both the @Primary and @Qualifer annotations are present, then @Qualifier takes precedence. @Primary defines a default value, and bean marked with this annotation will be used unless otherwise indicated. @Qualifier annotation is specific and is used when a particular bean is needed.

Suppose there are two beans of the same type and one is used in 90% of the cases, then it makes sense to make it the default choice by using the @Primary annotation. The @Autowired that needs the other bean can use the @Qualifier annotation while all other @Autowired will automatically choose the bean marked with @Primary.

# Why is the @Required annotation used?#
@Required is a method-level annotation. It is used on setter methods and makes setter injection of the property mandatory. The BeanInitializationException is thrown if the property value is not initialized. If a setter method has @Autowired annotation on it, then @Required is not needed.

This annotation has been deprecated because constructor injection is used for setting all mandatory dependencies.

# What is autowiring in Spring?
Connecting beans together in the Spring container is called autowiring. It is the process by which collaborating beans are tied together without the developer having to write explicit object instantiation code.

It reduces the code as well as the development time because it removes the need to write dependency injection code.

# What are the different modes of autowiring in Spring?
When using XML configuration the autowiring mode can be specified using the autowire attribute in the <bean> tag. The modes of bean autowiring are:

no: the default autowiring mode is no autowiring in which case the developer has to provide explicit bean reference using the ref attribute.
byName: the bean is injected by matching the property, that needs to be autowired, with a bean that has the same name. The property name must match a bean name for this type of autowiring to work.
byType: the bean is injected by matching the property, that needs to be autowired, with a bean of the same type. If no matches are found, the property is not set. If more than one matches are found an error occurs.
constructor: the dependency is injected by calling the constructor with a bean whose type matches with the constructor argument. If no matches are found, an error occurs.

# What is the purpose of @Autowired annotation
The @Autowired annotation in Spring is used for automatic dependency injection. It tells the Spring IoC container to automatically inject a bean into a class field, constructor, or setter method. 
It simplifies the process of connecting dependent components in a Spring application by automatically resolving and injecting the required dependencies.

# How Does Spring Resolve Dependencies with @Autowired?

1. By Type: Spring matches the type of the dependency to a bean in the application context.
Example: If a field is of type MyService, Spring searches for a bean of the same type.
2. By Name (with @Qualifier): If there are multiple beans of the same type, you can specify the exact bean to inject using @Qualifier.
```java
@Autowired
@Qualifier("myService1")
private MyService myService;

```
3. With @Primary: If multiple beans exist and one is marked with @Primary, Spring will inject that bean unless overridden with @Qualifier.

# What is the difference between @Inject and @Autowired in Spring? Which one to use under which condition?
Both these annotations perform the same function and are used for dependency injection by type. The order of dependency injection of both the annotations is as follows:

- By type
- Using @Qualifer annotation
- By name.
The only difference between both annotations is that @Inject is a CDI annotation which makes it framework-independent and @Autowired is a Spring framework annotation. Thus using @Inject may be helpful if the application is moved to another framework.

# Both @Bean and @Component annotations create beans. What is the difference between the two

1. @Bean Annotation

Purpose: The @Bean annotation is used to explicitly declare a Spring bean within a Java configuration class. It is typically used for beans that are created programmatically or require some custom initialization logic.

Usage: It is always used within a @Configuration-annotated class.

Lifecycle: Spring invokes the method annotated with @Bean, and the return value of the method is registered as a Spring bean.
Use Case: Use @Bean when:

You need to configure a third-party library/component.
You need more control over the bean creation process, such as setting specific properties or performing initialization.

2. @Component Annotation
   Purpose: The @Component annotation is used to mark a class as a Spring-managed component. The Spring IoC container automatically detects and registers it as a bean during component scanning.

Usage: It is placed directly on the class.

Lifecycle: Spring automatically creates and registers the bean during component scanning.

Use Case: Use @Component for:

Application classes that are part of your business logic, such as service classes, repositories, or controllers.

```java
@Configuration
public class AppConfig {
    @Bean
    public ThirdPartyLibraryService thirdPartyService() {
        return new ThirdPartyLibraryService("customConfig");
    }
}

@Component
public class MyComponent {
    private final ThirdPartyLibraryService service;

    @Autowired
    public MyComponent(ThirdPartyLibraryService service) {
        this.service = service;
    }
}

```
# What is the @Configuration annotation?
Indicates that a class contains bean definitions. Equivalent to an XML configuration file. Often used with @Bean to define beans programmatically.

# What Does @SpringBootApplication Do?
It is a composite annotation that combines the following three annotations:

* @Configuration:

Indicates that the class contains one or more bean definitions and serves as a configuration class for the Spring application context.
Equivalent to an XML-based configuration file in traditional Spring applications.

* @EnableAutoConfiguration:

Enables Spring Boot's auto-configuration feature, which automatically configures beans based on the application's classpath and other settings (e.g., if you have spring-web on the classpath, it configures a DispatcherServlet for you).
Reduces boilerplate configuration required for Spring applications.

* @ComponentScan:
Enables component scanning, which allows Spring to detect and register beans annotated with @Component, @Service, @Repository, @Controller, etc., in the same package and sub-packages of the class where @SpringBootApplication is used.

# What is the @EnableAutoConfiguration annotation, and how does it work?
@EnableAutoConfiguration annotation,  tells Spring Boot to automatically configure beans and components for your application based on the dependencies and libraries present on the classpath.
Purpose of @EnableAutoConfiguration
The primary purpose of @EnableAutoConfiguration is to reduce boilerplate code by letting Spring Boot set up application configurations automatically. For example:

If spring-boot-starter-web is on the classpath, it configures beans such as DispatcherServlet, WebMvcConfigurer, and a Tomcat server automatically.
If spring-boot-starter-data-jpa is present, it sets up a DataSource, EntityManagerFactory, and repositories.
Without @EnableAutoConfiguration, developers would need to manually configure these components.

# What is @Value, and how is it used?
Used to inject values from properties files or environment variables into fields:
``java
@Value("${app.name}")
private String appName;

``

# What is @PropertySource, and how is it different from @Value?

@PropertySource loads a properties file into the Spring Environment. It works alongside @Value to inject property values.

# What is Spring Security?
It is a Spring module that provides authentication and authorization functionality to Spring MVC applications. It also provides the PasswordEncoder interface to secure user passwords. Spring Security takes care of common security vulnerabilities.

Spring security intercepts a user request and checks if the user is authorized to access the protected resources. It reads the application’s security configuration and also looks at the user’s passwords and roles to see if the user is authenticated and authorized to access the web resource.

# What is Spring Boot?
Spring Boot is a framework for building Java-based, production-ready applications with minimal configuration. It simplifies the development of Spring applications by providing preconfigured setups.
What are the important Goals of Spring Boot?
* Simplify Spring application development.
* Provide out-of-the-box configurations via conventions.
* Reduce boilerplate code with starter dependencies.
* Enable embedded servers for standalone apps.

# What are the important Features of Spring Boot?
Starter dependencies (e.g., spring-boot-starter-web).
Auto Configuration for quick setups.
Embedded servers like Tomcat.
Production-ready features (e.g., Actuator for monitoring)

# Compare Spring Boot vs Spring
Spring Boot extends Spring with easier setup, embedded servers, and auto-configuration, while Spring provides the core DI/IoC framework.
Example: Spring Boot eliminates web.xml and adds default configurations for dependencies.

# Compare Spring Boot vs Spring MVC
Spring MVC is a module for building web applications, while Spring Boot streamlines the setup and configuration of MVC apps with embedded servers and starters.
Example: Spring Boot auto-configures DispatcherServlet for Spring MVC.

# How can we find more information about Auto Configuration?
Use the spring-boot-actuator endpoint /actuator/autoconfig or view the class META-INF/spring.factories in the jar files.

# What is an embedded server?
An embedded server is a server instance (like Tomcat, Jetty, or Undertow) that runs directly within your Spring Boot application rather than being deployed externally.
It is packaged as part of the application’s executable JAR or WAR.

Example: A Spring Boot application with spring-boot-starter-web automatically includes Tomcat as the embedded server.

# What is the default embedded server with Spring Boot?
The default is Apache Tomcat.

# What are the other embedded servers supported by Spring Boot?
Jetty
Undertow
Netty



# What are Starter Projects?
Starter Projects in Spring Boot are predefined sets of dependencies that make it easy to set up specific types of applications or functionalities. They provide a quick way to include commonly used libraries and configurations, reducing boilerplate code.

Why are they useful?
Simplifies Setup: They bundle the necessary dependencies for different use cases (e.g., web, JPA, security).
Reduces Configuration: The starters come pre-configured with sensible defaults.
Consistency: Ensures common dependencies across multiple applications.
Example Starter Projects:
spring-boot-starter-web (for web applications with RESTful APIs)
spring-boot-starter-data-jpa (for working with databases using JPA/Hibernate)
spring-boot-starter-security (for security features like authentication and authorization)
In pom.xml, you can include a starter project like:

```xml

<dependency>  
<groupId>org.springframework.boot</groupId>  
<artifactId>spring-boot-starter-web</artifactId>  
</dependency>
```



# What is Starter Parent?
spring-boot-starter-parent is a special parent POM (Project Object Model) used in Spring Boot applications. It serves as the foundation for managing dependencies, plugin versions, and common configurations in a Spring Boot project.

Key Features of Starter Parent:
Dependency Management: It defines default versions for commonly used libraries in Spring Boot projects.
Plugin Management: Configures necessary plugins (like the Maven compiler plugin) with sensible defaults.
Configuration for Common Settings: Handles basic application settings like Java version, logging configurations, and Spring Boot plugin setup.


Example:
In your pom.xml, you can include the spring-boot-starter-parent as the parent POM:

```xml
<parent>  
<groupId>org.springframework.boot</groupId>  
<artifactId>spring-boot-starter-parent</artifactId>  
<version>2.5.0</version>  
</parent>
```
  
This eliminates the need to manually specify versions for many dependencies, as the parent POM takes care of it.

# What are some of the important things that can be customized in application.properties?
Server port (server.port)
Datasource settings (spring.datasource.*)
Logging (logging.level.*)

# How do you configure and use externalized configuration in Spring Boot?
Externalized configuration in Spring Boot allows you to manage application settings in various sources (e.g., application.properties, environment variables).

Key Approaches:
* Using application.properties or application.yml:

Define properties in src/main/resources/application.properties:
```yaml
server.port=8081
app.name=MyApp
```
Access the properties using @Value or @ConfigurationProperties:


```java
@Value("${app.name}")
private String appName;
```

* Using Environment Variables:

Define environment variables in your OS or container. Spring automatically maps them to configuration properties.

Using @ConfigurationProperties:

```yaml
app.config.name=MyApp
app.config.version=1.0
```

```java
@Component
@ConfigurationProperties(prefix = "app.config")
public class AppConfig {
private String name;
private String version;
// Getters and Setters
}
```

* Command-Line Arguments:

Pass properties as JVM arguments:

java -jar app.jar --server.port=8082

* Profiles:

Use profiles (application-{profile}.properties) for environment-specific settings:
```yaml
java -jar app.jar --spring.profiles.active=prod
```

# What is Spring Boot Actuator?

**Spring Boot Actuator** is a powerful and essential tool in Spring Boot for managing and monitoring production-ready applications. It provides built-in features for checking the health, metrics, application status, and other vital aspects of your Spring Boot application, with minimal configuration.

---

### **Key Features of Spring Boot Actuator**

1. **Health Checks**:
   - Spring Boot Actuator allows you to monitor the health of your application by providing endpoints like `/actuator/health`.
   - Health checks can be customized to check the status of various components like databases, message brokers, or custom components.

   Example response from `/actuator/health`:
   ```json
   {
       "status": "UP",
       "components": {
           "db": {
               "status": "UP",
               "details": {
                   "database": "H2",
                   "hello": "Greetings from H2 database"
               }
           }
       }
   }
   ```

2. **Metrics**:
   - The actuator provides various **metrics endpoints** (e.g., `/actuator/metrics`) to help monitor the performance of your application. Metrics include memory usage, garbage collection, database connections, and much more.
   - This data can be crucial in understanding your app's resource usage.

   Example:
   ```json
   {
       "names": ["jvm.memory.used", "jvm.gc.time", "process.uptime"]
   }
   ```

3. **Application Info**:
   - The `/actuator/info` endpoint can expose general information about the application such as version, build, and custom application properties.

   Example:
   ```json
   {
       "app": {
           "version": "1.0.0",
           "name": "MySpringBootApp"
       }
   }
   ```

4. **Audit Events**:
   - Spring Boot Actuator can track audit events (e.g., login attempts, password resets) and expose them via the `/actuator/auditevents` endpoint.

5. **Environment Info**:
   - The `/actuator/env` endpoint can expose environment properties like system properties, environment variables, and configuration properties used in your application.

6. **Thread Dump**:
   - The `/actuator/threaddump` endpoint provides detailed information about the state of all threads in your application, which can be useful for debugging performance issues.

7. **Loggers**:
   - The `/actuator/loggers` endpoint allows you to view and modify logging levels dynamically. You can change the log level for specific classes or packages in real time.

   Example:
   ```json
   {
       "levels": {
           "org.springframework.web": "DEBUG"
       }
   }
   ```

8. **Tracing**:
   - The `/actuator/httptrace` endpoint provides trace information for HTTP requests. It includes request details, response times, and status codes.

9. **Custom Endpoints**:
   - You can easily create **custom actuator endpoints** if you need to expose additional functionality or metrics.

---

### **How to Enable Spring Boot Actuator**

1. **Add Dependency**:
   To use Spring Boot Actuator, simply add the `spring-boot-starter-actuator` dependency in your `pom.xml` or `build.gradle` file:

   **For Maven**:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```

   **For Gradle**:
   ```groovy
   implementation 'org.springframework.boot:spring-boot-starter-actuator'
   ```

2. **Expose Endpoints**:
   By default, Spring Boot only exposes a limited set of actuator endpoints. You can configure which endpoints are enabled and exposed in your `application.properties` or `application.yml`.

   For example, to expose health and metrics endpoints, add the following:
   ```properties
   management.endpoints.web.exposure.include=health,metrics
   ```

   You can also choose to expose all endpoints:
   ```properties
   management.endpoints.web.exposure.include=*
   ```

   To secure the endpoints, you can also configure authentication:
   ```properties
   management.endpoint.health.show-details=always
   management.endpoints.web.exposure.include=health
   management.endpoints.web.exposure.exclude=env,beans
   ```

---

### **Commonly Used Actuator Endpoints**

1. **`/actuator/health`**: Shows the health status of the application.
   - Includes information about the database, messaging systems, and custom health checks.

2. **`/actuator/metrics`**: Shows various metrics such as memory usage, garbage collection statistics, active threads, and HTTP request statistics.

3. **`/actuator/info`**: Displays arbitrary application information, such as build version and other metadata.

4. **`/actuator/env`**: Shows environment properties, including system properties and configuration properties.

5. **`/actuator/loggers`**: Lets you view and modify the logging levels for different components.

6. **`/actuator/threaddump`**: Provides detailed information about the current state of the application's threads.

7. **`/actuator/auditevents`**: Exposes application audit events.

---

### **Customization**

1. **Custom Health Indicators**:
   You can add custom health checks by implementing `HealthIndicator`:
   ```java
   @Component
   public class MyCustomHealthIndicator implements HealthIndicator {
       @Override
       public Health health() {
           boolean isHealthy = checkSomeService();
           if (isHealthy) {
               return Health.up().build();
           } else {
               return Health.down().withDetail("Service", "Not available").build();
           }
       }

       private boolean checkSomeService() {
           // Logic to check the health of some service
           return true;
       }
   }
   ```

2. **Custom Metrics**:
   You can define your own metrics using the `MeterRegistry`:
   ```java
   @Component
   public class CustomMetrics {
       private final MeterRegistry meterRegistry;

       public CustomMetrics(MeterRegistry meterRegistry) {
           this.meterRegistry = meterRegistry;
       }

       @PostConstruct
       public void init() {
           meterRegistry.gauge("my_custom_metric", 1);
       }
   }
   ```

3. **Securing Endpoints**:
   You can secure actuator endpoints with Spring Security:
   ```properties
   management.endpoints.web.exposure.include=health,metrics
   management.endpoints.web.exposure.exclude=*
   management.endpoint.health.show-details=when-authorized
   ```

---

### **Advantages of Spring Boot Actuator**

- **Real-time Monitoring**: Enables quick access to application health, metrics, and status.
- **Ease of Use**: Pre-configured endpoints that require minimal setup.
- **Extensibility**: Easily add custom health checks, metrics, and endpoints.
- **Insights for Troubleshooting**: Provides valuable data for identifying performance bottlenecks and failures.
- **Production-Ready**: Allows you to monitor and manage applications effectively in production environments.

---

### **When to Use Spring Boot Actuator?**

- **In production environments**: It’s essential for monitoring the health and performance of the application.
- **For performance tuning**: It helps collect important metrics that can be used for optimizing the app.
- **In microservices architectures**: When dealing with multiple services, actuator endpoints help you keep track of the health and status of each service.



# What is Spring MVC?
Spring MVC is the module of Spring that implements the front controller and MVC design pattern. It provides a decoupled approach to developing web applications.
In Spring MVC, the three components of the MVC architecture, the front controller, view resolver and model are not dependent on each other. 
All incoming requests are handled by the front controller which is called the DispatcherServlet.

# What are the advantages of using Spring MVC?
- Spring MVC is based on interfaces which are independent of each other allowing separation of concerns.
- Because there is no explicit dependency between the interfaces, Spring MVC applications are easily testable.
- The view technology is customizable and switching between JSP, Velocity or Thymeleaf etc. can be done easily.
- Spring MVC supports RESTful web services.

# What is the flow of request in the MVC architecture?
The dispatcher servlet is the front controller which receives all requests from the client. It has a mapping of all controllers and maps the incoming request to the appropriate controller.
The controller executes the request and returns a model and view name to the dispatcher servlet. The model contains the results.
The dispatcher servlet uses the view resolver to resolve the name of the view and populates it with results from the model and displays it to the client as a web page.

# What are the steps required to create a Spring MVC application?
To create a simple MVC application, we perform the following steps:

- Include the spring-context and spring-webmvc dependencies in the pom.xml file.
- Define the dispatcher servlet in web.xml to handle all requests.
- Configure the controller classes using XML or annotations.
- Create URL mappings in controller classes to handle incoming requests.
- Configure a view resolver.

# Is spring-mvc jar included in spring-core?
spring-mvc jar is not a part of spring-core. It needs to be added separately to the classpath. It has a dependency on spring-core jar which is downloaded automatically if a build tool like Maven is used.

# What is the Front Controller Pattern?
Front Controller Pattern is a design pattern in which request handling is centralized. All client requests go to the front controller which contains a mapping of all controllers/servlets.
The front controller maps the client request to the relevant controller/servlet, receives the results back, and renders the view to the client.
In Spring DispatcherServlet is the front controller. It receives all the requests from the client and forwards them to the appropriate controller.

# Describe the function of the DispatcherServlet.
The DispatcherServlet is at the heart of Spring MVC. It is the front controller which handles all the HTTP requests and responses.
The dispatcher servlet uses a configuration file containing information of all controllers and the URL mappings to forward an incoming request to the correct controller.
It gets a model object and view back from the controller. The dispatcher servlet then forwards the view name to the view resolver and renders the response back to the client.

# How is the Dispatcher servlet configured?
Dispatcher servlet can be configured using XML or programmatically using the ServletContainerInitializer interface.
XML configuration: The dispatcher servlet is configured in the web.xml file like any other servlet. 
The <servlet-name> tag specifies the name, while <servlet-class> tag specifies the class as org.springframework.web.servlet.DispatcherServlet.

To direct all requests to go through the dispatcher servlet, the <servlet-mapping> tag is used with the /* URL mapping. 
The <load-on-startup> tag with value 1 is used to preload the dispatcher servlet, otherwise it is loaded when a request comes.

Java configuration: Spring provides its own implementation of the ServletContainerInitializer interface. 
We can use the class AbstractAnnotationConfigDispatcherServletInitializer and provide implementation of the getServletConfigClasses and getServletMappings methods to configure the dispatcher servlet.

# How does the dispatcher servlet map a request to a controller method?
The dispatcher servlet uses handler mappings and annotations like @Controller and @RequestMapping to map a request to a controller method.
The HandlerMapping interface provides a mapping between requests and handler objects. Spring provides implementations of this interface where incoming request URLs can be mapped to controller classes.

# What is a controller?
A controller is a class that handles user requests. It is annotated with the @Controller annotation.
A controller class contains the business logic of the application. Based on the client request, a controller populates the model which is shown to the user as a view.

# What is the function of @Controller annotation?
The @Controller annotation is used at class level. It marks the class as a controller class and tells the Spring framework that this class will handle user requests.

# What is the difference between @Controller and @RestController annotations?
Web applications and REST APIs differ in their return types with the former returning a view comprising of HTML, CSS and JavaScript while the later returning JSON or XML data.

The @Controller annotation populates the model map and returns a view name that is sent to the view resolver. The @RestController annotation returns an object which is written to HTTP response as JSON or XML.

The @RestController annotation is a combination of @Controller and @ResponseBody annotations. To return JSON or XML in a web application, we need to use the @ResponseBody annotation explicitly with the @Controller annotation.

# What is the function of @RequestMapping annotation?
When the @RequestMapping annotation is used at class level, it acts as a parent mapping which maps the URL of an incoming request.

When it is used on method level, it maps the URL as well as the HTTP request method.

When using @RequestMapping annotation, can multiple paths map to the same controller method?
Usually, the @RequestMapping annotation is used to map a single path to a controller method but this is not a rule. Multiple mappings can be specified in the value attribute as can be seen from the following example:

```java
@RequestMapping(value = { "/hello", "/hi" })
@ResponseBody
public String multipleMapping() {
  return "Hello World!";
}
```
Both /hello and /hi are mapped to the multipleMapping() method which returns a String response.

# When using @RequestMapping annotation, can the same HTTP request be mapped to multiple controller methods?
We can have multiple methods in the controller with the same request mapping as long as the request method is different. In the following example, /userForm is mapped to both the showForm() and processForm() methods. 
One method is for GET request while the other is for POST request.

```java
@RequestMapping(value = "/userForm")
public String showForm() {
  return "user-form";
}

@RequestMapping(value = "/userForm", method = RequestMethod.POST)
public String processForm() {
  return "user-confirmation";
}
```
Adding @RequestMapping annotation to the methods
When @RequestMapping annotation is used without the method attribute, it defaults to HTTP GET request.

# When using @RequestMapping annotation, can multiple HTTP request methods map to the same controller method?
Yes, it is possible to map different HTTP request methods to the same controller method. The method attribute accepts multiple values. In the following example, both PUT and POST requests to /userForm are mapped to the addUser() method.

```java
@RequestMapping(value = "/userForm", 
                method = {RequestMethod.PUT, RequestMethod.POST})
public String addUser() {
  return "user-confirmation";
}
```
Using @RequestMapping annotation to map different HTTP request methods to the same controller
# What is an ambiguous mapping error?
When Spring finds more than one controller methods having the same URL mapping, HTTP request method, parameters, headers and media type, it throws the ambiguous mapping error because it cannot choose which controller method to map the incoming request to. Changing anything from the above mentioned list to differentiate the controller methods will resolve this error.

# What is the function of @ResponseBody annotation?
The value returned by a controller method is resolved to a view name. However, if we want to write directly to the response body, we use the @ResponseBody annotation to tell Spring that instead of resolving the return value as a view name, it should be displayed to the client as a String.

The following method resolves hello-world as a view name and displays the page by this name to the user.

@RequestMapping(value = "/hello")
public String welcome() {
return "hello-world";
}
When the @ResponseBody annotation is used on the same method, hello-world is displayed as the response.



# What are the shortcut annotations for HTTP request methods?
The @RequestMapping annotation can be used with the method attribute to specify the HTTP request method. Spring also provides shortcut annotations for five HTTP request types which are as follows:

@GetMapping
@PostMapping
@PutMapping
@PatchMapping
@DeleteMapping
The purpose of these annotations is to make the code short and readable. Behind the scene, all these annotations are resolved to the @RequestMapping annotation with the respective HTTP method attribute. 
The example below shows the same request mapping in two different ways.

@RequestMapping(value = "/userForm", method = RequestMethod.POST)
//OR
@PostMapping((value = "/userForm")

# What is the isELIgnored attribute and what is its default value?
The isELIgnored attribute is used to disable the evaluation of scriptlets. The default value of this attribute is true for JSP 1.2. For JSP 2.0 and above, the default value is false.

# How would you handle exceptions globally in Spring Boot?
Global exception handling in Spring Boot is typically implemented using the @ControllerAdvice annotation, which allows you to centralize exception handling for all controllers. Here's how you can do it:


```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    
```

Explanation:
@RestControllerAdvice (or @ControllerAdvice): Indicates this is a centralized exception-handling component.
@ExceptionHandler: Maps specific exceptions to custom error responses.
ResponseEntity: Used to return custom HTTP status codes and messages.
You can also use @ResponseStatus on exception classes to map them to HTTP statuses.


# What is AOP, and how does it relate to OOP?
AOP stands for Aspect Oriented Programming. It divides the application logic into parts called concerns. AOP is similar to OOP in terms of modularity. It differs from OOP in the way modularity is defined. In OOP, modularity is achieved by using classes whereas in AOP modularity is achieved using aspects that are applied to different class methods. Since aspects or concerns apply to the whole application, their processing should be centralized.

# What is a cross-cutting concern in AOP?
A cross cutting concern is something that affects the whole application. Instead of handling it separately in every layer, it is handled in a centralized manner. Examples of cross cutting concerns are security, logging, transaction management, and error handling etc.

# Is there a difference between the terms concern and cross cutting concern in Spring AOP?
Concern can be defined as the functionality of a particular module in the application. For example in an e-commerce application, concerns may be inventory management and user management etc.

A cross cutting concern is a concern that is applicable across multiple application layers. For example logging and security functionality is needed by every module of an application.

# What problems does AOP solve?
AOP modularizes the code in terms of cross cutting concerns. The absence of AOP leads to two main problems.

Code tangling: the code for the cross cutting concern gets mixed with business logic.
Code scattering: Identical code is present in all modules.

# Name some implementations of AOP?
Popular implementations of AOP include Apache AspectJ, JBoss by Red Hat and Spring AOP.

# What is the difference between Spring AOP and AspectJ AOP?
Spring AOP does weaving at runtime using proxy while AspectJ does compile time weaving using AspectJ Java tools.

Only method level pointcuts are supported by Spring AOP while AspectJ also supports field level pointcut.

# How does Spring implement a cross cutting concern?
Spring AOP separates the business logic and cross cutting concern. The developer focuses on the program logic. Spring provides many aspects for cross cutting concerns which can be woven into the application.

# What is a proxy in Spring AOP?
In simple words, a proxy is an object that looks like another object but has some added functionality. In Spring AOP, proxy is the object created after applying advice to a target object.

Note: Proxy = Advice + Target Object

Target object is also called a proxy object.

# What is a target object?
Target object is an object to which a cross cutting concern has been added. It is also called advised object or proxy object.

# What advantage does Spring AOP provide?
Spring AOP allows us to add or change a concern without having to change the application code. Since we separate the concerns from the application logic, concerns can be dynamically plugged in before, after or around the application logic. The code also becomes easy to maintain. Another advantage is that the developer can concentrate on business logic rather than the cross cutting concerns.

Spring AOP configures aspects as normal beans. Also, no special compilation unit is needed when using Spring AOP.

# What is a JoinPoint?
JoinPoint is a point in the program such as method execution where an aspect can be plugged in. There are different types of JoinPoints like field access, error handling and method execution.

Spring AOP only provides support for method execution join points. Any method inside the class can be called a join point if any cross cutting concern is applied to it and an aspect’s code is inserted into the normal flow of the application.

# What is advice?
The logic of the aspect is called advice. It is the action that is taken when an aspect is executed. In terms of programming, advice is the execution of the method where a joinpoint matches a pointcut.

# List the different types of advice in AOP?
There are 5 types of advice:

Before
After
After Returning
After Throwing
Around
Which advice type is appropriate for a try catch block?
In order to try and catch exceptions, the @AfterThrowing advice type is used. The method annotated with this annotation is run after the method exits by throwing an exception.


# What are the different types of advice annotations in Spring AOP?

@Before: Executes before a method is invoked.
@After: Executes after a method is invoked (regardless of outcome).
@AfterReturning: Executes after a method returns a value.
@AfterThrowing: Executes if a method throws an exception.
@Around: Combines @Before and @After, providing full control over the method invocation.

# What is the difference between Joinpoint and ProceedingJoinPoint?
Proceedingjoinpoint extends the Joinpoint interface.

Joinpoint is used with the @Before, @After, @AfterReturning and @AfterThrowing advice types.

Proceedingjoinpoint is used with @Around advice. The @Around advice type is different from the rest because it can control when/if a method is executed. It also has a return value.

# What is pointcut?
Pointcut is the expression that is matched to a JoinPoint to determine whether the advice should be executed or not. Spring framework uses the AspectJ pointcut expression language. These contain matching method or class name patterns.

# What is a named pointcut?
When we need a pointcut at multiple places in the application, rather than using the lengthy pointcut expression, we can give it a name. This is done by creating a pointcut configuration class where we associate every pointcut with a method. Now the method name can be used in place of the long pointcut expression.

# What is an Aspect?
An aspect is a class denoted by the @Aspect annotation. It contains advice and joinpoints. Aspect defines a concern that cuts across multiple application layers.

# What is weaving?
Weaving is a process in which the aspects are plugged in at different points in the program execution. In Spring AOP, weaving is done at runtime. AspectJ provides both compile-time and load-time weaving.

# When does the Spring framework perform weaving?
Spring framework performs weaving at runtime. The process of weaving aspects into the application classes takes place when the classes are being loaded in JVM.

# Which AspectJ Pointcut Designators are supported by Spring AOP?
Spring provides support for some of the AspectJ Pointcut Designators (PCD) that can be used in pointcut expressions. For example:

execution: matches method execution joinpoints
within: matches to joinpoints of certain types
this: matches to joinpoints where the target object is of a given type
args: matches to joinpoints where the arguments of the given type
@annotation: matches to joinpoints where the method has a given annotation

# Are there any limitations of Spring AOP?
Spring AOP only supports method-level joinpoints.
Advice is only applicable on public methods. Methods with private or protected visibility cannot be advised.
When weaving with proxies, advice is not executed on local method calls.
Aspects can only be applied to Spring beans.