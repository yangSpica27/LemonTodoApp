package spica.lemon.plan.ui.gallery

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentGalleryBinding
import spica.lemon.plan.tools.setVisibility

/**
 * 事件画廊
 */
class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {


  private val viewModel by viewModels<GalleryViewModel>()

  override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGalleryBinding = FragmentGalleryBinding.inflate(inflater, container, false)

  @SuppressLint("SetTextI18n")
  override fun init() {

    val currentCalendar = Calendar().apply {
      year = viewBinding.calendarView.curYear
      month = viewBinding.calendarView.curMonth
      day = viewBinding.calendarView.curDay
    }

    viewModel.setCurrentCalendar(calendar = currentCalendar)
    viewModel.changeCalendar(calendar = currentCalendar)

    viewModel.currentSelectCalendar.observe(viewLifecycleOwner, { calendar ->
      run {
        lifecycleScope.launch(Dispatchers.Main) {
          viewBinding.tvToolbarTitle.text = "${calendar.year}年${calendar.month}月"
        }
      }
    })

    viewBinding.btnCalendar.setOnClickListener {
      if (viewBinding.calendarView.isYearSelectLayoutVisible) {
        viewBinding.calendarView.closeYearSelectLayout()
      } else {
        viewBinding.calendarView.showYearSelectLayout(viewBinding.calendarView.curYear)
      }
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
        viewModel.changeCalendar(calendar)
        //不是该月的时候可见
        viewBinding.btnCalendar.setVisibility(!calendar.isCurrentMonth)
      }

    })

  }


}