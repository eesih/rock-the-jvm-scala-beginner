package lectures.part2oop

object InheritanceAndTraits extends App {

  //single class inheritance
  sealed class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")
  }

  class Cat extends Animal {
    def cruch() = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.cruch

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name:String, age: Int, idCards: String) extends Person(name, age)

  //overriding
  class Dog( override val creatureType: String) extends Animal {
//    override val creatureType: String = "domestic"
    override def eat = {
      super.eat
      println("crunch, crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  //type substitution
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  //super

  //preventing overrides
  // 1. use final on member
  // 2. use final on class
  // 3. seal the class = extends classes in this file, not another files
}
