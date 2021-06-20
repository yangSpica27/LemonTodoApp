package spica.lemon.plan.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation


@Entity
data class ScheduleDayWithSchedule(
  @Embedded
  val scheduleDay: ScheduleDay,//有计划的那天
  @Relation(
    parentColumn = "id",
    entityColumn = "did"
  )
  val schedules: List<Schedule>,//那天的计划
)