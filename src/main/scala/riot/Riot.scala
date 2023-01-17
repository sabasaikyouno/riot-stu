package riot

import sleep.SleepCall
import io.circe.generic.auto._
import io.circe.parser._
import riot.models.{MatchDTO, MatchTimelineDTO, SummonerDTO}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

class Riot(protected val apiKey: String, protected val region: Region) extends SummonersCall with MatchesCall {
  protected val sleepCall: SleepCall = SleepCall((20, 1.seconds), (100, 2.minutes))

  def matchesByPuuid(puuid: String) =
    matchesByPuuidCall(puuid).map(toDTO[List[String]])

  def matchesById(matchId: String) =
    matchesByIdCall(matchId).map(toDTO[MatchDTO])

  def matchesTimeline(matchId: String) =
    matchesTimelineCall(matchId).map(toDTO[MatchTimelineDTO])

  def summonersByAccount(accountId: String) =
    summonersByAccountCall(accountId).map(toDTO[SummonerDTO])

  def summonersByName(name: String) =
    summonersByNameCall(name).map(toDTO[SummonerDTO])

  def summonersByPuuid(puuid: String) =
    summonersByPuuidCall(puuid).map(toDTO[SummonerDTO])

  def summonersByMe(authorization: String) =
    summonersByMeCall(authorization).map(toDTO[SummonerDTO])

  def summonersById(summonerId: String) =
    summonersByIdCall(summonerId).map(toDTO[SummonerDTO])

  private def toDTO[A](body: String) =
    parse(body)
      .flatMap(_.as[A])
      .toOption
      .get
}
