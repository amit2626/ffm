import csv._
import function.EarningFunctions._
import function.GathererFunctions._
import model._

import java.io.File

object App extends scala.App {

  args match {
    case Array(harvestFilePath, pricesFilePath) =>
      val harvestCSVFile: File = new File(harvestFilePath.trim)
      val pricesCSVFile: File = new File(pricesFilePath)

      val harvestParser: CSVParser[Gatherer] = HarvestCSVParser
      val pricesParser: CSVParser[FruitPrice] = PricesCSVParser

      val gatherers: List[Gatherer] = harvestParser.parse(harvestCSVFile)
      val prices: List[FruitPrice] = pricesParser.parse(pricesCSVFile)
      val earnings: List[Earning] = combine(gatherers, prices)

      println("Best Gatherer By Month")

      bestGatherersByMonth(gatherers).foreach(println)

      println("Best Gatherer By Year")

      bestGatherersByYear(gatherers).foreach(println)

      println("Best Gatherer of Specific Fruit By Month")

      bestGatherersOfSpecificFruitByMonth(gatherers).foreach(println)

      println("Best Gatherer of Specific Fruit By Year")

      bestGathererOfSpecificFruitsByYear(gatherers).foreach(println)

      println("Best Earning Fruit By Month")

      bestEarningFruitByMonth(earnings).foreach(println)
      println("Best Earning Fruit By Year")

      bestEarningFruitsByYear(earnings).foreach(println)

      println("Least Earning Fruit  By Month")
      leastEarningFruitByMonth(earnings).foreach(println)

      println("Least Earning Fruit  By Year")

      leastEarningFruitsByYear(earnings).foreach(println)

      println("Best Earning Gatherer By Month")

      bestEarningGathererByMonth(earnings).foreach(println)

      println("Best Earning Gatherer By Year")

      bestEarningGathererByYear(earnings).foreach(println)

      println("Least Earning Gatherer By Month")

      leastEarningGathererByMonth(earnings).foreach(println)

      println("Least Earning Gatherer By Year")

      leastEarningGathererByYear(earnings).foreach(println)

    case _ => println("Incorrect arguments, please provide file path as an argument.")
  }
}
