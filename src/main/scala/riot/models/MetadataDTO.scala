package riot.models

case class MetadataDTO(
  dataVersion: String,
  matchId: String,
  participants: List[String]
)
