@file:Suppress("unused")
package spica.lemon.plan.tools

import android.content.res.Resources
import android.view.View

val Float.dp: Float                 // [xxhdpi](360 -> 1080)
  get() = android.util.TypedValue.applyDimension(
    android.util.TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

val Int.dp: Int
  get() = android.util.TypedValue.applyDimension(
    android.util.TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()


val Float.sp: Float                 // [xxhdpi](360 -> 1080)
  get() = android.util.TypedValue.applyDimension(
    android.util.TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics
  )


val Int.sp: Int
  get() = android.util.TypedValue.applyDimension(
    android.util.TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics
  ).toInt()

/**
 * 设置可见性
 */
fun View.setVisibility(visibility: Boolean) {
  if (visibility) {
    this.visibility = View.VISIBLE
  } else {
    this.visibility = View.GONE
  }
}
