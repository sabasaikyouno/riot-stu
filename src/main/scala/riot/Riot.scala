package riot

import io.circe.Decoder.Result
import io.circe.Json
import sleep.SleepCall
import io.circe.generic.auto._
import io.circe.parser._
import riot.models.{MatchDTO, MatchTimelineDTO, SummonerDTO}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

class Riot(
  protected val apiKey: String,
  protected val region: Region,
  protected val sleepCall: SleepCall
) extends SummonersCall with MatchesCall {

  def matchesByPuuid(puuid: String) =
    matchesByPuuidCall(puuid).map(toDTO(_.as[List[String]]))

  def matchesById(matchId: String) =
    matchesByIdCall(matchId).map(toDTO(_.as[MatchDTO]))

  def matchesTimeline(matchId: String) =
    matchesTimelineCall(matchId).map(toDTO(_.as[MatchTimelineDTO]))

  def summonersByAccount(accountId: String) =
    summonersByAccountCall(accountId).map(toDTO(_.as[SummonerDTO]))

  def summonersByName(name: String) =
    summonersByNameCall(name).map(toDTO(_.as[SummonerDTO]))

  def summonersByPuuid(puuid: String) =
    summonersByPuuidCall(puuid).map(toDTO(_.as[SummonerDTO]))

  def summonersByMe(authorization: String) =
    summonersByMeCall(authorization).map(toDTO(_.as[SummonerDTO]))

  def summonersById(summonerId: String) =
    summonersByIdCall(summonerId).map(toDTO(_.as[SummonerDTO]))

  private def toDTO[A](f: Json => Result[A])(body: String) =
    parse(body)
      .flatMap(f)
      .toOption
      .get

  def changeApiKey(newApiKey: String) = Riot(newApiKey, region, sleepCall)
}

object Riot {
  def apply(apiKey: String, region: Region, sleepCall: SleepCall = SleepCall((20, 1.seconds), (100, 2.minutes))): Riot = new Riot(apiKey, region, sleepCall)
}
