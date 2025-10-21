# Programming II

## Lab 5.5 - Genericity & Parameterization

An introduction to using polymorphism and parameterization in the Java programming language.

### Attribution

This is a "mini lab" assignment used in CS 222 at [Saint Mary's College of California](https://www.stmarys-ca.edu/) in Fall 2025. It is based on one used in Computer Science II (CSCE 156 & 156H) in the School of Computing at the University of Nebraska-Lincoln. 
Originally written by Dr. Chris Bourke. Instructions were condensed by Dr. Sarah Roscoe.

## Instructions

The department of Math and Computer Science at SMC is expanding the CS program to graduate students as well. However, their codebase for representing students and courses is out of date. Your job is to make changes to the existing code to get it parameterized and working.

1. Clone this lab from Github. Use this link: <https://github.com/sroscoe2/CS222-Lab-05.git> Refer to Lab 1 if you need a refresher for how to do so. REMEMBER: when importing, name your folder `firstnameLIpartnerLI-CS222-Lab-05a`

2. Examine each of the 7 courses and discuss with your partner what they represent. Describe how each class should interact with the others. *For example*: "A `Droid` is NOT a `Student`, and should not be enrolled in a `Section` of a `Course`. 

3. The main issue is that we want to restrict `Droid`s from being added to courses. You must solve this problem, which will account for about 3-5 lines of code to edit.
    1. _Hint:_ Each `Section` currently enrolls `Object`s. What change do you make to a `Section` to allow only students? What change do you make to a `Course`? There may be more than one right answer. Discuss with your partner, make changes, then attempt to run the test cases.

4. **Getting Credit:** To get credit for this lab, show the instructor your code without syntax errors, and show the test cases passing.


## Explanations -- written by Dr. Chris Bourke


### Polymorphism

Polymorphism is the ability for variables, methods, and classes to take
on different forms (types) at different points in the execution of a
program. Polymorphism can take on various forms and allows us to write
more general code that can be applied to different types of variables
and classes.

The classical form of polymorphism is subtype polymorphism where you can
treat a type *T* as its super type *S* but the subtype retains its
methods and behavior. Typically this is done so that code that can be
written generally enough to be applied to type *S* can be applied to any
subtype of *S*.

## Parameterized Polymorphism

Parametric polymorphism is when code is written without a specific type
and instead can be applied to any type or types. Typically, the
arguments that you provide to a method or variable declarations within a
method have a fixed type (`int`, `double`, `String`, etc.). However, often code can be
written generically as it doesn’t depend on any particular property of
the types that it is applied to.

As an example, consider a `getMax()` method applied to a collection of integers,
doubles, or Strings which returns the maximal value in each of the lists
(numerically or lexicographically). Without parameterized polymorphism
we would need to write 3 different methods even though the logic of the
code would be the same (save perhaps for the way comparisons are made).
As a better alternative, we could write one method, which could be
applied to any collection of a generic type `T`. Here, `T` serves as a
parameterized *type*–you provide the method with a collection of objects
of type `T` and it will find the maximum among them and return it.

Of course, we may need a minimal amount of information about the type `T`.
In this particular case, we would need to know how to compare objects of
type `T` with each other in order to find the maximum. This introduces the
need for *bounded* parameterized polymorphism. In the context of the `getMax()`
method, we would need to know that objects of type `T` are (in some way)
comparable to each other and how to compare them.

Another context in which we would bound a parameterization is when we
only want objects of a particular type that are also a subclass of some
other class. For example, numerical methods such as a sum or average
method could be made generic, but could be bounded to specify that the
type must also be a subclass of a `Number`.



## The Activity 

In this activity you will explore how to parameterize classes in Java
using generics, and observe the consequences of not parameterizing.
Generics were introduced in Java 5. Prior to that (and for backwards
compatibility), Java used *raw types*. Collections such as `List` and `Set`
could hold *any* type of Java `Object`. The syntax for parameterizing a java
class is to include a `<T>` in its declaration:

```java
public class Foo <T> {
  ...
}
```

Once parameterized, the type `T` can now be used in the body of the class,
which will take on whatever type instances of `Foo` are instantiated with.
Once a parameterized class has been defined, the following syntax can be
used to instantiate an instance of `Foo` parameterized with an integer type:

`Foo<Integer> bar = new Foo<Integer>()`

Parameters can be *bounded* using the `extends` keyword. For example 
`<T extends Bar>` will match any type `T`, but `T` *must* be a subclass of 
`Bar`.

When you use a variable to represent your type, this is known as a
*named parameter* and is used when you need to refer to the type (such
as when you declare a variable, method parameter, or method return type
that needs to match `T`). However, if you don’t need to refer to the type,
it is better to use the wildcard, `?` instead of a specific type. The
wildcard matches any type and can still be used in conjunction with the
`extends` keyword to specify "any type that is a subtype of `Bar`" (using 
the syntax `<? extends Bar>`).

It is also possible to use generics of generics. For example, the
following creates an `ArrayList` that is parameterized to only hold objects 
of type `Foo` that have themselves been parameterized with a `Double`:

`List<Foo<Double>> baz = new ArrayList<Foo<Double>>();`

Several classes have been provided in the project to model `Undergraduate` 
students, `Graduate` students (both of which are subclasses of `Student`), 
and `Droid` (which are not students). Also included are `Course`s, which 
may have `Section`(s). These classes are
intended to model an enrollment system based on the following rules:

-   Each `Section` should only hold student types and each `Section` 
    should only consist of one type of `Student`. Grads and undergrads 
    should not be able to enroll in the same section.

-   Each `Course` should be able to hold any number of sections, but those
    sections should conform to the previous rules.

Follow the instructions below to parameterize your classes to conform to
these rules and to understand the implications of not parameterizing
your classes.

### Options for parameterizing a class

1.  Open the `CourseDemo`, `Course`, and `Section` class files. Two 
    instances of each entity (`Undergraduate`, `Gradaute`, `Droid`) 
    have been created for you (feel free to modify or add if you wish).

2.  In the `CourseDemo` class create a `Section` and enroll all 6 
    instances to this section.

**Problem**: Observe that since the `Section` class is not parameterized 
(it uses raw types) we are able to add any of these three types into the same
section, violating rule 1 above.

**Solution**: Parameterize the `Section` class so that each section can only hold
one type of object. Update the rest of the `Section` class to use this type as
needed. Observe that in `CourseDemo` the code you wrote should now be giving you
warnings: the `Section` class is now parameterized so you should parameterize
instances when you create them. An example:

`Section<Undergraduate> ugradSection = new Section<Undergraduate>("001");`

Modify your code in the `CourseDemo` to create three separate sections (one 
for `Undergraduate`, `Graduate`, and `Droid`) and add the entities to the 
appropriate section instead.

### Parameterizing with a Bound

**Problem**: The `Course` class still allows us to add sections consisting of
non-student objects

**Solution A**: we *could* parameterize the `Course` class just like we did with
the `Section` class to only hold `Section`(s) of a particular type. Try this solution.

**New Problem**: You will find that you are not able to add sections
holding different types.  For example, you will not be able to add a 
section of `Graduates` *and* `Undergraduates` only one or the other. 
Explain (to yourself or partner) why this is the case.

**Solution B**: We want the `Course` class to be able to hold `Section`(s) 
of any type of student. Remove the parameterizations you did in Solution A and instead
of parameterizing the class, parameterize the `courseRoster` list so that it can only
hold sections containing objects which must be a subtype of `Student` (hint: use
the wildcard).  Parameterize the `addSection` method so that it only accepts 
sections of one type of student (and so that no warnings or compiler errors are
issued).  If your changes were correct, then adding the section of `Droid`s 
should no longer be allowed.

### Parameterizing a class with a bound

**Problem**: though we can no longer add a section to a course with
non-student objects, we are still able to instantiate a section with
non-student objects

**Solution**: parameterize the `Section` class so that it can only hold one type
of object and that type of object must be a subtype of `Student`.
