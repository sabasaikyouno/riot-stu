package riot.models.matches

case class InfoDTO(
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
  participants: List[ParticipantDTO],
  platformId: String,
  queueId: Int,
  teams: List[TeamDTO],
  tournamentCode: String
)
