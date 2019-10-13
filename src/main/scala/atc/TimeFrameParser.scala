package atc

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimeFrameParser {
  def parseTimeFrame(string: String): TimeFrame =
    string.split(" ") match {
      case Array(timestamp, count, _*) => TimeFrame(parseTimestamp(timestamp), parseCount(count))
    }

  private def parseTimestamp(timestamp: String): LocalDateTime =
    LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

  private def parseCount(count: String): Int = count.toInt
}
