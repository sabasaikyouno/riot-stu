import SleepActor.Call
import akka.actor.{ActorSystem, Props}

object Sleep {

  private val actorSystem = ActorSystem("SleepActor")
  private val sleepActor = actorSystem.actorOf(Props(new SleepActor(1, 2000)))

  def call(url: String) = {
    sleepActor ! Call(url)
  }
}
