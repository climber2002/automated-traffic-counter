package atc

import java.time.{Duration, LocalDateTime}

case class TimeFrame(startTime: LocalDateTime, count: Int) {
  def date = startTime.toLocalDate
}

object TimeFrame {

  /**
   * Return true if the list of TimeFrames is contiguous
   *
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
   * Convert a list of time frames to a list of sub TimeFrames, each sub TimeFrames has exact subFramesSize of
   * TimeFrames from the timeFrames param
   *
   * @param timeFrames
   * @param subFramesSize
   * @return
   */
  def convertToSubFramesWithSize(timeFrames: List[TimeFrame], subFramesSize: Int): List[List[TimeFrame]] = {
    val subFrames = timeFrames match {
      case _ :: tail => timeFrames.take(subFramesSize) :: convertToSubFramesWithSize(tail, subFramesSize)
      case Nil => List.empty[List[TimeFrame]]
    }

    subFrames.filter(_.length == subFramesSize)
  }

}
