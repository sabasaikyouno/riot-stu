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
  def apply(req: Int, sec: Long): SleepCall = new SleepCall(req, sec)

  def call[A](a: => A)(implicit sleepTime: Long => Long, defaultSleepTime: Long = 0) = {
    Thread.sleep(sleepTime(defaultSleepTime))
    a
  }

  implicit def sleepImp(sleepCall: SleepCall) = sleepCall.sleep
}
