package model

import java.time.{Month, Year}

case class GathererByMonth(yearMonth: YearMonth, name: String, totalGatheredQuantity: Double) {
  def year: Year = yearMonth.year

  def month: Month = yearMonth.month
}

object GathererByMonth {

  implicit val ordering: Ordering[GathererByMonth] =
    (x: GathererByMonth, y: GathererByMonth) => YearMonth.ordering.compare(x.yearMonth, y.yearMonth)
}
