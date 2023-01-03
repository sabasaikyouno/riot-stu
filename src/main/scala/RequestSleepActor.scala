import akka.actor.Actor

class RequestSleepActor(reqPerSec: Double) extends Actor {
  private var rk = reqPerSec

  def receive = {
    case "is" => sender ! rk >= 1
    case "plus" => rk = (rk + reqPerSec).min(reqPerSec.max(1))
    case "minus" => rk -= 1
  }
}
