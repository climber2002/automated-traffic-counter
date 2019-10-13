package atc

import java.time.{LocalDateTime, Month}

import org.scalatest.{FunSpec, Matchers}

class StatisticsSpec extends FunSpec with Matchers {
  val timeFrames = List(
    TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 6, 30), 15),
    TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 7, 0), 25),
    TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 7, 30), 46),
    TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 8, 0), 42),

    TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 5, 9, 30), 18),
    TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 5, 10, 30), 15),
  )

  describe("totalNumberOfCars") {
    it("should return the total number of cars of all TimeFrames") {
      Statistics.totalNumberOfCars(timeFrames) should be(15 + 25 + 46 + 42 + 18 + 15)
    }

    it("should return correct count if the list has only one TimeFrame") {
      Statistics.totalNumberOfCars(List(
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 6, 30), 15)
      )) should be(15)
    }

    it("should return 0 for empty list") {
      Statistics.totalNumberOfCars(List.empty) should be(0)
    }
  }
}
