import SleepActor.Call
import akka.actor.Actor

object SleepActor {
  case class Call[A](a: () => A, sleepTime: Long => Long, defaultSleepTime: Long = 0)
}

class SleepActor extends Actor {

  def receive = {
    case Call(a, s, d) =>
      Thread.sleep(s(d))
      a()
  }
}
