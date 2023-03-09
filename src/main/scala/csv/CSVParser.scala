package csv

import java.io.File
import scala.io.Source

trait CSVParser[Model] {

  def parse(file: File): List[Model] = {
    val source = Source.fromFile(file)
    try {
      source
        .getLines()
        .flatMap(parseLine)
        .toList
    } finally {
      source.close()
    }
  }

  protected def parseLine(in: String): Option[Model]
}

object CSVParser {

  lazy val Delimiter: String = ","
}
