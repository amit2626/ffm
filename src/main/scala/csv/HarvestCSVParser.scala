package csv

import csv.CSVParser.Delimiter
import model.Gatherer
import util.DateUtil

import scala.util.{Failure, Success, Try}

object HarvestCSVParser extends CSVParser[Gatherer] {

  def parseLine(in: String): Option[Gatherer] =
    in.split(Delimiter) match {
      case Array(nameString, dateString, fruitNameString, quantityString) =>
        (for {
          name <- Try(nameString.trim).filter(_.nonEmpty)
          date <- Try(dateString.trim).filter(_.nonEmpty).flatMap(DateUtil.parse)
          fruitName <- Try(fruitNameString.trim).filter(_.nonEmpty)
          quantity <- Try(quantityString.trim).filter(_.nonEmpty).flatMap(q => Try(q.toDouble))
        } yield Gatherer(name, date, fruitName, quantity)) match {
          case Success(gatherer) => Some(gatherer)
          case Failure(exception) =>
            println(s"""Unable to parse line: "$in" with exception as: "${exception.getMessage}"""")
            None
        }

      case _ =>
        println(s"Unable to parse line: $in")
        None
    }
}
