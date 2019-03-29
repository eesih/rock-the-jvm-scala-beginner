package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    //use the type A
    def add[B >: A](element: B): MyList[B] = ???

    /**
      * A = Cat
      * B = Animal
      */
  }

  class MyMap[K, V]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]


  //generics methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfInts = MyList.empty[Int]

  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal : Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  //animalList.add(new Dog) ??? HARD QUESTION. => we return list of animals

  //2. NO = Invariance
  class InvariantList[T]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  //Bounded types
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

  class Car
 // val newCage = new Cage(new Car) //WONT WORK



}
