import akka.actor.{ActorSystem, Props}
import SleepActor.Call

class SleepCall(sleepTime: Long => Long, defaultSleepTime: Long) {
  private val actorSystem = ActorSystem("SleepActor")
  private val sleepActor = actorSystem.actorOf(Props(new SleepActor))

  def call[A](a: => A) =
    sleepActor ! Call(() => a, sleepTime, defaultSleepTime)
}

object SleepCall {
  def apply(defaultSleepTime: Long, sleep: Sleep*): SleepCall = {
    val sleepTime = sleep.tail.foldLeft(sleep.head.sleep)(_ andThen _)

    new SleepCall(sleepTime, defaultSleepTime)
  }

  def apply(sleep: Sleep*): SleepCall = {
    apply(0, sleep: _*)
  }
}
