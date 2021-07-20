package spica.lemon.plan.ui.modifyplan

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.color.colorChooser
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.drakeet.multitype.MultiTypeAdapter
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.gyf.immersionbar.ktx.immersionBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import spica.lemon.plan.R
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityModifyPlanBinding
import spica.lemon.plan.model.Schedule
import spica.lemon.plan.model.ScheduleItem
import spica.lemon.plan.tools.dp
import java.text.SimpleDateFormat
import java.util.*

const val SCHEDULE_ID_KEY = "SCHEDULE_ID_KEY"

@AndroidEntryPoint
class ModifyScheduleActivity : BindingActivity<ActivityModifyPlanBinding>() {


    private val viewModel: ModifyScheduleViewModel by viewModels()

    //子计划的适配器
    private lateinit var childScheduleAdapter: MultiTypeAdapter

    //计划
    private lateinit var schedule: Schedule

    //时间选择器
    private lateinit var datePickDialog: MaterialDialog

    //子item编辑Dialog
    private lateinit var childScheduleInputDialog: MaterialDialog

    //颜色选择器
    private lateinit var colorPickDialog: MaterialDialog

    //标签的颜色集合
    private val labelColors = intArrayOf(
        0xFF002766.toInt(),
        0xFFff834d.toInt(),
        0xFF617a83.toInt(),
        0xFFd4a880.toInt()
    )

    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("yyyy年   MM月dd日")


    override fun onCreate(savedInstanceState: Bundle?) {
//        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
//        findViewById<View>(android.R.id.content).transitionName = "shared_element_container"
//        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
//        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
//        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
//            addTarget(android.R.id.content)
//            duration = 300L
//        }
//        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
//            addTarget(android.R.id.content)
//            duration = 250L
//        }
        super.onCreate(savedInstanceState)
    }

    //init
    override fun initializer() {
        initStatusBar()
        initToolbar()
        initData()
        initColorPickDialog()
        initDatePickDialog()
        initChildScheduleInputDialog()
        initRecyclerView()
    }


    //从数据库中读取完整数据
    private fun initData() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getSchedule(intent.getLongExtra(SCHEDULE_ID_KEY, 0))
            viewModel.scheduleDetails.observe(this@ModifyScheduleActivity) {
                this@ModifyScheduleActivity.schedule = it
            }
        }
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


    companion object {
        fun startActivity(startView: View, context: Activity, id: Long) {
            val intent = Intent(context, CreateScheduleActivity::class.java)
            intent.putExtra(SCHEDULE_ID_KEY, id)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                context,
                startView,
                "shared_element_container" // The transition name to be matched in Activity B.
            )
            context.startActivity(intent, options.toBundle())
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
            lifecycleOwner(this@ModifyScheduleActivity)
        }
        viewBinding.tvDate.setOnClickListener {
            datePickDialog.show()
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
            lifecycleOwner(this@ModifyScheduleActivity)
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


    override fun setupViewBinding(inflater: LayoutInflater): ActivityModifyPlanBinding =
        ActivityModifyPlanBinding.inflate(inflater)

}