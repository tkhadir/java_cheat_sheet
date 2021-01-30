Lombok @Getter and @Setter Annotations
You can annotate any field with @Getter and/or @Setter, to let lombok generate the default getter/setter automatically.
```
@Getter @Setter
public class PersonLombok {
 String firstName;
 String lastName;
 LocalDate dateOfBirth;
}
```
Lazy Getter
In @Getter  When lazy set to true, it is possible to delay the initialization of the value until the getter method is called first.
```
package sample.lombok;
import lombok.Getter;
public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println("Main instance is created");
        m.getLazy();
    }

    @Getter
    private final String notLazy = createValue("not lazy");

    @Getter(lazy=true)
    private final String lazy = createValue("lazy");

    private String createValue(String name) {
        System.out.println("createValue(" + name + ")");
        return null;
    }
}
```
Constructor Annotations
@NoArgsConstructorYou can automatically generate the default constructor by giving it to the class.
```
import lombok.NoArgsConstructor;

@ NoArgsConstructor 
public  class Person1 {
    private  long id;
    private String name;
    private  int age;
}
```
@AllArgsConstructorBy assigning a class to a class, you can automatically generate a constructor with arguments to set values for all members.
```
import lombok.AllArgsConstructor;
@ AllArgsConstructor 
public  class Person2 {
    private  long id;
    private String name;
    private  int age;
}
```
@RequiredArgsConstructorBy assigning a class to a class, you can automatically generate a constructor with arguments to set the value to a required member (final member).
```
import lombok.RequiredArgsConstructor;
@ RequiredArgsConstructor 
public  class Person4 {
    private  final  long id;
    private String name;
    private  int age;
}
```
Lombok @EqualsAndHashCode Annotation
Giving the class an @EqualsAndHashCode annotation properly overrides the equals and hashCode methods of the target class depending on the members in the class.
@EqualsAndHashCode without specifying a parameter will generate processing of equals, hashCode using all members in the class
```
@EqualsAndHashCode 
public class PersonLombok {
	String firstName;
	String lastName;
	LocalDate dateOfBirth;
}
```
Lombok @ToString Annotation

A @ToString annotation provides a toString() implementation. Let’s see this:

By default, it’ll print your class name, along with each field, in order, separated by commas
```
@ToString
public class PersonLombok {
	String firstName;
	String lastName;
	LocalDate dateOfBirth;
}
```
Lombok @Data Annotation
By annotating the class with @Data , you get the same effect as setting all the following annotations.

 - @ToString
 - @Getter
 - @Setter
 - @RequiredArgsConstructor
 - @EqualsAndHashCode
```
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String summary;
    private String description;
}
```
Project Lombok @Log Annotation
You put the variant of @Log on your class (whichever one applies to the logging system you use); you then have a static final log field, initialized to the name of your class, which you can then use to write log statements.

@Log: creates
```
private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName());
```
@Log4j: creates
```
private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LogExample.class);
```
@Log4j2: creates
```
private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogExample.class);
```
@Slf4j: Creates
```
private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExample.class);
```
Lombok @Synchronized Annotations
@Synchronized is a safer variant of the synchronized method modifier. Like synchronized, the annotation can be used on static and instance methods only. It operates similarly to the synchronized keyword, but it locks on different objects. The keyword locks on this, but the annotation locks on a field named $lock, which is private.
If the field does not exist, it is created for you. If you annotate a static method, the annotation locks on a static field named $LOCK instead.
```
import lombok.Synchronized;
public class SynchronizedExample {
  private final Object readLock = new Object(); 
  @Synchronized
  public static void hello() {
    System.out.println("world");
  }
  
  @Synchronized
  public int answerToLife() {
    return 42;
  }
  @Synchronized("readLock")
  public void foo() {
    System.out.println("bar");
  }
}
```
Lombok @Builder annotation
Lombok provides an annotation for implementing the Builder pattern on your classes. 

@Builder can be placed on a class, or on a constructor, or on a method. While the “on a class” and “on a constructor” 
```
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class Person {
  private final String firstname;
  private final String lastname;
  private final String email;
}
```
Lombok @SneakyThrows Annotation
@SneakyThrows can be used to sneakily throw checked exceptions without actually declaring this in your method’s throws clause. 
```
import lombok.SneakyThrows;

public class SneakyClone implements Cloneable {

    @SneakyThrows
    @Override
    protected Object clone() {
        return super.clone();
    }
}
```
What happened behind:
```
public class SneakyClone implements Cloneable {
    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (final java.lang.Throwable $ex) {
            throw lombok.Lombok.sneakyThrow($ex);
        }
    }
}
```
Lombok @NonNull Annotation
You can use @NonNull on the parameter of a method or constructor to have lombok generate a null-check statement for you.
```
class User {
    @Getter @Setter @NonNull private String name;
}
```
The above annotated code is equivalent to the following java code –
```
class User {
    @Getter @Setter @NonNull private String name;

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull final String name) {
        if(name == null) throw new NullPointerException("name");
        this.name = name;
    }
}
```
