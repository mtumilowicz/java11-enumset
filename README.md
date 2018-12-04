[![Build Status](https://travis-ci.com/mtumilowicz/java11-enumset.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-enumset)

# java11-enumset
Overview of `EnumSet` API.

_Reference_: https://docs.oracle.com/javase/10/docs/api/java/util/EnumSet.html

# preface
`EnumSet` is specialized `Set` implementation for use 
with enum types.

Enum sets are represented internally as bit vectors. This 
representation is extremely compact and efficient. The 
space and time performance of this class should be good 
enough to allow its use as a high-quality, typesafe alternative 
to traditional int-based "bit flags." (for bit flags please
refer my other github project: 
https://github.com/mtumilowicz/java11-ORed-container)

Even bulk operations (such as containsAll and retainAll) 
should run very quickly if their argument is also an enum set.

The returned iterator (from `iterator()`) is weakly 
consistent (for iterator types please refer my other github
project: https://github.com/mtumilowicz/java-iterator-fail-types).

Null elements are not permitted (NPE).

## static methods
Assumption: `<E extends Enum<E>>`
* `EnumSet<E>	allOf​(Class<E> elementType)` - 
Creates an enum set containing all of the elements in the specified element type.
* `EnumSet<E>	complementOf​(EnumSet<E> s)` - 
Creates an enum set with the same element type as the specified enum set, initially containing all the elements of this type that are not contained in the specified set.
* `EnumSet<E>	copyOf​(Collection<E> c)` - 
Creates an enum set initialized from the specified collection.
* `EnumSet<E>	copyOf​(EnumSet<E> s)` - 
Creates an enum set with the same element type as the specified enum set, initially containing the same elements (if any).
* `EnumSet<E>	noneOf​(Class<E> elementType)` - 
Creates an empty enum set with the specified element type.
* `EnumSet<E>	of​(E e)` - 
Creates an enum set initially containing the specified element.
Up to five elements.
* `EnumSet<E>	range​(E from, E to)` - 
Creates an enum set initially containing all of the elements in the range defined by the two specified endpoints.

## summary
* All basic operations execute in constant time. They are 
likely (though not guaranteed) to be much faster than 
their `HashSet` counterparts. Even bulk operations execute 
in constant time if their argument is also an enum set.

* Please note that from static methods we obtain **mutable** set.

# project description
We provide tests for static methods:
* `allOf`
    ```
    EnumSet<DayOfWeek> dayOfWeeks = EnumSet.allOf(DayOfWeek.class);

    assertThat(dayOfWeeks.size(), is(7));
    assertThat(dayOfWeeks, contains(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY)
    );
    ```
* `complementOf`
    ```
    EnumSet<DayOfWeek> withoutWeekendDays = EnumSet.complementOf(
            EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
    );
    
    assertThat(withoutWeekendDays.size(), is(5));
    assertThat(withoutWeekendDays, contains(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY)
    );
    ```
* `range`
    ```
    EnumSet<DayOfWeek> workingDays = EnumSet.range(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);
    
    assertThat(workingDays.size(), is(5));
    assertThat(workingDays, contains(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY)
    );
    ```
* `noneOf`
    ```
    EnumSet<DayOfWeek> emptyDays = EnumSet.noneOf(DayOfWeek.class);
    
    assertTrue(emptyDays.isEmpty());
    
    emptyDays.add(DayOfWeek.SATURDAY);
    
    assertThat(emptyDays, contains(DayOfWeek.SATURDAY));
    ```