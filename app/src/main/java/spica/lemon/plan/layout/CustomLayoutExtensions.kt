package spica.lemon.plan.layout

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setMargins


/**
 * 用于方便布局的拓展方法
 */
@Suppress("unused")
interface CustomLayoutExtensions {
    fun getResources(): Resources

    companion object {
        const val INVALID_VIEW_SIZE = -3
    }

    val View.measureWidthWithMargins
        get() = measuredWidth + leftMargin + rightMargin

    val View.measureHeightWithMargins
        get() = measuredHeight + topMargin + bottomMargin

    val View.leftMargin: Int
        get() = (layoutParams as? CustomLayout.LayoutParams)?.leftMargin ?: 0

    val View.topMargin: Int
        get() = (layoutParams as? CustomLayout.LayoutParams)?.topMargin ?: 0

    val View.rightMargin: Int
        get() = (layoutParams as? CustomLayout.LayoutParams)?.rightMargin ?: 0

    val View.bottomMargin: Int
        get() = (layoutParams as? CustomLayout.LayoutParams)?.bottomMargin ?: 0


    fun Int.toExactlyMeasureSpec(): Int {
        return View.MeasureSpec.makeMeasureSpec(this, View.MeasureSpec.EXACTLY)
    }

    fun Int.toAtMostMeasureSpec(): Int {
        return View.MeasureSpec.makeMeasureSpec(this, View.MeasureSpec.AT_MOST)
    }

    fun View.defaultWidthMeasureSpec(parentView: ViewGroup): Int = when (layoutParams.width) {
        ViewGroup.LayoutParams.MATCH_PARENT -> (parentView.measuredWidth - paddingStart - paddingEnd).toExactlyMeasureSpec()
        ViewGroup.LayoutParams.WRAP_CONTENT -> (parentView.measuredWidth - paddingStart - paddingEnd).toAtMostMeasureSpec()
        0 -> throw IllegalAccessException("Need special treatment for $this")
        else -> layoutParams.width.toExactlyMeasureSpec()
    }

    fun View.defaultHeightMeasureSpec(parentView: ViewGroup): Int = when (layoutParams.height) {
        ViewGroup.LayoutParams.MATCH_PARENT -> (parentView.measuredHeight - paddingTop - paddingBottom).toExactlyMeasureSpec()
        ViewGroup.LayoutParams.WRAP_CONTENT -> (parentView.measuredHeight - paddingTop - paddingBottom).toAtMostMeasureSpec()
        0 -> throw IllegalAccessException("Need special treatment for $this")
        else -> layoutParams.height.toExactlyMeasureSpec()
    }

    fun View.measureExactly(width: Int, height: Int) {
        measure(width.toExactlyMeasureSpec(), height.toExactlyMeasureSpec())
    }

    fun View.setPadding(padding: Int) {
        setPadding(padding, padding, padding, padding)
    }

    fun TextView.setTextSizePx(px: Float) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, px)
    }

    fun View.setViewSize(size: Int) {
        setViewSize(size, size)
    }

    fun View.setViewSize(width: Int = INVALID_VIEW_SIZE, height: Int = INVALID_VIEW_SIZE) {
        val mLayoutParams = layoutParams
        if (width != INVALID_VIEW_SIZE) mLayoutParams.width = width
        if (height != INVALID_VIEW_SIZE) mLayoutParams.height = height
        layoutParams = mLayoutParams
    }

    fun View.setMargins(size: Int) {
        (layoutParams as? CustomLayout.LayoutParams)?.setMargins(size)
    }

    fun View.setMargins(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
        (layoutParams as? CustomLayout.LayoutParams)?.setMargins(left, top, right, bottom)
    }

    fun Array<View>.setMargins(size: Int) {
        forEach { it.setMargins(size) }
    }

    fun Array<View>.setMargins(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
        forEach { it.setMargins(left, top, right, bottom) }
    }
}