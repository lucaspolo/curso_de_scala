package lectures.part3fp

import scala.util.Random

object Options extends App {
  val myFistOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFistOption)

  // unsafe APIs
  def unsafeMethod(): String = null

  // val result = Some(unsafeMethod()) // NEVER do this
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  println(chainedResult)

  // DESIGN unsafe API
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  println(betterChainedResult)

  // function on Options
  println(myFistOption.isEmpty)
  println(myFistOption.get) // UNSAFE - DONT USE

  // map, flatMap, filter
  println(myFistOption.map(_ * 2))
  println(myFistOption.filter(x => x > 10))
  println(myFistOption.flatMap(x => Option(x * 10)))

  // for-comprehensions

  /*
    Exercise.
   */

  val config: Map[String, String] = Map (
    // fetched from elsewhere
    "host" -> "176.45.6.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to estabilish a connection, if so - print the connect method

  val host = config.get("host")
  val port = config.get("port")

  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)
    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))

  /*
    if (c != null)
      return c.connect
    return null
   */
  val connectionStatus = connection.map(c => c.connect)

  println(connection)
  print("1: ")
  connectionStatus.foreach(println)

  // chained calls
  print("2: ")
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
        .map(connection => connection.connect)
    )
    .foreach(println)

  // for-comprehensions
  print("3: ")
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}
