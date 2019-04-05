package lectures.part3fp

import scala.util.Random

object Options extends App{

  val myFirstOption : Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  //unsafe apis
  def unsageMethod(): String = null
  val result = Some(unsageMethod()) //WRONG

  val res = Option(unsageMethod()) //Some or none

  println(res)

  //chained method
  def backupMethod(): String = "A valid response"

  val chainedResult = Option(unsageMethod()).orElse(Option(backupMethod()))

  //DESING unsafe API's
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("Better result type")

  val betterChainedResult = betterUnsafeMethod orElse betterBackupMethod

  //functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) //UNSAFE > DO NOT USE THIS

  //map flatMap filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  //for-comprehensions
  val config: Map[String, String] = Map(
    "host" -> "1.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect() = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  val connections = for {
    conf <- config
    conn <- Connection(conf._1, conf._2)
  } yield conn

 // println(connections.map(c => c.connect()))

  //CHAINED CALLS
  config.get("host")
    .flatMap(host => config.get("port")
    .flatMap(port => Connection(host, port))
    .map(conn => conn.connect))
    .foreach(println)

  val connStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    conn <- Connection(host, port)
  } yield conn.connect()

  connStatus.foreach(println)

}
