package spica.lemon.plan.ui.modifyplan

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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


    private var _scheduleDetails: LiveData<Schedule> = MutableLiveData()

    //不可set的计划对象
    val scheduleDetails: LiveData<Schedule> get() = _scheduleDetails


    @WorkerThread
    fun getSchedule(id: Long) {
        _scheduleDetails = scheduleDao.getScheduleById(id);
    }


    //保存
    @WorkerThread
    fun saveSchedule(schedule: Schedule) {
        //切换至io线程
        viewModelScope.launch(Dispatchers.IO) {
            scheduleDao.insertSchedule(schedule)
        }
    }


}