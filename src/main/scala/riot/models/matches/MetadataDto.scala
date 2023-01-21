package riot.models.matches

case class MetadataDto(
  dataVersion: String,
  matchId: String,
  participants: List[String]
)
