package riot.models.matches

case class PerkStyleDTO(
  description: String,
  selections: List[PerkStyleSelectionDTO],
  style: Int
)
