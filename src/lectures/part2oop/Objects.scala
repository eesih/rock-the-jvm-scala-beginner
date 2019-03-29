package lectures.part2oop

object Objects extends App {

  //SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY (STATIC)
  object Person { //type + its only instance
    val N_EYES = 2
    def canFly: Boolean = false
    def test(value: String): String = value

    //factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    //instance level functionality
  }
  //COMPANIONS

  println(Person.N_EYES)
  println(Person.canFly)
  println(Person.test("Testing"))

  //Scala object is signleton instance

  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)


  val person1 = Person
  val person2 = Person
  println(person1 == person2)

  val bobbie = Person(mary, john)

  //Scala applications = Scala objects
  //def main (args: Arrays[String]): Unit
  
}
