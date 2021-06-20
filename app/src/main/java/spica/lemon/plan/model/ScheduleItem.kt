package spica.lemon.plan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_schedule_item")
data class ScheduleItem(
  @PrimaryKey(autoGenerate = true)
  var id: Long? = null,
  var hasDone: Boolean = true,
  var info: String = ""
)
