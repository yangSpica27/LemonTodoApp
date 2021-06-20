package spica.lemon.plan.ui.gallery

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentGalleryBinding

/**
 * 事件画廊
 */
class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {

  private var currentMonth: Int = -1//当前月


  override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGalleryBinding = FragmentGalleryBinding.inflate(inflater, container, false)

  override fun init() {

    currentMonth = viewBinding.calendarView.curMonth

    viewBinding.btnCalendar.setOnClickListener {
      viewBinding.calendarView.showYearSelectLayout(viewBinding.calendarView.curYear)
    }

    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        isEnabled = if (viewBinding.calendarView.isYearSelectLayoutVisible) {
          viewBinding.calendarView.closeYearSelectLayout()
          true
        } else {
          false
        }
      }

    })

    viewBinding.calendarView.setOnCalendarSelectListener(object : CalendarView.OnCalendarSelectListener {
      override fun onCalendarOutOfRange(calendar: Calendar?) {
        //日期拦截
      }

      @SuppressLint("SetTextI18n")
      override fun onCalendarSelect(calendar: Calendar, isClick: Boolean) {
        //日期点击
        if (calendar.month != currentMonth) {
          viewBinding.btnCalendar.visibility = View.VISIBLE
        } else {
          viewBinding.btnCalendar.visibility = View.GONE
        }
        viewBinding.tvToolbarMonth.text = "${calendar.month}月"
      }

    })

  }


}