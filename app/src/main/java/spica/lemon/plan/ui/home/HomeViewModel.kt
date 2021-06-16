package spica.lemon.plan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import spica.lemon.plan.model.Plan

class HomeViewModel : ViewModel() {

  private val _item = MutableLiveData<List<Plan>>().apply {
    value = listOf(
      Plan(id = 0L, label = "普通", description = "整理旅行药品"),
      Plan(id = 1L, label = "普通", description = "买火车票"),
      Plan(id = 2L, label = "普通", description = "退机票"),
      Plan(id = 3L, label = "普通", description = "买晕车药"),
      Plan(id = 5L, label = "常用", description = "带上合同"),
    )
  }
  val list: LiveData<List<Plan>> = _item
}