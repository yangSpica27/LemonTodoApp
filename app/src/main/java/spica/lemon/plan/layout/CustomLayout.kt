@file:Suppress("unused")

package spica.lemon.plan.layout

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.HapticFeedbackConstants
import android.view.View
import android.view.ViewGroup
import androidx.core.view.*

abstract class CustomLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr), CustomLayoutExtensions {

    protected fun <T : View> T.autoAddView(
        width: Int = wrapContent,
        height: Int = wrapContent,
        block: T.(LayoutParams) -> Unit = {}
    ): T = apply {
        val mLayoutParams = LayoutParams(width, height)
        block(mLayoutParams)
        layoutParams = mLayoutParams
        this@CustomLayout.addView(this)
    }

    protected fun <T : View> T.autoAddViewMaxWidth(
        width: Int = matchParent,
        height: Int = wrapContent,
        block: T.(LayoutParams) -> Unit = {}
    ): T = apply {
        val mLayoutParams = LayoutParams(width, height)
        block(mLayoutParams)
        layoutParams = mLayoutParams
        this@CustomLayout.addView(this)
    }

    protected fun <T : View> T.autoAddView(
        size: Int,
        block: T.(LayoutParams) -> Unit = {}
    ) = autoAddView(size, size, block)

    protected fun <T : View> T.autoAddViewMax(
        width: Int = matchParent,
        height: Int = matchParent,
        block: T.(LayoutParams) -> Unit = {}
    ) = autoAddView(width, height, block)

    protected val Int.dp: Int get() = (this * resources.displayMetrics.density + 0.5f).toInt()

    class LayoutParams(width: Int, height: Int) : MarginLayoutParams(width, height)

    override fun generateDefaultLayoutParams(): LayoutParams {
        return LayoutParams(matchParent, wrapContent)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        this.onMeasureChildren(widthMeasureSpec, heightMeasureSpec)
    }

    protected abstract fun onMeasureChildren(widthMeasureSpec: Int, heightMeasureSpec: Int)

    protected fun View.autoMeasure() {
        if (isGone) return
        measure(
            this.defaultWidthMeasureSpec(parentView = this@CustomLayout),
            this.defaultHeightMeasureSpec(parentView = this@CustomLayout)
        )
    }

    protected fun View.forEachAutoMeasure() {
        forEach { it.autoMeasure() }
    }

    protected fun View.layout(x: Int, y: Int, fromRight: Boolean = false) {
        if (isGone) return
        if (!fromRight) {
            layout(x, y, x + measuredWidth, y + measuredHeight)
        } else {
            layout(this@CustomLayout.measuredWidth - x - measuredWidth, y)
        }
    }

    protected val View.measuredWidthWithMargins get() = (measuredWidth + marginLeft + marginRight)
    protected val View.measuredHeightWithMargins get() = (measuredHeight + marginTop + marginBottom)


    fun addView(child: View, width: Int, height: Int, apply: (LayoutParams.() -> Unit)) {
        val params = generateDefaultLayoutParams().apply {
            this.width = width
            this.height = height
        }
        params.apply { apply.invoke(this) }
        super.addView(child, params)
    }

    fun View.overScrollNever() {
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    fun ViewGroup.horizontalCenterX(child: View): Int {
        return (measuredWidth - child.measuredWidth) / 2
    }

    fun ViewGroup.verticalCenterTop(child: View): Int {
        return (measuredHeight - child.measuredHeight) / 2
    }

    /**
     * ????????????
     */
    protected fun View.autoMeasure(
        widthMeasureSpec: Int = defaultWidthMeasureSpec(parentView = this@CustomLayout),
        heightMeasureSpec: Int = defaultHeightMeasureSpec(parentView = this@CustomLayout)
    ) {
        measure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * ???View????????????
     */
    protected fun autoMeasure(vararg views: View) {
        views.forEach { it.autoMeasure() }
    }

    /**
     * ??????
     */
    protected fun View.layout(x: Int, y: Int) = layout(
        x, y, x + measuredWidth, y + measuredHeight
    )

    /**
     * ??????
     * @param fromRight ???????????????
     * @param fromBottom ???????????????
     */
    @Suppress("NOTHING_TO_INLINE")
    protected inline fun View.layout(
        x: Int, y: Int,
        fromRight: Boolean = false,
        fromBottom: Boolean = false
    ) = layout(
        if (fromRight) this@CustomLayout.measuredWidth - x - measuredWidth else x,
        if (fromBottom) this@CustomLayout.measuredHeight - y - measuredHeight else y
    )

    /**
     * ??????
     */
    @Suppress("NOTHING_TO_INLINE")
    protected inline fun View.layoutCenter() = layout(
        (this@CustomLayout.measuredWidth - measuredWidth) / 2,
        (this@CustomLayout.measuredHeight - measuredHeight) / 2
    )

    /**
     * ??????
     * @param target ??????View
     */
    @Suppress("NOTHING_TO_INLINE")
    protected inline fun View.layoutCenter(target: View) = layout(
        target.left + (target.measuredWidth - measuredWidth) / 2,
        target.top + (target.measuredHeight - measuredHeight) / 2
    )

    /**
     * ???????????????????????????  ??? ???
     */
    @Suppress("NOTHING_TO_INLINE")
    protected inline fun View.layoutHorizontal(x: Int, fromRight: Boolean = false) = layout(
        x = x,
        y = (this@CustomLayout.measuredHeight - measuredHeight) / 2,
        fromRight = fromRight
    )

    /**
     * ?????????View???????????????????????????  ??? ???
     * @param target ??????View
     */
    @Suppress("NOTHING_TO_INLINE")
    protected inline fun View.layoutHorizontal(
        x: Int, target: View, fromRight: Boolean = false
    ) = layout(
        x = x,
        y = target.top + (target.measuredHeight - measuredHeight) / 2,
        fromRight = fromRight
    )

    /**
     * ??????????????????????????? ??? ???
     */
    @Suppress("NOTHING_TO_INLINE")
    protected inline fun View.layoutVertical(y: Int, fromBottom: Boolean = false) = layout(
        x = (this@CustomLayout.measuredWidth - measuredWidth) / 2,
        y = y,
        fromBottom = fromBottom
    )

    /**
     * ?????????View??????????????????????????? ??? ???
     * @param target ??????View
     */
    @Suppress("NOTHING_TO_INLINE")
    protected inline fun View.layoutVertical(
        y: Int, target: View, fromBottom: Boolean = false
    ) = layout(
        x = target.left + (target.measuredWidth - measuredWidth) / 2,
        y = y,
        fromBottom = fromBottom
    )

    /**
     * ???View????????????
     * @param isVertical ??????????????????
     */
    protected fun layoutCenter(vararg views: View, isVertical: Boolean = true) {
        if (isVertical) {
            val topY = (measuredHeight - plusHeightWithMargins(*views)) / 2
            layoutVertical(topY, *views)
        } else {
            val leftX = (measuredWidth - plusWidthWithMargins(*views)) / 2
            layoutHorizontal(leftX, *views)
        }
    }

    /**
     * ???view???????????? ???????????? ???
     */
    @Suppress("NOTHING_TO_INLINE")
    protected inline fun layoutHorizontal(vararg views: View): Int {
        return layoutHorizontal(0, *views)
    }

    /**
     * ???view???????????? ???????????? ???
     * @param startX x?????????
     */
    protected fun layoutHorizontal(startX: Int, vararg views: View): Int {
        var leftX = startX
        for (view in views) {
            leftX += view.leftMargin
            view.layoutHorizontal(leftX)
            leftX += view.measuredWidth + view.rightMargin
        }
        return leftX
    }

    /**
     * ???view???????????? ???????????? ???
     * @param startX x?????????
     * @param centerY y?????????
     */
    protected fun layoutHorizontal(startX: Int, centerY: Int, vararg views: View): Int {
        var leftX = startX
        for (view in views) {
            leftX += view.leftMargin
            view.layout(leftX, centerY - view.measuredHeight / 2)
            leftX += view.measuredWidth + view.rightMargin
        }
        return leftX
    }

    /**
     * ???view???????????? ???????????? ???
     */
    protected fun layoutVertical(vararg views: View): Int {
        return layoutVertical(0, *views)
    }

    /**
     * ???view???????????? ???????????? ???
     * @param startY y?????????
     */
    protected fun layoutVertical(startY: Int, vararg views: View): Int {
        var topY = startY
        for (view in views) {
            topY += view.topMargin
            view.layoutVertical(topY)
            topY += view.measuredHeight + view.bottomMargin
        }
        return topY
    }

    /**
     * ???view???????????? ???????????? ???
     * @param startY y?????????
     * @param centerX x?????????
     */
    protected fun layoutVertical(startY: Int, centerX: Int, vararg views: View): Int {
        var topY = startY
        for (view in views) {
            topY += view.topMargin
            view.layout(centerX - view.measuredWidth / 2, topY)
            topY += view.measuredHeight + view.bottomMargin
        }
        return topY
    }

    /**
     * ?????????View????????????
     * @param target ??????View
     * @return x?????????
     */
    protected fun View.horizontalCenterOf(target: View): Int {
        return target.left + (target.measuredWidth - measuredWidth) / 2
    }

    /**
     * ?????????View????????????
     * @param target ??????View
     * @return y?????????
     */
    protected fun View.verticalCenterOf(target: View): Int {
        return target.top + (target.measuredHeight - measuredHeight) / 2
    }

    protected fun plusWidthWithMargins(vararg views: View): Int {
        return views.sumOf { it.measureWidthWithMargins }
    }

    protected fun plusHeightWithMargins(vararg views: View): Int {
        return views.sumOf { it.measureHeightWithMargins }
    }


}

const val matchParent = ViewGroup.LayoutParams.MATCH_PARENT
const val wrapContent = ViewGroup.LayoutParams.WRAP_CONTENT


fun View.transparentBackground() {
    setBackgroundColor(Color.TRANSPARENT)
}

val View.parentView get() = parent as ViewGroup

fun View?.performHapticFeedbackSafely() {
    try {
        this?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
    } catch (t: Throwable) {
        Log.e("CustomLayout:", t.message.toString())
    }
}
