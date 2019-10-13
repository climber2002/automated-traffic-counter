package atc

import java.time.LocalDateTime

case class TimeFrame(startTime: LocalDateTime, count: Int) {
  def date = startTime.toLocalDate
}
