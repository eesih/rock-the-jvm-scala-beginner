package lectures.part3fp

object WhatsAFunction extends App {

  val doubler = new MyFunction[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  }

  println(doubler(3))

  //function types = Function1[A,B]
  val stringConverter = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringConverter("5"))

  //syntactic sugar Function2[Int, Int, Int]  >> (int, int => int)
  val adder:((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  println(adder(10, 4))

  //Function types Function[A, B, R] === (A, B) => R

  //ALL Scala FUNCTIONS ARE OBJECTS!!

  val concanater = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + " " + b
  }

  println(concanater("Hello", "World"))


  val superAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = (y: Int) => x + y
  }

  val specialAdder = (x: Int) => (y: Int) =>  x + y

 // val test2 = (x) => Int
  val test2 = (x: Int) => (y: Int) => x + y

  println(test2(23) (33))

  val test3 = (x: Int) => (y: Int) => (e: Int) => x + y + e

  println(test3(23) (33) (11))
  //println(test3.)

//  val test = new Function1[Int, Function1[Int, Function1[Int, Int]]] {
//     def apply(x: Int): Int => Int
//                              => Int = (y: Int) => Int
//                                  => Int = (e: Int) => x + y + e
//  }

  val adder3 = superAdder(11)

  println(adder3(2))

  println(superAdder(1)(6)) //curried method

  //println(test(1)(2)(3))

}



trait MyFunction[A, B] {
  def apply(elem: A): B
}
