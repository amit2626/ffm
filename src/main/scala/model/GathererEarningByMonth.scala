package model

case class GathererEarningByMonth(yearMonth: YearMonth, gathererName: String, totalEarning: Double)

object GathererEarningByMonth {

  implicit val ordering: Ordering[GathererEarningByMonth] =
    (x: GathererEarningByMonth, y: GathererEarningByMonth) => YearMonth.ordering.compare(x.yearMonth, y.yearMonth)
}
