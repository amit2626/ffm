package util

object Implicits {

  implicit lazy val tupleOrdering: Ordering[(String, Double)] =
    (x: (String, Double), y: (String, Double)) => java.lang.Double.compare(x._2, y._2)
}
