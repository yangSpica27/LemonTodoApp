package spica.lemon.plan.view

import android.content.Context
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.haibin.calendarview.WeekBar
import spica.lemon.plan.R

class GalleryWeekBar(context: Context) : WeekBar(context) {

  init {
    LayoutInflater.from(context).inflate(R.layout.week_bar, this, true)
    setBackgroundColor(ContextCompat.getColor(context, R.color.steel_blue))
  }

}