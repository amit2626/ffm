package function

import model._
import util.Implicits._

import java.time.Year
import scala.collection.immutable.SortedSet

object EarningFunctions {

  def combine(gatherers: List[Gatherer], prices: List[FruitPrice]): List[Earning] =
    gatherers
      .map { gatherer =>
        val price = prices.find(fruit => gatherer.fruitName == fruit.name && gatherer.date.isEqual(fruit.date))
        Earning(
          gathererName = gatherer.name,
          date = gatherer.date,
          fruitName = gatherer.fruitName,
          price = price.fold(0.0)(p => p.price),
          quantity = gatherer.quantity
        )
      }

  def bestEarningFruitByMonth(earnings: List[Earning]): SortedSet[EarningByMonth] = {
    val rs = earnings
      .groupBy(earning => YearMonth(earning.date))
      .map {
        case (yearMonth, v: List[Earning]) =>
          val (bestEarningFruitName, bestEarning) = v.groupBy(earning => earning.fruitName)
            .map {
              case (fruitName, v1: List[Earning]) => (fruitName, totalEarning(v1))
            }.max
          EarningByMonth(yearMonth, bestEarningFruitName, bestEarning)
      }

    SortedSet.from(rs)
  }

  def bestEarningFruitsByYear(earnings: List[Earning]): SortedSet[EarningByYear] = {
    val rs = earnings
      .groupBy(earning => Year.from(earning.date))
      .map {
        case (year, v: List[Earning]) =>
          val (bestEarningFruitName, bestEarning) = v.groupBy(earning => earning.fruitName)
            .map {
              case (fruitName, v1: List[Earning]) => (fruitName, totalEarning(v1))
            }.max
          EarningByYear(year, bestEarningFruitName, bestEarning)
      }

    SortedSet.from(rs)
  }

  def leastEarningFruitByMonth(earnings: List[Earning]): SortedSet[EarningByMonth] = {
    val rs = earnings
      .groupBy(earning => YearMonth(earning.date))
      .map {
        case (yearMonth, v: List[Earning]) =>
          val (leastEarningFruitName, leastEarning) = v.groupBy(earning => earning.fruitName)
            .map {
              case (fruitName, v1: List[Earning]) => (fruitName, totalEarning(v1))
            }.min
          EarningByMonth(yearMonth, leastEarningFruitName, leastEarning)
      }

    SortedSet.from(rs)
  }

  def leastEarningFruitsByYear(earnings: List[Earning]): SortedSet[EarningByYear] = {
    val rs = earnings
      .groupBy(earning => Year.from(earning.date))
      .map {
        case (year, v: List[Earning]) =>
          val (leastEarningFruitName, leastEarning) = v.groupBy(earning => earning.fruitName)
            .map {
              case (fruitName, v1: List[Earning]) => (fruitName, totalEarning(v1))
            }.min
          EarningByYear(year, leastEarningFruitName, leastEarning)
      }

    SortedSet.from(rs)
  }

  def bestEarningGathererByMonth(earnings: List[Earning]): SortedSet[GathererEarningByMonth] = {
    val rs = earnings
      .groupBy(earning => YearMonth(earning.date))
      .map {
        case (yearMonth, v: List[Earning]) =>
          val (bestEarningEmployeeName, bestEarning) = v.groupBy(earning => earning.gathererName)
          .map {
            case (gathererName, v1: List[Earning]) =>  (gathererName, totalEarning(v1))
          }.max
          GathererEarningByMonth(yearMonth, bestEarningEmployeeName, bestEarning)
      }

    SortedSet.from(rs)
  }


  def bestEarningGathererByYear(earnings: List[Earning]): SortedSet[GathererEarningByYear] = {
    val rs = earnings
      .groupBy(earning => Year.from(earning.date))
      .map {
        case (year, v: List[Earning]) =>
          val (bestEarningEmployeeName, bestEarning) = v.groupBy(earning => earning.gathererName)
            .map {
              case (gathererName, v1: List[Earning]) => (gathererName, totalEarning(v1))
            }.max
          GathererEarningByYear(year, bestEarningEmployeeName, bestEarning)
      }

    SortedSet.from(rs)
  }

  def leastEarningGathererByMonth(earnings: List[Earning]): SortedSet[GathererEarningByMonth] = {
    val rs = earnings
      .groupBy(earning => YearMonth(earning.date))
      .map {
        case (yearMonth, v: List[Earning]) =>
          val (leastEarningEmployeeName, leastEarning) = v.groupBy(earning => earning.gathererName)
            .map {
              case (gathererName, v1: List[Earning]) => (gathererName, totalEarning(v1))
            }.min
          GathererEarningByMonth(yearMonth, leastEarningEmployeeName, leastEarning)
      }

    SortedSet.from(rs)
  }


  def leastEarningGathererByYear(earnings: List[Earning]): SortedSet[GathererEarningByYear] = {
    val rs = earnings
      .groupBy(earning => Year.from(earning.date))
      .map {
        case (yearMonth, v: List[Earning]) =>
          val (leastEarningEmployeeName, leastEarning) = v.groupBy(earning => earning.gathererName)
            .map {
              case (gathererName, v1: List[Earning]) => (gathererName, totalEarning(v1))
            }.min
          GathererEarningByYear(yearMonth, leastEarningEmployeeName, leastEarning)
      }

    SortedSet.from(rs)
  }

  private def totalEarning(in: List[Earning]): Double =
    in.foldLeft(0.0) { case (sum, earning) => sum + earning.earning }
}
