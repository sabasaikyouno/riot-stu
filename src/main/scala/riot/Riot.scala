package riot

import io.circe.Decoder
import sleep.SleepCall
import io.circe.generic.auto._
import io.circe.parser._
import riot.models.summoners.SummonerDto
import riot.models.matches.{MatchDto, MatchTimelineDto}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

class Riot(
  protected val apiKey: String,
  protected val region: Region,
  protected val sleepCall: SleepCall
) extends SummonersCall with MatchesCall {

  def matchesByPuuid(
    puuid: String,
    startTimeOpt: Option[Long] = None,
    endTimeOpt: Option[Long] = None,
    queueOpt: Option[Int] = None,
    typeOpt: Option[String] = None,
    start: Int = 0,
    count: Int = 20
  ) =
    matchesByPuuidCall(puuid, startTimeOpt, endTimeOpt, queueOpt, typeOpt, start, count).map(toDto[List[String]])

  def matchesById(matchId: String) =
    matchesByIdCall(matchId).map(toDto[MatchDto])

  def matchesTimeline(matchId: String) =
    matchesTimelineCall(matchId).map(toDto[MatchTimelineDto])

  def summonersByAccount(accountId: String) =
    summonersByAccountCall(accountId).map(toDto[SummonerDto])

  def summonersByName(name: String) =
    summonersByNameCall(name).map(toDto[SummonerDto])

  def summonersByPuuid(puuid: String) =
    summonersByPuuidCall(puuid).map(toDto[SummonerDto])

  def summonersByMe(authorization: String) =
    summonersByMeCall(authorization).map(toDto[SummonerDto])

  def summonersById(summonerId: String) =
    summonersByIdCall(summonerId).map(toDto[SummonerDto])

  private def toDto[A](body: String)(implicit decoder: Decoder[A]) =
    decode(body)
      .toOption
      .get

  def changeApiKey(newApiKey: String) = Riot(newApiKey, region, sleepCall)
}

object Riot {
  def apply(apiKey: String, region: Region, sleepCall: SleepCall = SleepCall((20, 1.seconds), (100, 2.minutes))): Riot = new Riot(apiKey, region, sleepCall)
}