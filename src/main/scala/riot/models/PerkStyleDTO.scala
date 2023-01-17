package riot.models

case class PerkStyleDTO(
  description: String,
  selections: List[PerkStyleSelectionDTO],
  style: Int
)
