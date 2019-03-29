package lectures.part2oop

object MethodNotations extends App{


  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is handling out with ${person.name}"
    def +(value: String): Person = new Person(s"$name ($value)", favoriteMovie)
    def unary_! : String = s"$name what the heck?!"
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def isAlive: Boolean = true
    def learns(thing: String): String = s"$name learns $thing"
    def learnsScala(): String = this learns "Scala"
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(times: Int) : String = s"$name wached $favoriteMovie $times times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") //Infix notation = operator notation (syntactic sugar)

  //"operators"

  val tom = new Person("Tom", "Fight club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  //ALL OPERATORS ARE METHODS

  //AKKA actors have ! ?


  //prefix notation
  val x = -1 //equivalent with 1.unary_-
  val y = 1.unary_-

  //unary_ prefix only works with - + ~ !
  println(!mary)
  println(mary.unary_!)

  //postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  //apply
  println(mary.apply())
  println(mary())

  println((mary + "the rockstar")())
  println((+mary).age)
  println(mary learnsScala)
  println(mary(3))



}
