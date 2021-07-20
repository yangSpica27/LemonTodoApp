package spica.lemon.plan.ui.modifyplan

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.color.colorChooser
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.drakeet.multitype.MultiTypeAdapter
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.gyf.immersionbar.ktx.immersionBar
import dagger.hilt.android.AndroidEntryPoint
import spica.lemon.plan.R
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityModifyPlanBinding
import spica.lemon.plan.model.Schedule
import spica.lemon.plan.model.ScheduleItem
import spica.lemon.plan.tools.FluidContentResizer
import spica.lemon.plan.tools.dp
import java.text.SimpleDateFormat
import java.util.*


/**
 * 自定义计划内容
 */
@AndroidEntryPoint
class CreateScheduleActivity : BindingActivity<ActivityModifyPlanBinding>() {

    //子计划的适配器
    private lateinit var childScheduleAdapter: MultiTypeAdapter

    //计划
    private val schedule: Schedule = Schedule()

    //时间选择器
    private lateinit var datePickDialog: MaterialDialog

    //子item编辑Dialog
    private lateinit var childScheduleInputDialog: MaterialDialog


    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("yyyy年   MM月dd日")


    //标签的颜色集合
    private val labelColors = intArrayOf(
        0xFF002766.toInt(),
        0xFFff834d.toInt(),
        0xFF617a83.toInt(),
        0xFFd4a880.toInt()
    )


    private lateinit var colorPickDialog: MaterialDialog


    private val viewModel by viewModels<ModifyScheduleViewModel>()



    override fun initializer() {
        FluidContentResizer.listen(this)
        initStatusBar()
        initToolbar()
        initData()
        initColorPickDialog()
        initDatePickDialog()
        initChildScheduleInputDialog()
        initRecyclerView()
    }


    //初始化状态栏
    private fun initStatusBar() {
        immersionBar {
            statusBarDarkFont(true)
            statusBarColor(R.color.blue_7)
        }
    }


    //初始化工具栏
    private fun initToolbar() {
        viewBinding.toolbar.setOnMenuItemClickListener {

            return@setOnMenuItemClickListener false
        }
    }

    //初始化颜色选择窗口
    private fun initColorPickDialog() {
        colorPickDialog = MaterialDialog(this)
        with(colorPickDialog) {
            title = "选择标签颜色"
            colorChooser(
                colors = labelColors,
                allowCustomArgb = true
            ) { dialog, color ->
                viewBinding.btnFlag.background = ColorDrawable(color)
                dialog.dismiss()
            }
        }
        viewBinding.btnFlag.setOnClickListener {
            colorPickDialog.show()
        }
    }


    //初始化时间选择Dialog
    private fun initDatePickDialog() {
        datePickDialog = MaterialDialog(this)
        with(datePickDialog) {
            datePicker { dialog, datetime ->
                schedule.date = datetime.timeInMillis
                viewBinding.tvDate.text = sdf.format(datetime.time)
                dialog.dismiss()
            }
            lifecycleOwner(this@CreateScheduleActivity)
        }
        viewBinding.tvDate.setOnClickListener {
            datePickDialog.show()
        }
    }


    companion object {
        fun startActivity(startView: View, context: Activity) {
            val intent = Intent(context, CreateScheduleActivity::class.java)
            val bundle = ActivityOptions.makeSceneTransitionAnimation(context).toBundle()
            context.startActivity(intent, bundle)
        }
    }


    //初始化子计划的RecyclerView
    private fun initRecyclerView() {
        dividerBuilder()
            .showLastDivider()
            .size(8.dp)
            .colorRes(android.R.color.transparent)
            .build()
            .addTo(viewBinding.rvChildSchedule)
        childScheduleAdapter = MultiTypeAdapter(schedule.childSchedules)
        childScheduleAdapter.register(ChildScheduleDelegate())
        childScheduleAdapter.setHasStableIds(true)
        viewBinding.rvChildSchedule.adapter = childScheduleAdapter
    }

    //初始化子item编辑Dialog
    @SuppressLint("NotifyDataSetChanged")
    private fun initChildScheduleInputDialog() {
        childScheduleInputDialog = MaterialDialog(this)
        with(childScheduleInputDialog) {
            lifecycleOwner(this@CreateScheduleActivity)
            input { dialog, text ->
                run {
                    if (text.isNotEmpty()) {
                        schedule.childSchedules.add(
                            ScheduleItem(
                                false,
                                text.toString(),
                                createTime = Calendar.getInstance().timeInMillis
                            )
                        )
                        childScheduleAdapter.notifyDataSetChanged()
                    }
                    dialog.dismiss()
                }
            }
        }
        viewBinding.btnAddChildPlan.setOnClickListener {
            childScheduleInputDialog.show()
        }
    }


    //绑定数据
    private fun initData() {

    }


    override fun setupViewBinding(inflater: LayoutInflater): ActivityModifyPlanBinding =
        ActivityModifyPlanBinding.inflate(inflater)


}