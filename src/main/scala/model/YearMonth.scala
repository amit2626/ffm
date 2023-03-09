package model

import java.time.{LocalDate, Month, Year}

case class YearMonth(year: Year, month: Month)

object YearMonth {

  def apply(date: LocalDate): YearMonth = YearMonth(Year.from(date), date.getMonth)

  implicit val ordering: Ordering[YearMonth] =
    (x: YearMonth, y: YearMonth) =>
      LocalDate.of(x.year.getValue, x.month, 1)
        .compareTo(LocalDate.of(y.year.getValue, y.month, 1))
}
