package spica.lemon.plan.view

import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Paint
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.WeekView
import spica.lemon.plan.tools.dp

/**
 * 日程的周视图
 */
class GalleryWeekView(context: Context) : WeekView(context) {


    private val mRectPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 0.5F.dp
        color = -0x77101011
    }
    private val mSchemeBasicPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        color = -0x12acad
        isFakeBoldText = true
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, mSchemeBasicPaint)
        mSelectedPaint.maskFilter = BlurMaskFilter(50F, BlurMaskFilter.Blur.SOLID)
    }

    override fun onDrawSelected(
        canvas: Canvas?,
        calendar: Calendar?,
        x: Int,
        hasScheme: Boolean
    ): Boolean {
        mSelectedPaint.style = Paint.Style.FILL
        canvas?.drawRect(
            x.toFloat(),
            0f,
            (x + mItemWidth).toFloat(),
            mItemHeight.toFloat(),
            mSelectedPaint
        )
        return true
    }

    override fun onDrawScheme(canvas: Canvas, calendar: Calendar, x: Int) {
        mSchemeBasicPaint.color = calendar.schemeColor
        val schemes = calendar.schemes
        if (schemes == null || schemes.size == 0) {
            return
        }
        val space: Int = 2F.dp.toInt()
        var indexY = mItemHeight - 2 * space
        val sw: Int = 10.dp
        val sh: Int = 4F.dp.toInt()
        for (scheme in schemes) {
            mSchemePaint.color = scheme.shcemeColor
            canvas.drawRect(
                (x + mItemWidth - sw - 2 * space).toFloat(),
                (
                        indexY - sh).toFloat(),
                (x + mItemWidth - 2 * space).toFloat(),
                indexY.toFloat(),
                mSchemePaint
            )
            indexY = indexY - space - sh
        }
    }

    override fun onDrawText(
        canvas: Canvas,
        calendar: Calendar,
        x: Int,
        hasScheme: Boolean,
        isSelected: Boolean
    ) {
        canvas.drawRect(
            x.toFloat(),
            0f,
            (x + mItemWidth).toFloat(),
            mItemHeight.toFloat(),
            mRectPaint
        )
        val cx = x + mItemWidth / 2
        val top = -mItemHeight / 6

        val isInRange = isInRange(calendar)

        when {
            isSelected -> {
                canvas.drawText(
                    calendar.day.toString(), cx.toFloat(), mTextBaseLine + top,
                    mSelectTextPaint
                )
                canvas.drawText(
                    calendar.lunar,
                    cx.toFloat(),
                    mTextBaseLine + mItemHeight / 10,
                    mSelectedLunarTextPaint
                )
            }
            hasScheme -> {
                canvas.drawText(
                    calendar.day.toString(), cx.toFloat(), mTextBaseLine + top,
                    if (calendar.isCurrentMonth && isInRange) mSchemeTextPaint else mOtherMonthTextPaint
                )
                canvas.drawText(
                    calendar.lunar,
                    cx.toFloat(),
                    mTextBaseLine + mItemHeight / 10,
                    mCurMonthLunarTextPaint
                )
            }
            else -> {
                canvas.drawText(
                    calendar.day.toString(), cx.toFloat(), mTextBaseLine + top,
                    if (calendar.isCurrentDay) mCurDayTextPaint else if (calendar.isCurrentMonth && isInRange) mCurMonthTextPaint else mOtherMonthTextPaint
                )
                canvas.drawText(
                    calendar.lunar, cx.toFloat(), mTextBaseLine + mItemHeight / 10,
                    if (calendar.isCurrentDay && isInRange) mCurDayLunarTextPaint else if (calendar.isCurrentMonth) mCurMonthLunarTextPaint else mOtherMonthLunarTextPaint
                )
            }
        }
    }
}