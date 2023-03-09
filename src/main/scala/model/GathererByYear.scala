package model

import java.time.Year

case class GathererByYear(year: Year, name: String, totalGatheredQuantity: Double)

object GathererByYear {

  implicit val ordering: Ordering[GathererByYear] = (x: GathererByYear, y: GathererByYear) => x.year.compareTo(y.year)
}
