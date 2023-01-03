import akka.actor.{Actor, ActorRef}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

class SleepActor(rsleepActor: ActorRef) extends Actor {
  implicit private val timeout: Timeout = Timeout(5.seconds)

  override def receive = {
    case "call" => loop()
  }

  def loop(): Unit = {
    val f = (rsleepActor ? "is").mapTo[Boolean]
    Await.ready(f, Duration.Inf)

    if (f.value.get.get) {
      println("true")
      rsleepActor ! "minus"
    } else {
      println("false")
      Thread.sleep(1000)
      loop()
    }
  }
}
