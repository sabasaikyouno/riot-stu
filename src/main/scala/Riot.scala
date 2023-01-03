trait Riot {
  def matchList(puuid: String): List[String]

  def matchFilter(list: List[String], puuidFilter: String): List[String]
}
