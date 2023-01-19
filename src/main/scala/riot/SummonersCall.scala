package riot

import htt.Htt.request
import sleep.SleepCall

trait SummonersCall {
  protected implicit val sleepCall: SleepCall
  protected val apiKey: String
  protected val region: Region

  protected def summonersByAccountCall(accountId: String) =
    request(makeUrl(s"by-account/$accountId"))

  protected def summonersByNameCall(name: String) =
    request(makeUrl(s"by-name/$name"))

  protected def summonersByPuuidCall(puuid: String) =
    request(makeUrl(s"by-puuid/$puuid"))

  protected def summonersByMeCall(authorization: String) =
    request(makeUrl("me"), "Authorization" -> authorization)

  protected def summonersByIdCall(summonerId: String) =
    request(makeUrl(summonerId))

  private def makeUrl(route: String) =
    s"https://${region.name}.api.riotgames.com/lol/summoner/v4/summoners/$route?api_key=$apiKey"
}
