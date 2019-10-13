package atc

import java.time.{LocalDateTime, Month}

import org.scalatest.{FunSpec, Matchers}

class TimeFrameSpec extends FunSpec with Matchers {
  val timestamp = LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 0)
  val timeFrame = TimeFrame(timestamp, 5)

  it("initialize the time frame correctly") {
    timeFrame.startTime should be (timestamp)
    timeFrame.count should be (5)
  }
}
