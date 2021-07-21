package spica.lemon.plan.tools

import android.content.SharedPreferences
import android.util.Log
import kotlin.reflect.KProperty

private const val TAG = "SPDelegate"

/**
 * 使用 SharedPreferences 代理变量
 * e.g.
 * val sp: SharedPreferences = PrefManager.getSharedPreferences("sp", Context.MODE_PRIVATE)
 * var aInt: Int by sp.delegate()
 * var aString: String by sp.delegate()
 * var aLong: Long by sp.delegate()
 */
fun SharedPreferences.delegate(keyName: String? = null, defaultValue: Any? = null): SpDelegate {
    return SpDelegate(this, keyName, defaultValue)
}

class SpDelegate(
    private val sharedPreferences: SharedPreferences,
    /** sp key，如果不指定则使用 KProperty.name，KProperty.name 是变量名，且不受混淆影响 */
    val keyName: String? = null,
    /** 默认值，如果不指定则使用内置默认值 */
    private val defaultValue: Any? = null
) {

    @Suppress("UNCHECKED_CAST")
    inline operator fun <reified T> getValue(obj: Any, property: KProperty<*>): T {
        val name = keyName?: property.name
        return doGetValue(name, T::class.java) as T
    }

    fun doGetValue(name: String, clazz: Class<*>): Any {
        val value =  when (clazz) {
            java.lang.Integer::class.java, Int::class.java -> sharedPreferences.getInt(name, (defaultValue as? Int?: 0))
            java.lang.Long::class.java, Long::class.java -> sharedPreferences.getLong(name, (defaultValue as? Long?: 0L))
            java.lang.Float::class.java, Float::class.java -> sharedPreferences.getFloat(name, (defaultValue as? Float?: 0f))
            java.lang.Boolean::class.java, Boolean::class.java -> sharedPreferences.getBoolean(name, (defaultValue as? Boolean?: false))
            String::class.java -> sharedPreferences.getString(name, (defaultValue as? String?: ""))?: ""
            else -> throw IllegalArgumentException("SpDelegate not support class $clazz")
        }
        Log.i(TAG, "getValue $name $value")
        return value
    }

    inline operator fun <reified T> setValue(obj: Any, property: KProperty<*>, i: T) {
        val name = keyName?: property.name
        doSetValue(name, T::class.java, i)
    }

    fun <T> doSetValue(name: String, clazz: Class<*>, i: T) {
        Log.i(TAG, "setValue $name: $i")
        val editor = sharedPreferences.edit()
        when (clazz) {
            java.lang.Integer::class.java, Int::class.java -> editor.putInt(name, i as Int)
            java.lang.Long::class.java, Long::class.java -> editor.putLong(name, i as Long)
            java.lang.Float::class.java, Float::class.java -> editor.putFloat(name, i as Float)
            java.lang.Boolean::class.java, Boolean::class.java -> editor.putBoolean(name, i as Boolean)
            String::class.java -> editor.putString(name, i as String?)
            else -> throw IllegalArgumentException("SpDelegate not support class $clazz")
        }
        editor.apply()
    }
}