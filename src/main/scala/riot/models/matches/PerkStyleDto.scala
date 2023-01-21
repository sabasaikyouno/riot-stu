package riot.models.matches

case class PerkStyleDto(
  description: String,
  selections: List[PerkStyleSelectionDto],
  style: Int
)
