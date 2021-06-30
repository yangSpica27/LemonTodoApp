package spica.lemon.plan.ui.modifyplan

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import com.drakeet.multitype.MultiTypeAdapter
import com.gyf.immersionbar.ktx.immersionBar
import dagger.hilt.android.AndroidEntryPoint
import spica.lemon.plan.R
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityModifyPlanBinding
import spica.lemon.plan.model.ScheduleItem


/**
 * 自定义计划内容
 */
@AndroidEntryPoint
class ModifyScheduleActivity : BindingActivity<ActivityModifyPlanBinding>() {

    private lateinit var childScheduleAdapter: MultiTypeAdapter


    private val items: List<ScheduleItem> = listOf(
        ScheduleItem(true, "事件1"),
        ScheduleItem(false, "事件1"),
        ScheduleItem(true, "事件1"),
        ScheduleItem(true, "事件1"),
        ScheduleItem(true, "事件1"),
        ScheduleItem(true, "事件1"),
        ScheduleItem(true, "事件1"),
    )


    private val viewModel by viewModels<ModifyScheduleViewModel>()

    override fun initializer() {
        initStatusBar()
        initToolbar()
        initRecyclerView()
        initData()
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
        setSupportActionBar(viewBinding.toolbar)
        supportActionBar?.apply {
            title = "创建新的任务"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {
        fun startActivity(startView: View, context: Activity) {
            val intent = Intent(context, ModifyScheduleActivity::class.java)
            val bundle = ActivityOptions.makeSceneTransitionAnimation(context).toBundle()
            context.startActivity(intent, bundle)
        }
    }

    //初始化顶部菜单
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.modify_schedule, menu)
    }

    //初始化子计划的RecyclerView
    private fun initRecyclerView() {
        childScheduleAdapter = MultiTypeAdapter(items)
        childScheduleAdapter.register(ChildScheduleDelegate())
        viewBinding.rvChildSchedule.adapter = childScheduleAdapter
    }


    private fun initData() {

    }


    override fun setupViewBinding(inflater: LayoutInflater): ActivityModifyPlanBinding =
        ActivityModifyPlanBinding.inflate(inflater)


}