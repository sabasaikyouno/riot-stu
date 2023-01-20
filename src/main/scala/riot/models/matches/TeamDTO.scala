package riot.models.matches

case class TeamDTO(
  bans: List[BanDTO],
  objectives: ObjectivesDTO,
  teamId: Int,
  win: Boolean
)
