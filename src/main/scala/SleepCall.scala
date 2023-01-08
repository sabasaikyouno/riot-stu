import SleepActor.Call
import akka.actor.{ActorSystem, Props}
import com.github.nscala_time.time.Imports.DateTime

class SleepCall(req: Int, sec: Long) {
  private var canReqCount = req
  private var nextResetTime = 0L

  def sleep: Long => Long = defaultSleepTime => {
    val sleepTime = if (canReqCount <= 0) (nextResetTime - DateTime.now().getMillis).max(0).max(defaultSleepTime) else 0

    if (canReqCount == req) {
      nextResetTime = getNextResetTime
      canReqCount -= 1
    } else if (canReqCount <= 0) {
      nextResetTime = getNextResetTime
      canReqCount = req - 1
    } else {
      canReqCount -= 1
    }

    sleepTime
  }

  private def getNextResetTime = DateTime.now().getMillis + sec
}

object SleepCall {
  private val actorSystem = ActorSystem("SleepActor")
  private val sleepActor = actorSystem.actorOf(Props(new SleepActor))

  def apply(req: Int, sec: Long): SleepCall = new SleepCall(req, sec)

  def call[A](a: => A)(implicit sleepTime: Long => Long, defaultSleepTime: Long = 0) = {
    sleepActor ! Call(() => a, sleepTime, defaultSleepTime)
  }

  implicit def sleepImp(sleepCall: SleepCall) = sleepCall.sleep
}
