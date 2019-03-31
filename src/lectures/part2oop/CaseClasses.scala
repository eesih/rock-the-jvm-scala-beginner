package lectures.part2oop

object CaseClasses extends App {

  case class Person(name: String, age: Int) {

  }

  val jim = Person("Jim", 33)
  val anotherJim = Person("Jim", 33)
  println(jim.age)
  println(jim.toString)
  println(jim)

  println( jim == anotherJim )

  val jim2 = jim.copy(age = 44)

  println(jim2)

  val thePerson = Person

  case object UnitedKingdom {
    def name: String = "hello"
  }
}
