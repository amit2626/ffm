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

      bestGatherersByMonth(gatherers).foreach(println)
      println(">>>>>>>>>>>>")
      bestGatherersByYear(gatherers).foreach(println)
      println(">>>>>>>>>>>>>")
      bestGatherersOfSpecificFruitByMonth(gatherers).foreach(println)
      println(">>>>>>>>>>")
      bestGathererOfSpecificFruitsByYear(gatherers).foreach(println)
      println(">>>>>>>>>>>>>>>>>")
      bestEarningFruitByMonth(earnings).foreach(println)
      println(">>>>>>>>>>>>>>>>>")
      bestEarningFruitsByYear(earnings).foreach(println)
      println(">>>>>>>>>>>>>>>>>")
      leastEarningFruitByMonth(earnings).foreach(println)
      println(">>>>>>>>>>>>>>>>>")
      leastEarningFruitsByYear(earnings).foreach(println)
      println(">>>>>>>>>>>>>")
      bestEarningGathererByMonth(earnings).foreach(println)
      println(">>>>>>>>>>>>>")
      bestEarningGathererByYear(earnings).foreach(println)
      println(">>>>>>>>>>>>>")
      leastEarningGathererByMonth(earnings).foreach(println)
      println(">>>>>>>>>>>>>")
      leastEarningGathererByYear(earnings).foreach(println)

    case _ => println("Incorrect arguments, please provide file path as an argument.")
  }
}
