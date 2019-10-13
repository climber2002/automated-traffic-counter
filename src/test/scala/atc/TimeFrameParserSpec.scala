package atc

import java.time.{LocalDateTime, Month}

import org.scalatest.{FunSpec, Matchers}

class TimeFrameParserSpec extends FunSpec with Matchers {
  describe("parseTimeFrame") {
    it("should parse a string into a TimeFrame object") {
      val string = "2016-12-01T05:30:00 12"
      val expected = TimeFrame(LocalDateTime.of(2016, Month.DECEMBER, 1, 5, 30), 12)

      TimeFrameParser.parseTimeFrame(string) should be(expected)
    }
  }
}
