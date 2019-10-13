package atc

import java.time.{LocalDate, LocalDateTime, Month}

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

  describe("totalNumberOfCarsByDate") {
    it("should group the total number of cars by date") {
      val expected = List(
        (LocalDate.of(2016, Month.DECEMBER, 1), 15 + 25 + 46 + 42),
        (LocalDate.of(2016, Month.DECEMBER, 5), 18 + 15)
      )

      Statistics.totalNumberOfCarsByDate(timeFrames) should be(expected)
    }

    it("should return empty list for empty list") {
      Statistics.totalNumberOfCarsByDate(List.empty) should be(List.empty)
    }
  }

  describe("topTimeFramesWithMostCars") {
    it("should return the top N TimeFrames with most cars") {
      val n = 3
      val expected = List(
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 7, 30), 46),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 8, 0), 42),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 7, 0), 25)
      )

      Statistics.topTimeFramesWithMostCars(timeFrames, n) should be(expected)
    }

    it("should return all TimeFrames sorted if the topN is larger than the size of TimeFrames list") {
      val timeFrames = List(
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 7, 0), 25),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 7, 30), 46)
      )

      val expected = List(
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 7, 30), 46),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 7, 0), 25)
      )

      Statistics.topTimeFramesWithMostCars(timeFrames, 3) should be(expected)
    }
  }
}
