package riot

import htt.Htt.request
import sleep.SleepCall

trait MatchesCall {
  protected implicit val sleepCall: SleepCall
  protected val apiKey: String
  protected val region: Region

  protected def matchesByPuuidCall(
    puuid: String,
    startTimeOpt: Option[Long],
    endTimeOpt: Option[Long],
    queueOpt: Option[Int],
    typeOpt: Option[String],
    start: Int,
    count: Int
  ) = {
    val startTimeParam = startTimeOpt.map(startTime => s"startTime=$startTime&").getOrElse("")
    val endTimeParam = endTimeOpt.map(endTime => s"endTime=$endTime&").getOrElse("")
    val queueParam = queueOpt.map(queue => s"queue=$queue&").getOrElse("")
    val typeParam = typeOpt.map(typE => s"type=$typE&").getOrElse("")
    val param = startTimeParam + endTimeParam + queueParam + typeParam
    val url = makeUrl(s"by-puuid/$puuid/ids?${param}start=$start&count=$count&")

    request(url)
  }

  protected def matchesByIdCall(matchId: String) =
    request(makeUrl(s"$matchId?"))

  protected def matchesTimelineCall(matchId: String) =
    request(makeUrl(s"$matchId/timeline?"))

  private def makeUrl(route: String) =
    s"https://${region.route}.api.riotgames.com/lol/match/v5/matches/${route}api_key=$apiKey"
}
