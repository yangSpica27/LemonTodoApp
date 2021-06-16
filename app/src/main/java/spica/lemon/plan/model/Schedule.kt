package spica.lemon.plan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 日程单元
 */
@Entity
data class Schedule(
  @PrimaryKey(autoGenerate = true)
  var id: Long? = null,
  val createDate: String,
  var plans: List<Plan> = listOf(),
)
