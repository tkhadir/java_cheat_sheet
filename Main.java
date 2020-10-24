import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    class Person {
        final String s1;
        final String s2;
        final int age;
        Person(final String s1, final String s2, final int age) {
            this.s1 = s1;
            this.s2 = s2;
            this.age = age;
        }
        public String getLastName() {
            return s2;
        }
        public String getFirstName() {
            return s1;
        }
        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "{ lastname: " + s1 + ", firstname: " + s2 + ", age: " + age + " }";
        }
    }

    void sortExample() {
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(3);
        numbers.add(2);
        numbers.add(1);
        Collections.sort(numbers, (a, b) -> a.compareTo(b));
        numbers.stream().forEach(System.out::println);
    }

    void functionExample() {
        Integer n = 4;
        Function<Integer, Integer> modulo = (Integer a) -> a % n;
        modulo.apply(n);
        System.out.println(modulo.apply(4));
    }

    void biFunctionExample() {
        BiFunction<Integer, String, String> concat = (Integer i, String s) -> s + ": " + i;
        System.out.println(concat.apply(3, "hello"));
        BinaryOperator<String> biConcat = (s1, s2) -> s1.concat(": ").concat(s2);
        System.out.println(biConcat.apply("hello1", "hello2"));
    }

    void predicate(final String o) {
        Predicate<String> isEmpty = s -> s == null || s.isEmpty();
        System.out.println(isEmpty.test(o));
        String myString = "hello";
        Predicate<String> startWith = myString::startsWith;
        System.out.println(startWith.test("hel"));
    }

    void supplier() {
        Supplier<String> helloFromSupplier = () -> "hello from supplier";
        System.out.println(helloFromSupplier.get());
    }

    void comparator(int x, int y) {
        Comparator<Integer> ascending = (a, b) -> a.compareTo(b);
        System.out.println(ascending.compare(x, y));
        System.out.println(ascending.reversed().compare(x, y));
    }

    void listExample() {
        List<String> names = Arrays.asList("Barbara", "James", "Mary", "John");
        Collections.sort(names, String::compareToIgnoreCase);
        names.stream().forEach(System.out::println);
        Stream.of("a", "b", "c").forEach(System.out::println);
        Stream.builder().add("a").add("b").add("c")
                .build()
                .forEach(System.out::println);
        Random random = new Random();
        Stream.generate(() -> random.nextInt())
                .limit(10)
                .forEach(System.out::println);
        new Random().ints()
                .limit(10)
                .forEach(System.out::println);
        List<Person> persons = Arrays.asList(
                new Person("John", "Doe", 30),
                new Person("Jane", "Doe", 20),
                new Person("Jim", "Smith", 15)
        );
        persons.stream()
                .filter(p -> p.getLastName().startsWith("D"))
                .forEach(System.out::println);
        persons.stream()
                .sorted((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()))
                .forEach(System.out::println);
        persons.stream()
                .map(Person::getLastName)
                .sorted()
                .forEach(System.out::println);
        Boolean allMatch = persons.stream()
                .allMatch(p -> p.getLastName().startsWith("J"));
        Boolean noneMatch = persons.stream()
                .noneMatch(p -> p.getAge() == 35);
        Boolean anyMatch = persons.stream()
                .anyMatch(p -> "Doe".equals(p.getLastName()));
        persons.stream()
                .filter(p -> p.getAge() >= 20)
                .count();
        IntStream.rangeClosed(1, 10).sum();
        persons.stream()
                .reduce((p1, p2) -> new Person(p1.getFirstName(), p2.getLastName(), p1.getAge() + p2.getAge()))
                .ifPresent(System.out::println);
        System.out.println("total age == " + persons.stream()
                .map(Person::getAge)
                .reduce(0, Integer::sum));
        System.out.println(persons.stream()
                .reduce(0,
                        (result, person) -> result + person.getAge(),
                        (a, b) -> a + b
                ));
        persons.stream()
                .map(Person::getLastName)
                .collect(Collectors.toList());
        IntStream.concat(
                IntStream.range(0, 4),
                IntStream.range(4, 6)
        ).forEach(System.out::print);

        Stream.<List<Person>>builder()
                .add(Arrays.asList(new Person("John", "Doe", 30), new Person("Jane", "Doe", 20)))
                .add(Arrays.asList(new Person("Jim", "Smith", 15)))
                .build()
                .flatMap(p -> p.stream())
                .filter(person -> "Doe".equals(person.getLastName()))
                .forEach(System.out::println);
        persons.stream()
                .limit(2)
                .skip(1)
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("min == " + IntStream.rangeClosed(1, 10)
                .min()
                .getAsInt());

        IntStream.rangeClosed(1, 10)
                .max()
                .getAsInt();

        //peek debug stream
        persons.stream()
                .filter(p -> "Doe".equals(p.getLastName()))
                .peek(System.out::println)
                .filter(p -> p.getAge() < 25)
                .peek(System.out::println)
                .collect(Collectors.toSet());
        IntStream
                .range(0, persons.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> {
                    System.out.println("getting person : " + i + " equals to " + persons.get(i));
                    return persons.get(i);
                })
                .collect(Collectors.toList());

        Map<String, String> map = new HashMap<>();
        map.putIfAbsent("mykey", "hello");
        map.computeIfAbsent("un", key -> null);
        map.getOrDefault("un", "not hello");
    }

    void dateExamples() {
        LocalTime time1 = LocalTime.of(13, 37, 26);
        LocalTime time2 = LocalTime.of(15, 47, 54);

        assert time1.isBefore(time2) == true;

        LocalTime time3 = time1.plusMinutes(5);

        assert ChronoUnit.HOURS.between(time1, time2) == 2;
        assert ChronoUnit.MINUTES.between(time1, time3) == 5;

        time1.format(DateTimeFormatter.ISO_LOCAL_TIME); // 13:37:26
        time3.format(DateTimeFormatter.ofPattern("Ha")); // 13PM

        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.FRANCE);
        LocalTime time4 = LocalTime.parse("17:17", formatter);

        assert time4.getHour() == 17;
        assert time4.getMinute() == 17;

                LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = today.minusDays(1);

        LocalDate birthday = LocalDate.of(2014, Month.DECEMBER, 18);
        assert birthday.getDayOfWeek() == DayOfWeek.THURSDAY;

        DateTimeFormatter formatter2 = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)
                .withLocale(Locale.FRANCE);
        LocalDate date = LocalDate.parse("14 juillet 2014", formatter2);

        assert date.getDayOfMonth() == 14;
        assert date.getMonth() == Month.JULY;
        assert date.getYear() == 2014;

        LocalDateTime time = LocalDateTime.of(2014, Month.DECEMBER, 25, 12, 10);
        assert time.get(ChronoField.YEAR) == 2014;

        time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")); // 25/12/2014 12:10
        ZonedDateTime todayDate = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        System.out.println(todayDate.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));

        ZoneId.getAvailableZoneIds();

        ZoneId paris = ZoneId.of("Europe/Paris");
        paris.getDisplayName(TextStyle.FULL, Locale.FRENCH); // Heure d'Europe centrale
        paris.getDisplayName(TextStyle.SHORT, Locale.FRENCH); // CET

        Instant.parse("2007-12-03T10:15:30.00Z");

        Instant now = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();

        Instant clockNow = Clock.system(ZoneId.of("Europe/Paris")).instant();
        long millis = Clock.systemDefaultZone().millis();
        Duration twentySeconds = Duration.parse("PT20S");
        assert twentySeconds.getSeconds() == 20;
        Duration oneMinuteTwentySeconds = Duration.ofMinutes(1).plus(twentySeconds);
        assert oneMinuteTwentySeconds.getSeconds() == 80;

    }


    void strings() {
        String join = new StringJoiner(",")
                .add("a")
                .add("b")
                .add("c")
                .toString();
        assert join.equals("a,b,c");
    }

    void encode() {
        Base64.getEncoder().encode("JC".getBytes());
    }

    public static void main(final String args[]) {
        Main m = new Main();
        m.sortExample();
        m.functionExample();
        m.biFunctionExample();
        m.predicate(null);
        m.supplier();
        m.comparator(2, 1);
        m.listExample();
        m.dateExamples();
        m.strings();
        m.encode();
    }
}
