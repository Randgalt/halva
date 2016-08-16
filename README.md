## Halva

**Welcome to Halva - _Idiomatic Scala... in Java!_**

[![Build Status](https://travis-ci.org/soabase/soabase-halva.svg?branch=master)](https://travis-ci.org/soabase/soabase-halva)
[![Maven Central](https://img.shields.io/maven-central/v/io.soabase.halva/halva.svg)](http://search.maven.org/#search%7Cga%7C1%7Chalva)

Halva's goal is to bring as many features from Scala to Java as is possible _without_ byte code generation 
or magic. Using the features in Java should be as close as possibile to how the features are used in Scala.

I wanted to see how close to Scala I could get in Java. Not only close in functionality, but close in syntax as well.
No magic, no byte code generation, nothing (too) tricky or magical. Just pure, standard Java 8. Halva is the result. The only reflection is for Google Guice's TypeLiteral class. The only "special" stuff is an annotation processor to generate case classes, type aliases and implicits. There are no dependencies whatsoever (Google Guice's TypeLiteral and JavaPoet are shaded into the code).

There are many 3rd party libraries that add support to Java in some form or another for some of Scala's features. However, most of these libraries are large and use their own DSLs or syntax. I want, where possible, to take unmodified Scala (adding semicolons!!) and get it to work in Java. Of course, this isn't literally possible. But, I began to wonder how close I could actually get.
Some features are pretty trivial: some simple "sugaring" that wouldn't be hard to add. Other features, like Tuples, could be supported just as well in Java as in Scala. There are, however, a few features that are complicated or seemingly impossible in Scala: Case Classes, Pattern Matching/Extractors/Partials, Comprehensions, and Implicits. There's no way to get total compatibility with Scala. But, could I get 50%? 70%? 80%? I searched the net for what people think are the killer features of Scala and attempted to implement them in pure Java. Project Halva is the result.

### Full Featured Example

To see the results of what Halva can do, please look at the [Simple Interpreter Example](../../tree/master/examples/README.md).

### Features

* [Case Classes and Case Objects](../../tree/master/halva/src/main/java/io/soabase/halva/caseclass/README.md)
* [Pattern Matching and Extraction](../../tree/master/halva/src/main/java/io/soabase/halva/matcher/README.md)
* [For Comprehensions](../../tree/master/halva/src/main/java/io/soabase/halva/comprehension/README.md)
* [Implicits](../../tree/master/halva/src/main/java/io/soabase/halva/implicit/README.md)
* [Type Aliases](../../tree/master/halva/src/main/java/io/soabase/halva/alias/README.md)
* [Tuples](../../tree/master/halva/src/main/java/io/soabase/halva/tuple/README.md)
* [Constructor Sugars](../../tree/master/halva/src/main/java/io/soabase/halva/sugar/README.md)
* [Type Containers](../../tree/master/halva/src/main/java/io/soabase/halva/container/README.md)
* [Custom Monadic Fors](../../tree/master/halva/src/main/java/io/soabase/halva/comprehension/CUSTOM.md)
* [Anys](../../tree/master/halva/src/main/java/io/soabase/halva/any/README.md)

### Example Generated Files

Many [example generated files](../../tree/master/examples/example-generated/README.md) are shown here.

### Using Halva

Halva is available from [Maven Central](http://search.maven.org/#search%7Cga%7C1%7Chalva). Use your favorite build tool and specify:

| GroupId | ArtifactId | Description |
|---------|------------|-------------|
| io.soabase.halva | halva-processor | Contains the javac processor for @CaseClass, @CaseObject, @TypeAlias, @ImplicitClass, and @TypeContainer |
| io.soabase.halva | halva | All the runtime code for Halva: matchers, comprehensions, etc. |

### FAQs

See miscellaneous info and [Frequently Asked Questions](FAQ.md)  

### Special Thanks

Special thanks to [@Alex-At-Home](https://github.com/Alex-At-Home) for his help and great ideas.
