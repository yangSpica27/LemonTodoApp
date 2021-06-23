package spica.lemon.plan.view

import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.MonthView
import spica.lemon.plan.tools.dp

@Suppress("unused")
class GalleryMonthView(context: Context) : MonthView(context) {


  private val dayTextTopPadding = 8.dp

  private val lunarTextPadding = 8.dp

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
    mSelectedPaint.maskFilter = BlurMaskFilter(32F, BlurMaskFilter.Blur.SOLID)
  }


  override fun onDrawSelected(canvas: Canvas, calendar: Calendar, x: Int, y: Int, hasScheme: Boolean): Boolean {
    mSelectedPaint.style = Paint.Style.FILL
    canvas.drawRect(x.toFloat(), y.toFloat(), (x + mItemWidth).toFloat(), (y + mItemHeight).toFloat(), mSelectedPaint)
    return true
  }

  override fun onDrawScheme(canvas: Canvas, calendar: Calendar, x: Int, y: Int) {
    mSchemeBasicPaint.color = calendar.schemeColor
    val schemes = calendar.schemes
    if (schemes == null || schemes.size == 0) {
      return
    }
    val space: Int = 2F.dp.toInt()
    var indexY = y + mItemHeight - 2 * space
    val sw: Int = (mItemWidth / 10).dp
    val sh: Int = 4F.dp.toInt()
    for (scheme in schemes) {
      mSchemePaint.color = scheme.shcemeColor
      canvas.drawRect(
        (x + mItemWidth - sw - 2 * space).toFloat(), (
                indexY - sh).toFloat(), (x + mItemWidth - 2 * space).toFloat(), indexY.toFloat(), mSchemePaint
      )
      indexY = indexY - space - sh
    }
  }


  private val rect = Rect() //日期数字测量
  private val rect2 = Rect() //农历节日的策略

  override fun onDrawText(canvas: Canvas, calendar: Calendar, x: Int, y: Int, hasScheme: Boolean, isSelected: Boolean) {

    canvas.drawRect(x.toFloat(), y.toFloat(), (x + mItemWidth).toFloat(), (y + mItemHeight).toFloat(), mRectPaint)
    val cx = x + mItemWidth / 2



    mSelectTextPaint.getTextBounds(calendar.day.toString(), 0, calendar.day.toString().length, rect)
    mSelectedLunarTextPaint.getTextBounds(calendar.lunar.toString(), 0, calendar.lunar.toString().length, rect2)

    val isInRange = isInRange(calendar)

    when {
      isSelected -> {
        canvas.drawText(
          calendar.day.toString(), cx.toFloat(),
          (y + rect.height() + lunarTextPadding).toFloat(),
          mSelectTextPaint
        )
        canvas.drawText(
          calendar.lunar, cx.toFloat(),
          (y + rect.height() + rect2.height() + dayTextTopPadding + lunarTextPadding).toFloat(),
          mSelectedLunarTextPaint
        )
      }
      hasScheme -> {
        canvas.drawText(
          calendar.day.toString(),
          cx.toFloat(),
          (y + rect.height() + lunarTextPadding).toFloat(),
          if (calendar.isCurrentMonth && isInRange)
            mSchemeTextPaint else
            mOtherMonthTextPaint
        )
        canvas.drawText(
          calendar.lunar, cx.toFloat(),
          (y + rect.height() + rect2.height() + dayTextTopPadding + lunarTextPadding).toFloat(),
          mCurMonthLunarTextPaint
        )

      }
      else -> {
        canvas.drawText(
          calendar.day.toString(), cx.toFloat(), (y + rect.height() + lunarTextPadding).toFloat(),
          if (calendar.isCurrentDay) mCurDayTextPaint else if (calendar.isCurrentMonth && isInRange) mCurMonthTextPaint else mOtherMonthTextPaint
        )
        canvas.drawText(
          calendar.lunar, cx.toFloat(),
          (y + rect.height() + rect2.height() + dayTextTopPadding + lunarTextPadding).toFloat(),
          if (calendar.isCurrentDay && isInRange) mCurDayLunarTextPaint else if (calendar.isCurrentMonth) mCurMonthLunarTextPaint else mOtherMonthLunarTextPaint
        )
      }
    }
  }
}