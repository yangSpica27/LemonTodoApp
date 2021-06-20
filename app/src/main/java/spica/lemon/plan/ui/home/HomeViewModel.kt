package spica.lemon.plan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import spica.lemon.plan.model.Schedule

class HomeViewModel : ViewModel() {

  private val _item = MutableLiveData<List<Schedule>>().apply {
    value = listOf(
      Schedule(id = 0L, 0, label = "普通", description = "整理旅行药品"),
      Schedule(id = 1L, 0, label = "普通", description = "买火车票"),
      Schedule(id = 2L, 0, label = "普通", description = "退机票"),
      Schedule(id = 3L, 0, label = "普通", description = "买晕车药"),
      Schedule(id = 5L, 0, label = "常用", description = "带上合同"),
    )
  }
  val list: LiveData<List<Schedule>> = _item
}