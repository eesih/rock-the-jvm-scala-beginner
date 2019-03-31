package excercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
  def map[B](myTransformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](myTransformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(myPredicate: MyPredicate[A]): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing]  {
   def head: Nothing = throw new NoSuchElementException
   def tail: MyList[Nothing] = throw new NoSuchElementException
   def isEmpty: Boolean = true
   def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
   def printElements: String = ""
   def map[B](myTransformer: MyTransformer[Nothing, B]): MyList[B] = Empty
   def flatMap[B](myTransformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
   def filter(myPredicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
   def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if (tail.isEmpty) "" + h
    else h + " " + t.printElements
  def filter(predicate: MyPredicate[A]) : MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else (t.filter(predicate))

  /*
    [1,2,3].map(n -> n * 2)
    = new Cons(1, [2,3].map(n -> n * 2))
    = new Cons(1, new Cons(2, [3].map(n -> n * 2)))
    = new Cons(1, new Cons(2, new Cons(3, Empty.map(n -> n * 2)))
    = new Cons(1, new Cons(2, new Cons(3, Empty)))
   */
  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  /*
    * [1,2] ++ [3,4,5]
    * = new Cons(1, [2] ++ [3,4,5]
    * = new Cons(1, new Cons(2, Empty ++[3,4,5]))
    * = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5))
    */
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons[B](h, t ++ list)

  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]

   */
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ tail.flatMap(transformer)
}

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}

object Test extends App {
  val listOfInts = Cons(1, new Cons(2, Cons(3, Empty)))
  val anotherListOfInts = Cons(4, Cons(5, Cons(6, Empty)))
  println(listOfInts.tail.head)
  println(listOfInts.add(4).head)
  println(listOfInts.isEmpty)
  println(listOfInts.toString)
  val listOfStrings = new Cons("Hello", new Cons("Scala", Empty))
  println(listOfStrings.toString)

  println(listOfInts.map((element: Int) => element * 2).toString)
  println(listOfStrings.map((element: String) => element.toUpperCase).toString)

  println(listOfInts.filter((i) => i < 2).toString)

  println((listOfInts ++ anotherListOfInts).toString)

  //println(listOfInts.flatMap((elem) => new Cons(elem, new Cons(elem + 1, Empty))).toString)
  println(listOfInts.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem +1, Empty))
  }).toString)

  //println(listOfInts.flatMap((elem: Int) => new Cons(elem, new Cons(elem + 1, Empty))).toString)
}
