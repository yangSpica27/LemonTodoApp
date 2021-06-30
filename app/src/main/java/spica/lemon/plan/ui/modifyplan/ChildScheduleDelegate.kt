package spica.lemon.plan.ui.modifyplan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import spica.lemon.plan.databinding.ItemChildScheduleBinding
import spica.lemon.plan.model.ScheduleItem

/**
 * 编辑页面的子item的适配器
 */
class ChildScheduleDelegate : ItemViewDelegate<ScheduleItem, ChildScheduleDelegate.ViewHolder>() {


    class ViewHolder(val itemBinding: ItemChildScheduleBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onBindViewHolder(holder: ViewHolder, item: ScheduleItem) {
        holder.itemBinding.checkbox.isChecked = item.hasDone
        holder.itemBinding.tvDesc.text = item.info
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
        val itemBinding =
            ItemChildScheduleBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(itemBinding = itemBinding)
    }

}