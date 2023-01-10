import sleep.Sleep._
import sleep.SleepCall

import scala.concurrent.duration._

class RiotCall(apiKey: String) {
  private val sleepCall = SleepCall((20, 1.seconds), (100, 2.minutes))

  def getSummoner(name: String) = {
    val url = s"https://jp1.api.riotgames.com/lol/summoner/v4/summoners/by-name/$name?api_key=$apiKey"
    sleepCall.call(url)
  }
}
