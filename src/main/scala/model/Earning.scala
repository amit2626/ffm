package model

import java.time.LocalDate

case class Earning(gathererName: String, date: LocalDate, fruitName: String, price: Double, quantity: Double) {

  def earning: Double = price * quantity
}
