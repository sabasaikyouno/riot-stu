sealed abstract class Region(val name: String, val route: String)
object Region {
  case object BR1 extends Region("br1", "americas")
  case object EUN1 extends Region("eun1", "europe")
  case object EUW1 extends Region("euw1", "europe")
  case object JP1 extends Region("jp1", "asia")
  case object KR extends Region("kr", "asia")
  case object LA1 extends Region("la1", "americas")
  case object LA2 extends Region("la2", "americas")
  case object NA1 extends Region("na1", "americas")
  case object OC1 extends Region("oc1", "sea")
  case object TR1 extends Region("tr1", "europe")
  case object RU extends Region("ru", "europe")
  case object PH2 extends Region("ph2", "sea")
  case object SG2 extends Region("sg2", "sea")
  case object TH2 extends Region("th2", "sea")
  case object TW2 extends Region("tw2", "sea")
  case object VN2 extends Region("vn2", "sea")

  implicit def toRegion(name: String): Region = name match {
    case "br1" => BR1
    case "eun1" => EUN1
    case "euw1" => EUW1
    case "jp1" => JP1
    case "kr" => KR
    case "la1" => LA1
    case "la2" => LA2
    case "na1" => NA1
    case "oc1" => OC1
    case "tr1" => TR1
    case "ru" => RU
    case "ph2" => PH2
    case "sg2" => SG2
    case "th2" => TH2
    case "tw2" => TW2
    case "vn2" => VN2
  }
}
