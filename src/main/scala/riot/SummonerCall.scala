package riot

import htt.Htt.request
import sleep.SleepCall

trait SummonerCall {
  implicit protected val sleepCall: SleepCall
  protected val apiKey: String
  protected val region: Region

  def summonerByAccount(accountId: String) =
    request(makeUrl(s"by-account/$accountId"))


  def summonersByName(name: String) =
    request(makeUrl(s"by-name/$name"))

  def summonerByPuuid(puuid: String) =
    request(makeUrl(s"by-puuid/$puuid"))

  def summonerByMe(authorization: String) =
    request(makeUrl("me"), ("Authorization", authorization))

  def summonerById(summonerId: String) =
    request(makeUrl(summonerId))

  private def makeUrl(route: String) =
    s"https://${region.name}.api.riotgames.com/lol/summoner/v4/summoners/$route?api_key=$apiKey"
}
