import SleepActor.Call
import akka.actor.Actor
import com.github.nscala_time.time.Imports.DateTime

object SleepActor {
  case class Call(url: String)
}

class SleepActor(req: Int, sec: Long) extends Actor {
  private var canReqCount = req
  private var nextResetTime = 0L

  def receive = {
    case Call(url) =>
      call()
      println(url)
  }

  private def call() =
    if (canReqCount == req) {
      nextResetTime = getNextResetTime
      canReqCount -= 1
    } else if (canReqCount <= 0) {
      Thread.sleep((nextResetTime - DateTime.now().getMillis).max(0))
      nextResetTime = getNextResetTime
      canReqCount = req - 1
    } else {
      canReqCount -= 1
    }

  private def getNextResetTime = DateTime.now().getMillis + sec
}
