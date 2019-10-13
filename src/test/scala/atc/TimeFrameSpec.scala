package atc

import java.time.{LocalDate, LocalDateTime, Month}

import org.scalatest.{FunSpec, Matchers}

class TimeFrameSpec extends FunSpec with Matchers {
  val timestamp = LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 0)
  val timeFrame = TimeFrame(timestamp, 5)

  it("initialize the time frame correctly") {
    timeFrame.startTime should be (timestamp)
    timeFrame.count should be (5)
  }

  describe("date") {
    it("should return the date of the TimeFrame") {
      timeFrame.date should be(LocalDate.of(2016, Month.DECEMBER, 1))
    }
  }

  describe("TimeFrame.isContiguous") {
    it("should return true if the list is empty") {
      TimeFrame.isContiguous(List.empty) should be(true)
    }

    it("should return true if the list has only one TimeFrame") {
      val timeFrames = List(TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 0), 5))
      TimeFrame.isContiguous(timeFrames) should be(true)
    }

    it("should return true if the list has contiguous TimeFrames") {
      val timeFrames = List(
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 0), 5),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 30), 0),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 6, 0), 10),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 6, 30), 20),
      )

      TimeFrame.isContiguous(timeFrames) should be(true)
    }

    it("should return false if the TimeFrames are not contiguous") {
      val timeFrames = List(
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 0), 5),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 6, 0), 10),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 6, 30), 20),
      )

      TimeFrame.isContiguous(timeFrames) should be(false)
    }
  }

  describe("TimeFrame.convertToSubFramesWithSize") {
    it("returns a list of lists, each list is a sub TimeFrames the first N TimeFrames") {
      val timeFrames = List(
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 0), 5),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 30), 0),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 6, 0), 10),
        TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 6, 30), 20)
      )

      val expected = List(
        List(
          TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 0), 5),
          TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 30), 0),
          TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 6, 0), 10)
        ),
        List(
          TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 30), 0),
          TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 6, 0), 10),
          TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 6, 30), 20)
        )
      )

      TimeFrame.convertToSubFramesWithSize(timeFrames, 3) should be(expected)
    }
  }
}
