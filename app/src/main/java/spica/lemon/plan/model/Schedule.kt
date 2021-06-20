package spica.lemon.plan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 日程安排的大单元
 */
@Entity
data class Schedule(
  @PrimaryKey(autoGenerate = true)
  var id: Long? = null,
  val dIdLong:Long,
  var items: List<ScheduleItem> = listOf(),
  var title: String = "标题",
  var description: String = "",
  var hasDone: Boolean = false,
  var label: String = "普通任务",
  var lv: Int = 0
)
