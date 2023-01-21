package riot.models.matches

case class TeamDto(
  bans: List[BanDto],
  objectives: ObjectivesDto,
  teamId: Int,
  win: Boolean
)
