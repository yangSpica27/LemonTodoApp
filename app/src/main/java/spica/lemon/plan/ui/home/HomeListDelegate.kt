package spica.lemon.plan.ui.home

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import spica.lemon.plan.databinding.ItemScheduleBinding
import spica.lemon.plan.model.Schedule
import spica.lemon.plan.ui.modifyplan.ModifyScheduleActivity

/**
 * 主页列表
 */
class HomeListDelegate(private val activity: Activity) : ItemViewDelegate<Schedule, HomeListDelegate.ViewHolder>() {

  class ViewHolder(val binding: ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root)

  override fun onBindViewHolder(holder: ViewHolder, item: Schedule) {
    holder.binding.textTag.text = item.label
    holder.binding.textSchedule.text = item.description
    holder.binding.root.setOnClickListener {
      ModifyScheduleActivity.startActivity(it, activity)
    }
  }

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    val binding = ItemScheduleBinding.inflate(LayoutInflater.from(context), parent, false)
    return ViewHolder(binding)
  }

  override fun getItemId(item: Schedule): Long {
    return item.id ?: 100L;
  }
}



