package atc

import java.time.LocalDate

object Statistics {
  def totalNumberOfCars(timeFrames: List[TimeFrame]): Int = timeFrames.map(_.count).sum

  def totalNumberOfCarsByDate(timeFrames: List[TimeFrame]): List[(LocalDate, Int)] =
    timeFrames.groupBy(_.date).mapValues(totalNumberOfCars(_)).toList
}
