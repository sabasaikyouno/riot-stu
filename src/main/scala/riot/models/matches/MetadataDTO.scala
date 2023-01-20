package riot.models.matches

case class MetadataDTO(
  dataVersion: String,
  matchId: String,
  participants: List[String]
)
