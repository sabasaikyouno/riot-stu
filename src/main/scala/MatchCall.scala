import Htt.request
import sleep.SleepCall

trait MatchCall {
  implicit protected val sleepCall: SleepCall
  protected val apiKey: String
  protected val region: Region

  def matchByPuuid(
    puuid: String,
    startTimeOpt: Option[Long] = None,
    endTimeOpt: Option[Long] = None,
    queueOpt: Option[Int] = None,
    typeOpt: Option[String] = None,
    start: Int = 0,
    count: Int = 20
  ) = {
    val startTimeParam = startTimeOpt.map(startTime => s"startTime=$startTime&").getOrElse("")
    val endTimeParam = endTimeOpt.map(endTime => s"endTime=$endTime&").getOrElse("")
    val queueParam = queueOpt.map(queue => s"queue=$queue&").getOrElse("")
    val typeParam = typeOpt.map(typE => s"type=$typE&").getOrElse("")
    val param = startTimeParam + endTimeParam + queueParam + typeParam

    val url = makeUrl(s"by-puuid/$puuid/ids?${param}start=$start&count=$count&")
    request(url)
  }

  def matchById(matchId: String) = {
    val url = makeUrl(s"$matchId?")
    request(url)
  }

  def matchTimeline(matchId: String) = {
    val url = makeUrl(s"$matchId/timeline?")
    request(url)
  }

  private def makeUrl(route: String) =
    s"https://${region.route}.api.riotgames.com/lol/match/v5/matches/${route}api_key=$apiKey"
}
