package model

import java.time.Year

case class GathererEarningByYear(year: Year, gathererName: String, totalEarning: Double)

object GathererEarningByYear {

  implicit val ordering: Ordering[GathererEarningByYear] =
    (x: GathererEarningByYear, y: GathererEarningByYear) => x.year.compareTo(y.year)
}
