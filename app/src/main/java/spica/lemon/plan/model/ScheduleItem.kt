package spica.lemon.plan.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduleItem(
    var hasDone: Boolean = false,
    var info: String = "",
    var createTime: Long = 0L
)
