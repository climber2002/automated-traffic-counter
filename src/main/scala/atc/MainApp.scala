package atc

import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter

import scala.io.Source

object MainApp extends App {

  val timeFrames = Source.fromResource("timeFrames.txt").getLines.toList.map(TimeFrameParser.parseTimeFrame(_))

  printTotalNumberOfCars(timeFrames)
  printTotalNumberOfCarsByDate(timeFrames)
  printTop3HalfHoursWithMostCars(timeFrames)
  printOneAndAHalfHourPeriodWithLeastCars(timeFrames)

  private def printTotalNumberOfCars(timeFrames: List[TimeFrame]) = {
    val totalCount = Statistics.totalNumberOfCars(timeFrames)
    println(s"\nThe number of cars seen in total is ${totalCount}")
  }

  private def printTotalNumberOfCarsByDate(timeFrames: List[TimeFrame]) = {
    val noOfCarsByDate = Statistics.totalNumberOfCarsByDate(timeFrames)
    println("\nThe total number of cars by date:")
    noOfCarsByDate.foreach {
      case (date, count) => println(s"${formatDate(date)} ${count}")
    }
  }

  private def printTop3HalfHoursWithMostCars(timeFrames: List[TimeFrame]) = {
    val top3 = Statistics.topTimeFramesWithMostCars(timeFrames, 3)
    println("\nThe top 3 half hours with most cars:")
    top3.foreach(timeFrame => println(s"${formatDateTime(timeFrame.startTime)} ${timeFrame.count}"))
  }

  private def printOneAndAHalfHourPeriodWithLeastCars(timeFrames: List[TimeFrame]) = {
    val contiguousFrames = Statistics.contiguousFramesWithLeastCars(timeFrames, 3)
    val startFrame = contiguousFrames.head
    val totalCars = Statistics.totalNumberOfCars(contiguousFrames)

    println(s"\nThe 1.5 hour period with least cars starts from ${formatDateTime(startFrame.startTime)}, and the total number of cars is ${totalCars}: ")
    contiguousFrames.foreach(timeFrame => println(s"${formatDateTime(timeFrame.startTime)} ${timeFrame.count}"))
  }

  private def formatDate(date: LocalDate): String = date.format(DateTimeFormatter.ISO_DATE)

  private def formatDateTime(dateTime: LocalDateTime): String = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}
