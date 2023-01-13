package riot

import htt.Htt.request
import sleep.SleepCall

trait SummonerCall {
  implicit protected val sleepCall: SleepCall
  protected val apiKey: String
  protected val region: Region

  def summonerByAccount(accountId: String) =
    request(makeUrl("by-account", accountId))


  def summonersByName(name: String) =
    request(makeUrl("by-name", name))

  def summonerByPuuid(puuid: String) =
    request(makeUrl("by-puuid", puuid))

  private def makeUrl(route: String, param: String) =
    s"https://${region.name}.api.riotgames.com/lol/summoner/v4/summoners/$route/$param?api_key=$apiKey"
}
