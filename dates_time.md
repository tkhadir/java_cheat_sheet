# Versions

- java 1.0
java.util.Date;java.text.Format
- java 1.2
java.util.Calendar
- java 8
java.time.*

## Creating Dates and Times

```
static of()
Overriden by LocalData, LocalTime and LocalD­ateTime
LocalDate.of(y,m.d);
m: int or Month(1-12)
LocalTime.of(h,mi,s,ns)
ns: nano second
LocalDateTime.of(y,m,d,h,mi,s,ns)
LocalDateTime.of(d,t)
static now()
Overriden by LocalData, LocalTime and LocalDateTime
static parse(s,f)
Overriden by LocalData, LocalTime and LocalDateTime
LocalDateTime.parse(s,f)
f must be DateTimeFormatter
LocalDate d= new LocalDate() //not compiled
LocalDate d= LocalDate.of(2015,2,30); //not compile
```
