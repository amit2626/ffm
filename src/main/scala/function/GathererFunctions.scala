package function

import model._
import util.Implicits._

import java.time.Year
import scala.collection.immutable.{SortedMap, SortedSet}

object GathererFunctions {

  def bestGatherersByMonth(gatherers: List[Gatherer]): SortedSet[GathererByMonth] = {
    val result = gatherers
      .groupBy(gatherer => YearMonth(gatherer.date))
      .map {
        case (yearMonth, v: List[Gatherer]) =>
          val (bestGathererName, bestGatheredQuantity) = v.groupBy(gatherer => gatherer.name)
            .map {
              case (gathererName, v1: List[Gatherer]) => (gathererName, totalGatheredQuantity(v1))
            }.max
          GathererByMonth(yearMonth, bestGathererName, bestGatheredQuantity)
      }

    SortedSet.from(result)
  }

  def bestGatherersByYear(gatherers: List[Gatherer]): SortedSet[GathererByYear] = {
    val result = gatherers
      .groupBy(gatherer => Year.from(gatherer.date))
      .map {
        case (year, v: List[Gatherer]) =>
          val (bestGathererName, bestGatheredQuantity) = v.groupBy(gatherer => gatherer.name)
            .map {
              case (gathererName, v1: List[Gatherer]) => (gathererName, totalGatheredQuantity(v1))
            }.max
          GathererByYear(year, bestGathererName, bestGatheredQuantity)
      }

    SortedSet.from(result)
  }

  def bestGatherersOfSpecificFruitByMonth(gatherers: List[Gatherer]): SortedMap[YearMonth, SortedSet[SpecificFruitGatherer]] = {
    val result: Map[YearMonth, SortedSet[SpecificFruitGatherer]] = gatherers
      .groupBy(gatherer => YearMonth(gatherer.date))
      .map {
        case (yearMonth, v: List[Gatherer]) =>
          val rs = v.groupBy(gatherer => gatherer.fruitName)
            .map {
              case (fruitName, v1: List[Gatherer]) =>
                val (bestGathererName, bestGatheredQuantity) = v1.groupBy(gatherer => gatherer.name)
                  .map {
                    case (gathererName, v2: List[Gatherer]) => (gathererName, totalGatheredQuantity(v2))
                  }.max
                SpecificFruitGatherer(bestGathererName, fruitName, bestGatheredQuantity)
            }
          yearMonth -> SortedSet.from(rs)((x: SpecificFruitGatherer, y: SpecificFruitGatherer) => x.fruitName.compareTo(y.fruitName))
      }

    SortedMap.from(result)
  }

  def bestGathererOfSpecificFruitsByYear(gatherers: List[Gatherer]): SortedMap[Year, SortedSet[SpecificFruitGatherer]] = {
    val result = gatherers
      .groupBy(gatherer => Year.from(gatherer.date))
      .map {
        case (year, v: List[Gatherer]) =>
          val rs = v.groupBy(gatherer => gatherer.fruitName)
            .map {
              case (fruitName, v1: List[Gatherer]) =>
                val (bestGathererName, bestGatheredQuantity) = v1.groupBy(gatherer => gatherer.name)
                  .map {
                    case (gathererName, v2: List[Gatherer]) => (gathererName, totalGatheredQuantity(v2))
                  }.max
                SpecificFruitGatherer(bestGathererName, fruitName, bestGatheredQuantity)
            }
          year -> SortedSet.from(rs)((x: SpecificFruitGatherer, y: SpecificFruitGatherer) => x.fruitName.compareTo(y.fruitName))
      }

    SortedMap.from(result)
  }


  private def totalGatheredQuantity(in: List[Gatherer]): Double =
    in.foldLeft(0.0) { case (sum, gatherer) => sum + gatherer.quantity }
}
