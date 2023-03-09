package util

import model.YearMonth

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, Year}
import scala.util.Try

object DateUtil {

  private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def parse(in: String): Try[LocalDate] = Try(LocalDate.parse(in, formatter))

  def compare(x: LocalDate, y: LocalDate): Int = x.compareTo(y)

  def compare(x: Year, y: Year): Int = x.compareTo(y)

  def compare(x: YearMonth, y: YearMonth): Int =
    LocalDate.of(x.year.getValue, x.month, 1)
      .compareTo(LocalDate.of(y.year.getValue, y.month, 1))
}
