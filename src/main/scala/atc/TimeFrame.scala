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
    case frame1 :: frame2 :: tail if isContiguous(frame1, frame2) => isContiguous(frame2 :: tail)
    case frame1 :: frame2 :: _ if !isContiguous(frame1, frame2) => false
    case _ => true
  }

  private def isContiguous(frame1: TimeFrame, frame2: TimeFrame): Boolean =
    Duration.between(frame1.startTime, frame2.startTime).toMinutes == 30

  /**
   * Convert a list of TimeFrames to a list of sub TimeFrames, each sub TimeFrames list is a sublist of the timeFrames
   * param and has exact subFramesSize TimeFrames
   *
   * @param timeFrames
   * @param subFramesSize
   * @return
   */
  def convertToSubFramesOfSize(timeFrames: List[TimeFrame], subFramesSize: Int): List[List[TimeFrame]] = {
    val subFramesList = timeFrames match {
      case _ :: tail => timeFrames.take(subFramesSize) :: convertToSubFramesOfSize(tail, subFramesSize)
      case Nil => List.empty[List[TimeFrame]]
    }

    subFramesList.filter(_.length == subFramesSize)
  }

}
