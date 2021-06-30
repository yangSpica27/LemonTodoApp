package spica.lemon.plan.ui.modifyplan

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import spica.lemon.plan.base.LiveCoroutinesViewModel
import spica.lemon.plan.model.Schedule
import spica.lemon.plan.persistence.dao.ScheduleDao
import javax.inject.Inject

@HiltViewModel
class ModifyScheduleViewModel @Inject constructor(private val scheduleDao: ScheduleDao) :
    LiveCoroutinesViewModel() {


    private lateinit var _scheduleLiveData: LiveData<Schedule?>


    val scheduleLiveData: LiveData<Schedule?> get() = _scheduleLiveData

    //保存
    fun saveSchedule(schedule: Schedule) {
        //切换至io线程
        viewModelScope.launch(Dispatchers.IO) {
            scheduleDao.insertSchedule(schedule)
        }
    }

    fun fetchSchedule(id: Long) {
        _scheduleLiveData = scheduleDao.getScheduleById(id).asLiveDataOnViewModelScope()
    }


}