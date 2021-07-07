package spica.lemon.plan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import spica.lemon.plan.model.Schedule

class HomeViewModel : ViewModel() {

    private val _item = MutableLiveData<List<Schedule>>().apply {
        value = listOf(
            Schedule(
                id = 0L,
                title = "",
                label = "普通",
                description = "整理旅行药品",
                childSchedules = arrayListOf(),
                hasDone = false,
                labelColor = 0xFF,
                lv = 0,
                date = 0
            ),
            Schedule(
                id = 0L,
                title = "",
                label = "普通",
                description = "买票",
                childSchedules = arrayListOf(),
                hasDone = false,
                labelColor = 0xFF,
                lv = 0,
                date = 0
            ),
            Schedule(
                id = 0L,
                title = "",
                label = "普通",
                description = "取票",
                childSchedules = arrayListOf(),
                hasDone = false,
                labelColor = 0xFF,
                lv = 0,
                date = 0
            ),
            Schedule(
                id = 0L,
                title = "",
                label = "普通",
                description = "请个客",
                childSchedules = arrayListOf(),
                hasDone = false,
                labelColor = 0xFF,
                lv = 0,
                date = 0
            ),
            Schedule(
                id = 0L,
                title = "",
                label = "普通",
                description = "整理旅行药品",
                childSchedules = arrayListOf(),
                hasDone = false,
                labelColor = 0xFF,
                lv = 0,
                date = 0
            ),
            Schedule(
                id = 0L,
                title = "",
                label = "普通",
                description = "整理旅行药品",
                childSchedules = arrayListOf(),
                hasDone = false,
                labelColor = 0xFF,
                lv = 0,
                date = 0
            )

        )
    }

    val list: LiveData<List<Schedule>> = _item
}