package atc

import java.time.{LocalDate, LocalDateTime}

object Statistics {
  def totalNumberOfCars(timeFrames: List[TimeFrame]): Int = timeFrames.map(_.count).sum

  def totalNumberOfCarsByDate(timeFrames: List[TimeFrame]): List[(LocalDate, Int)] =
    timeFrames.groupBy(_.date).mapValues(totalNumberOfCars(_)).toList

  def topTimeFramesWithMostCars(timeFrames: List[TimeFrame], topN: Int) =
    timeFrames.sortBy(_.count)(Ordering[Int].reverse).take(topN)

  def contiguousFramesWithLeastCars(timeFrames: List[TimeFrame], framesCount: Int): (LocalDateTime, Int) =
    TimeFrame.convertToSubFrames(timeFrames, framesCount)
      .filter(subFrames => subFrames.size == framesCount && TimeFrame.isContiguous(subFrames))
      .map(subFrames => (subFrames.head.startTime, totalNumberOfCars(subFrames)))
      .sortBy(_._2)
      .head
}
