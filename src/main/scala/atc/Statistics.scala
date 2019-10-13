package atc

import java.time.{LocalDate, LocalDateTime}

object Statistics {

  def totalNumberOfCars(timeFrames: List[TimeFrame]): Int = timeFrames.map(_.count).sum

  def totalNumberOfCarsByDate(timeFrames: List[TimeFrame]): List[(LocalDate, Int)] =
    timeFrames.groupBy(_.date).mapValues(totalNumberOfCars(_)).toList.sortBy(_._1)(Ordering.by(_.toEpochDay))

  def topTimeFramesWithMostCars(timeFrames: List[TimeFrame], topN: Int): List[TimeFrame] =
    timeFrames.sortBy(_.count)(Ordering[Int].reverse).take(topN)

  def contiguousFramesWithLeastCars(timeFrames: List[TimeFrame], framesCount: Int): List[TimeFrame] =
    TimeFrame.convertToSubFramesOfSize(timeFrames, framesCount)
      .filter(TimeFrame.isContiguous(_))
      .sortBy(subFrames => totalNumberOfCars(subFrames))
      .head
}
