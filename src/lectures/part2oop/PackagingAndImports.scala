package lectures.part2oop

import playground.{Cinderella, PrinceCharming => Prince}

object PackagingAndImports extends App {

  val writer = new Writer("Eero", "RockTheJVM", 39)

  val cinderella = new Cinderella
  val princess = new playground.Cinderella //fully qualified name

  //package objects

  sayHello

  println(SPEED_OF_LIGHT)

  val prince = new Prince
  println(prince.getClass)

  //default imports
  //java.lang
  //scala Int, Function etc
  //scala.predef

}
