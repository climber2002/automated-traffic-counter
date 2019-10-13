package atc

import java.time.{Duration, LocalDateTime}

case class TimeFrame(startTime: LocalDateTime, count: Int) {
  def date = startTime.toLocalDate
}

object TimeFrame {

  /**
   * Return true if the list of TimeFrames is contiguous
   * @param timeFrames
   * @return
   */
  def isContiguous(timeFrames: List[TimeFrame]): Boolean = timeFrames match {
    case frame1 :: frame2 :: tail =>
      if (Duration.between(frame1.startTime, frame2.startTime).toMinutes == 30) {
        isContiguous(frame2 :: tail)
      } else {
        false
      }
    case _ => true
  }

  /**
   * Convert a list of time frames to a list of list of TimeFrames, each list element is a sub list of the first N
   * TimeFrames of the TimeFrame list
   * @param timeFrames
   * @param subFrameSize
   * @return
   */
  def convertToSubFrames(timeFrames: List[TimeFrame], subFrameSize: Int): List[List[TimeFrame]] = timeFrames match {
    case _ :: tail => timeFrames.take(subFrameSize) :: convertToSubFrames(tail, subFrameSize)
    case Nil => List.empty[List[TimeFrame]]
  }

}
