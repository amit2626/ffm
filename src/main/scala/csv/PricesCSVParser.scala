package csv

import csv.CSVParser.Delimiter
import model.FruitPrice
import util.DateUtil

import scala.util.{Failure, Success, Try}

object PricesCSVParser extends CSVParser[FruitPrice] {

  def parseLine(in: String): Option[FruitPrice] =
    in.split(Delimiter) match {
      case Array(nameString, dateString, priceString) =>
        (for {
          name <- Try(nameString.trim).filter(_.nonEmpty)
          date <- Try(dateString.trim).filter(_.nonEmpty).flatMap(DateUtil.parse)
          price <- Try(priceString.trim).filter(_.nonEmpty).flatMap(q => Try(q.toDouble))
        } yield FruitPrice(name, date, price)) match {
          case Success(price) => Some(price)
          case Failure(exception) =>
            println(s"""Unable to parse line: "$in" with exception as: "${exception.getMessage}"""")
            None
        }

      case _ =>
        println(s"Unable to parse line: $in")
        None
    }
}
