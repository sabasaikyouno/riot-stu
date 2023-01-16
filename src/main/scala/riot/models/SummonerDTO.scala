package riot.models

case class SummonerDTO(
  accountId: String,
  profileIconId: Int,
  revisionDate: Long,
  name: String,
  id: String,
  puuid: String,
  summonerLevel: Long
)
