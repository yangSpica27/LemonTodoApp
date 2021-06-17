package spica.lemon.plan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 日程安排的小单元
 */
@Entity(tableName = "plan")
data class Plan(
  @PrimaryKey(autoGenerate = true)
  var id: Long? = null,
  var items: List<PlanItem> = listOf(),
  var title: String = "标题",
  var description: String = "",
  var hasDone:Boolean =  false,
  var label: String = "普通任务",
  var lv:Int = 0
)
