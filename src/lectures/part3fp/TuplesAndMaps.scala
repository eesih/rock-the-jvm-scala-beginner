package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  //tuples = finite ordered "lists"
  val aTuple = (2, "hello") //Tuple[Int, String] - (Int, String)

  println(aTuple._1) //2
  println(aTuple.copy(_2 = "goodbye java"))
  println(aTuple.swap) //("hello", 2)

  //maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map("Jim" -> 555, "Eero" -> 289).withDefaultValue(-1)
  //a -> b is sugar for (a, b)
  println(phonebook)

  //map ops
  println(phonebook.contains("Eero"))
  println(phonebook("Eero"))
  println(phonebook("Mary"))

  //add a pairing
  val newPairing = "Mary" -> 221
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  //funtionals on maps
  //map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  //filterKeys
  println(phonebook.filterKeys(x => x.startsWith("J")))

  //mapValues
  println(phonebook.mapValues(number => "0245-" + number))

  //conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim", "Murry")
  println(names.groupBy(name => name.charAt(0)))

  val map = Map("JIM" -> 812, "Jim" -> 433)
  println(map.map(pair => pair._1.toLowerCase -> pair._2))


  val fb: Map[String, Set[String]] = Map()

  def add(network: Map[String, Set[String]],  person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }


  def addFriend(network: Map[String, Set[String]],  a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unFriend(network: Map[String, Set[String]],  a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]],  person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unFriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)

  println(addFriend(network, "Bob", "Mary"))
  println(unFriend(addFriend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(addFriend(network, "Bob", "Mary"), "Bob"))

  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = addFriend(people, "Jim", "Bob")
  val testNet = addFriend(jimBob, "Bob", "Mary")
  println(testNet)

  println(numberOfFriends(testNet, "Bob"))
  println(getPersonWithMostFriend(testNet))
  println(nPeopleWithNoFriends(testNet))

  def numberOfFriends(network: Map[String, Set[String]],  person: String): Int =
    if(!network.contains(person)) 0
    else network(person).size

  def getPersonWithMostFriend(network: Map[String, Set[String]]): String =
    network.maxBy(pairing => pairing._2.size)._1

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)
    //network.count(pair => pair._2.isEmpty)
    //network.filter(pair => pair._2.isEmpty).size
    //network.filterKeys(k => network(k).isEmpty).size

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Bob", "Mary"))
}
