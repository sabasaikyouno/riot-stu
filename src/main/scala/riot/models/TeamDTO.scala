package riot.models

case class TeamDTO(
  bans: List[BanDTO],
  objectives: ObjectivesDTO,
  teamId: Int,
  win: Boolean
)
