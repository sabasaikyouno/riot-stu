import okhttp3.{OkHttpClient, Request}
import sleep.Sleep._
import sleep.SleepCall

import scala.concurrent.duration._

class RiotCall(apiKey: String, region: Region) {
  private val sleepCall = SleepCall((20, 1.seconds), (100, 2.minutes))
  private val client = new OkHttpClient()

  def getSummoner(name: String) = sleepCall.call {
    val url = s"https://${region.name}.api.riotgames.com/lol/summoner/v4/summoners/by-name/$name?api_key=$apiKey"
    getResponse(url)
  }

  private def getResponse(url: String) = {
    val request = new Request.Builder().url(url).build()

    client
      .newCall(request)
      .execute()
      .body()
      .string()
  }
}
