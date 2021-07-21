package spica.lemon.plan.layout

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import spica.lemon.plan.R
import spica.lemon.plan.tools.sp


/**
 * 主页的布局
 */
class ItemScheduleView(context: Context) : CustomLayout(context) {

    //标签
    private val tagText = AppCompatTextView(context).autoAddView {
        background = ContextCompat.getDrawable(context, R.drawable.bg_tag)
        setTextColor(ContextCompat.getColor(context, R.color.tan))
        setPadding(15.dp, 10.dp, 15.dp, 10.dp)
        textSize = 11F.sp
    }

    //计划内容
    private val scheduleText = AppCompatTextView(
        context, null,
        R.style.scheduleTextStyle
    ).autoAddView {
        setLines(2)
    }

    //时间
    private val timeText = AppCompatTextView(
        context, null,
        R.style.scheduleTimeTextStyle
    ).autoAddView {
        setLines(1)
    }


    init {
        transitionName = "shared_element_container"
        val params =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.marginStart = 24.dp
        params.marginEnd = 24.dp
        layoutParams = params
        background = ContextCompat.getDrawable(context, R.drawable.bg_item_rounded)
        setPadding(24.dp, 14.dp, 24.dp, 14.dp)
    }


    override fun onMeasureChildren(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        autoMeasure(tagText, timeText, scheduleText)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        tagText.layout(x = 0, y = 0)
        scheduleText.layout(x = 0, y = tagText.measuredHeightWithMargins)
        timeText.layout(x = 0, y = scheduleText.bottom + timeText.marginTop)
    }


}