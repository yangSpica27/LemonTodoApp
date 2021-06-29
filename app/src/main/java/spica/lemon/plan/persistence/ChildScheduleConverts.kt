package spica.lemon.plan.persistence

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import spica.lemon.plan.model.ScheduleItem
import java.lang.reflect.Type


/**
 * 子item序列化转换器
 */
class ChildScheduleConverts {


    @TypeConverter
    fun stringToObject(jsonString: String): List<ScheduleItem> {
        val moshi: Moshi = Moshi.Builder().build()
        val listOfCardsType: Type = Types.newParameterizedType(List::class.java, ScheduleItem::class.java)
        val jsonAdapter: JsonAdapter<List<ScheduleItem>> = moshi.adapter(listOfCardsType)

        return jsonAdapter.fromJson(jsonString) ?: arrayListOf()
    }

    @TypeConverter
    fun objectToString(list: List<ScheduleItem>): String {
        val moshi: Moshi = Moshi.Builder().build()
        val listOfCardsType: Type = Types.newParameterizedType(List::class.java, ScheduleItem::class.java)
        val jsonAdapter: JsonAdapter<List<ScheduleItem>> = moshi.adapter(listOfCardsType)
        return jsonAdapter.toJson(list)
    }

}