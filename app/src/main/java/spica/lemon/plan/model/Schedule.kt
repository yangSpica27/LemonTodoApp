package spica.lemon.plan.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import spica.lemon.plan.persistence.ChildScheduleConverts

/**
 * 日程安排的大单元
 */
@Entity
@TypeConverters(ChildScheduleConverts::class)
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,//自动生成的id
    var title: String = "标题",//标题
    var description: String = "",//描述
    var hasDone: Boolean = false,//是否完成
    var label: String = "普通任务",//标签
    var labelColor: Int = 0xFF000000.toInt(),//标签颜色
    var childSchedules: MutableList<ScheduleItem> = mutableListOf(),//子任务
    var lv: Int = 0,//重要等级
    var date: Long = 0 //时间
)
