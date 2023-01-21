package riot.models.summoners

case class SummonerDto(
  accountId: String,
  profileIconId: Int,
  revisionDate: Long,
  name: String,
  id: String,
  puuid: String,
  summonerLevel: Long
)
