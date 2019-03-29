package lectures.part2oop

object OoBasics extends App {

  val person = new Person("David", 23)
  println(person.age)
  println(person.name)
  person.name = "John"
  println(person.name)
  println(person.x)
  person.greet("Eero")
  person.greet()

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print

}

class Person(var name: String, val age: Int = 0) {
  val x = 2

  println(1 + 3)

  def greet(name: String) : Unit = println(s"${this.name} says, Hi, $name")

  def greet(): Unit = println(s"Hi, I'm $name")

  //multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")

}

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName = s"$firstName $surname"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge = yearOfRelease - author.year
  def isWrittenBy(other: Writer) = author == other
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int = 0) {

  def inc = {
    println("incrementing")
    new Counter(count + 1)
  }

  def dec = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n-1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    dec.dec(n-1)
  }

  def print = println(count)
}



//class parameters are not fields
