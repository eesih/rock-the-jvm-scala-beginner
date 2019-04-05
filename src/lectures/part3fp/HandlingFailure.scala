package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod: String = throw new RuntimeException("No String for you buster")

  //Try objects via the apply method
  val potentialFailure = Try(unsafeMethod)
  println(potentialFailure)

  val anotherPotentialFailure = Try {
    //code that might throw
  }

  //utilities
  println(potentialFailure.isSuccess)

  //orElse
  def backupMethod: String = "A valid response"

  val fallbackTry = Try(unsafeMethod).orElse(Try(backupMethod))
  println(fallbackTry)

  //if you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("yes, this is what I wanted")

  println( betterUnsafeMethod orElse betterBackupMethod )

  //map flatMap filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 12)))
  println(aSuccess.filter(_ > 10))

  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object Httpservice {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String) : Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port: String) : Try[Connection] = Try(getConnection(host, port))
  }

  val possibleConnection = Httpservice.getSafeConnection(hostname, port)
  val possibleHtml = possibleConnection.flatMap(conn => conn.getSafe("/home"))
  possibleHtml.foreach(renderHTML)

  val okRes = for {
    conn <- Try(Httpservice.getConnection(hostname, port))
    ok <- Try(conn.get("myurl"))
  } yield ok

  println(okRes)

  //shorthand version
  Httpservice.getSafeConnection(hostname, port)
    .flatMap(conn => conn.getSafe("/home"))
    .foreach(renderHTML)

  //for-comprehension version
   for {
     conn <- Httpservice.getSafeConnection(hostname, port)
     html <- conn.getSafe("/home")
   } renderHTML(html)



}
