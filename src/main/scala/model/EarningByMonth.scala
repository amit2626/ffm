package model

case class EarningByMonth(yearMonth: YearMonth, fruitName: String, totalEarning: Double)

object EarningByMonth {

  implicit val ordering: Ordering[EarningByMonth] =
    (x: EarningByMonth, y: EarningByMonth) => YearMonth.ordering.compare(x.yearMonth, y.yearMonth)
}
