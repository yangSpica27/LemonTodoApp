package spica.lemon.plan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 日程单元
 */
@Entity(tableName = "table_day")
data class ScheduleDay(
  @PrimaryKey(autoGenerate = true)
  var id: Long? = null,
  val title: String = "",
  val createDate: String,
)
