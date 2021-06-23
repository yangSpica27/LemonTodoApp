package spica.lemon.plan.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haibin.calendarview.Calendar

class GalleryViewModel : ViewModel() {

  //当前用户日历所在的日期
  private val _currentSelectCalendar: MutableLiveData<Calendar> = MutableLiveData<Calendar>()

  private val currentCalendar: Calendar = Calendar()


  val currentSelectCalendar: LiveData<Calendar>
    get() = _currentSelectCalendar


  fun changeCalendar(calendar: Calendar) {
    _currentSelectCalendar.postValue(calendar)
  }

  fun setCurrentCalendar(calendar: Calendar) {
    currentCalendar.year = calendar.year
    currentCalendar.month = calendar.month
    currentCalendar.day = calendar.day
  }

}