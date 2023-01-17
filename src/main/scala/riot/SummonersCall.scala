package riot

import htt.Htt.request
import sleep.SleepCall

trait SummonersCall {
  implicit protected val sleepCall: SleepCall
  protected val apiKey: String
  protected val region: Region

  def summonersByAccountCall(accountId: String) =
    request(makeUrl(s"by-account/$accountId"))


  def summonersByNameCall(name: String) =
    request(makeUrl(s"by-name/$name"))

  def summonersByPuuidCall(puuid: String) =
    request(makeUrl(s"by-puuid/$puuid"))

  def summonersByMeCall(authorization: String) =
    request(makeUrl("me"), "Authorization" -> authorization)

  def summonersByIdCall(summonerId: String) =
    request(makeUrl(summonerId))

  private def makeUrl(route: String) =
    s"https://${region.name}.api.riotgames.com/lol/summoner/v4/summoners/$route?api_key=$apiKey"
}
