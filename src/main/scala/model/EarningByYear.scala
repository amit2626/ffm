package model

import java.time.Year

case class EarningByYear(year: Year, fruitName: String, totalEarning: Double)

object EarningByYear {

  implicit val ordering: Ordering[EarningByYear] =
    (x: EarningByYear, y: EarningByYear) => x.year.compareTo(y.year)
}
