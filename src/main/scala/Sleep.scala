import akka.actor.{ActorSystem, Props}

import scala.concurrent.duration.DurationInt

object Sleep {

  import actorSystem.dispatcher

  private val actorSystem = ActorSystem("SleepActor")
  private val rsleepActor = actorSystem.actorOf(Props(new RequestSleepActor(0.5)))
  private val sleepActor = actorSystem.actorOf(Props(new SleepActor(rsleepActor)))

  def start() = {
    actorSystem.scheduler.schedule(0.milliseconds, 1.seconds, rsleepActor, "plus")
  }

  def call() = {
    sleepActor ! "call"
  }
}
