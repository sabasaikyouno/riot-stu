import sleep.Sleep._
import sleep.SleepCall

import scala.concurrent.duration._

class RiotCall(protected val apiKey: String, protected val region: Region) extends SummonerCall with MatchCall {
  protected val sleepCall = SleepCall((20, 1.seconds), (100, 2.minutes))

}

object RiotCall {
  def apply(apiKey: String, region: Region): RiotCall = new RiotCall(apiKey, region)
}
