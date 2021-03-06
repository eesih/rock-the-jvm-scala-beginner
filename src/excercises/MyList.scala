package excercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  //higher-order functions
  def map[B](myTransformer: A => B): MyList[B]
  def flatMap[B](myTransformer: A => MyList[B]): MyList[B]
  def filter(myPredicate: A => Boolean): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]

  //hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(f: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""
  def map[B](myTransformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](myTransformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(myPredicate: Nothing => Boolean): MyList[Nothing] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) = Empty
  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty

  def fold[B](start: B)(f: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if (tail.isEmpty) "" + h
    else h + " " + t.printElements

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else (t.filter(predicate))

  /*
    [1,2,3].map(n -> n * 2)
    = new Cons(1, [2,3].map(n -> n * 2))
    = new Cons(1, new Cons(2, [3].map(n -> n * 2)))
    = new Cons(1, new Cons(2, new Cons(3, Empty.map(n -> n * 2)))
    = new Cons(1, new Cons(2, new Cons(3, Empty)))
   */
  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

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
  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ tail.flatMap(transformer)

  //hofs
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {

    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)


}

object Test extends App {
  val listOfInts = Cons(1, new Cons(2, Cons(3, Empty)))
  val anotherListOfInts = Cons(4, Cons(5, Cons(6, Empty)))
  println(listOfInts.tail.head)
  println(listOfInts.add(4).head)
  println(listOfInts.isEmpty)
  println(listOfInts.toString)
  val listOfStrings = new Cons("Python", new Cons("Scala", new Cons("Java", Empty)))
  println(listOfStrings.toString)

  println(listOfInts.map(_ * 2).toString)
  println(listOfStrings.map(_.toUpperCase).toString)

  println(listOfInts.filter((i) => i < 2).toString)

  println((listOfInts ++ anotherListOfInts).toString)

  println(listOfInts.flatMap((elem) => new Cons(elem, new Cons(elem + 1, Empty))).toString)

  println(listOfInts.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }).toString)

  println("----------------")
  println(listOfInts.toString)
  listOfInts.foreach(println)
  println(listOfInts.sort((x, y) => y - x))

  println(listOfInts.zipWith(Cons(4, Cons(5, Cons(6, Empty))), (x: Int, y: Int) => x * y))
  println(listOfInts.zipWith[String, String](listOfStrings, _ + "-" + _))
  println(listOfInts.fold(0)( _ + _))

  //println(listOfInts.flatMap((elem: Int) => new Cons(elem, new Cons(elem + 1, Empty))).toString)


  val combinations = for {
    i <- listOfInts
    s <- listOfStrings
  } yield i + "-" + s

  println(combinations)
}
