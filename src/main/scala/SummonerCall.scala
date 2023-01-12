import Htt.request
import sleep.SleepCall

trait SummonerCall {
  protected val sleepCall: SleepCall
  protected val apiKey: String
  protected val region: Region

  def summonersByName(name: String) = sleepCall.call {
    val url = s"https://${region.name}.api.riotgames.com/lol/summoner/v4/summoners/by-name/$name?api_key=$apiKey"
    request(url)
  }
}
