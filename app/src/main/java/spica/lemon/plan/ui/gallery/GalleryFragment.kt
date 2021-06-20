package spica.lemon.plan.ui.gallery

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentGalleryBinding

class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {

  override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGalleryBinding = FragmentGalleryBinding.inflate(inflater, container, false)

  override fun init() {

    viewBinding.calendarView.setOnCalendarSelectListener(object : CalendarView.OnCalendarSelectListener {

      override fun onCalendarOutOfRange(calendar: Calendar?) {
        //日期拦截
      }

      @SuppressLint("SetTextI18n")
      override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
        //日期点击
        viewBinding.tvToolbarMonth.text = "${calendar?.month}月"
      }

    })

  }

}