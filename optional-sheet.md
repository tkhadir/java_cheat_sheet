# Optional Cheat Sheet
## I have an A a, how can I get an Optional<A> containing a?
```
Optional.of(a)    // a cannot be null
```
## I have an A aOrNull which can be null, how can I get an Optional<A>?

```
Optional.ofNullable(aOrNull)
```
This is extremely useful to convert the traditional nullable reference to an Optional object.

## How can I get an empty Optional<A>?

```
Optional.empty()
```
## How can I know if an Optional<A> maybeA contains any object?
```
maybeA.isPresent()    // Returns true if it is not empty
```
This method should be rarely used. If you find you use it frequently, see if you use if statements too much (or the notorious ?: operator). Take a look at Item 6 and Item 7 for a better approach.

## I have an Optional<A> maybeA, how can I get the instance in it?
You have four ways to get it:
```
maybeA.get()    // Throws NoSuchElementException if empty

maybeA.orElse(anotherObject)    // Returns anotherObject if empty

maybeA.orElseGet(() -> makeAnotherObject())  // Call the function if empty

maybeA.orElseThrow(() -> throw new MyException())   // Throw if empty
```
You usually only need to use them on the boundary of your method.

## I have an Optional<A> maybeA and a function B process(A a), how can I get an Optional<B>?
```
maybeA.map(a -> process(a))
```
It means if maybeA is empty, return an empty Optional<B>. Otherwise, return an Optional<B> containing process(a).

Use Optional.map to replace these if statements:

// Bad, because we cannot use the final modifier!
```
Optional<B> maybeB = Optional.empty();   

if (maybeA.isPresent()) {
    A a = maybeA.get();
    B b = process(a);
    maybeB = Optional.of(b);
}
```
// Good
```
final Optional<B> maybeB = maybeA.map(a -> process(a));
```
## I have an Optional<A> maybeA and a function Optional<B> process(A a), how can I get an Optional<B>?
```
maybeA.flatMap(a -> process(a))
```
It means if maybeA is empty, return an empty Optional<B>. Otherwise, return process(a).

Use Optional.flatMap to replace these if statements:
```
// Bad, because we cannot use the final modifier!
Optional<B> maybeB = Optional.empty();

if (maybeA.isPresent()) {
    A a = maybeA.get();
    maybeB = process(a);
}
```
// Good
```
final Optional<B> maybeB = maybeA.flatMap(a -> process(a));
```
## I have Optional<A> maybeA and Optional<B> maybeB and a function C process(A a, B b), how can I get an Optional<C>?
```
maybeA.flatMap(a ->
    maybeB.map(b ->
        process(a, b)))
```
What if the function has three arguments? Easy.
```
maybeA.flatMap(a ->
    maybeB.flatMap(b ->
        maybeC.map(c ->
            process(a, b, c))))
```
