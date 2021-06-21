package spica.lemon.plan.view

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.Keep
import androidx.core.content.ContextCompat
import com.haibin.calendarview.WeekBar
import spica.lemon.plan.R

@Suppress("unused")
@Keep
class GalleryWeekBar(context: Context) : WeekBar(context) {

  init {
    LayoutInflater.from(context).inflate(R.layout.week_bar, this, true)
    setBackgroundColor(ContextCompat.getColor(context, R.color.blue_7))
  }

}