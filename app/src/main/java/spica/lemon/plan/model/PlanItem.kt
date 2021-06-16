package spica.lemon.plan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlanItem(
  @PrimaryKey(autoGenerate = true)
  var id: Long? = null,
  var hasDone: Boolean = true,
  var info: String = ""
)
