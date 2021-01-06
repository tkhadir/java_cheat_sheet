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

## Manipulating Dates and Times

```
plusXxx(int)
minusXxx(int)
return new immutable, original not changed, like String
chain of method on date and time
getXxx(); //Xxx= DayOfWeek,DayOfYear, Month, Year
dt1.isBefore(dt2)
dt1.isAfter(dt2)
Xxx= Years / Months /Days/Hours/Minutes/Seconds/Nanos;
Java 8 date & time are immutable, like String objects.
```

## Peroid and Duration

```
java.time.Period; Java.time.Duration;
4 ways to create a period
Period.ofXxx(int); //Xxx=Years,Months,Days
Period.of(int years,int months, int days);
Period.between (d1,d2)
Period.parse("P1Y2M3W4D");//same as Period.of(1, 2, 25)
Modify period
plusXxx(int); //Days|Weeks|Months|Years
minusXxx(int)
Use period
dt.plus/minnus(period);
d.plus/minus(period);
time.plus/minus(duration); //if input period, UnsupportedTemporalTypeException
Chain of Periods method does not make sense and raise compiler error
```

## Formatting

```
2 methods to create a formatter
DateTimeFormatter f=DateTimeFormatter.ofXxx(FormatStyle.SHORT|MEDIUM|LONG|FULL);
DateTimeFormatter f=DateTimeFormatter.ofPattern("yyyy-MMMM-dd hh:mm:ss"); //M 1 MM 01 MMM Jan MMMM January
Standard formatter:
DateTimeFormatter.ISO_LOCAL_DATE/TIME/DATETIME
ISO_LOCAL_DATETIME: '2011-12-03T10:15:30'
3 ways to use formatter
f.format( date/time/datetime_obj );
date/time/datetime_obj.format( f );
LocalDate/Time/DateTime.parse(String s, DateTimeFormatter f);
1 Xxx = LocalizedDate, LocalizedTime,LocalizedDateTime
2 date and time format legal for datatime object, datatime format not legal for date or time object
```
