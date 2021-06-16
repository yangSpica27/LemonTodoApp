package spica.lemon.plan.ui.home

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import spica.lemon.plan.model.Plan

/**
 * 主页列表
 */
class HomeListDelegate(private val activity: Activity) : ItemViewDelegate<Plan, HomeListDelegate.ViewHolder>() {

  class ViewHolder(val binding: ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root)

  override fun onBindViewHolder(holder: ViewHolder, item: Plan) {
    holder.binding.textTag.text = item.label
    holder.binding.textSchedule.text = item.description
    holder.binding.root.setOnClickListener {

    }
  }

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    val binding = spica.lemon.plan.databinding.ItemScheduleBinding.inflate(LayoutInflater.from(context), parent, false)
    return ViewHolder(binding)
  }

  override fun getItemId(item: Plan): Long {
    return item.id ?: 100L;
  }
}



