package riot.models.matches

case class InfoDto(
  gameCreation: Long,
  gameDuration: Long,
  gameEndTimestamp: Long,
  gameId: Long,
  gameMode: String,
  gameName: String,
  gameStartTimestamp: Long,
  gameType: String,
  gameVersion: String,
  mapId: Int,
  participants: List[ParticipantDto],
  platformId: String,
  queueId: Int,
  teams: List[TeamDto],
  tournamentCode: String
)
