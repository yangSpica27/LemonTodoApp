package spica.lemon.plan.ui.modifyplan

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.gyf.immersionbar.ktx.immersionBar
import spica.lemon.plan.R
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityModifyPlanBinding


/**
 * 自定义计划内容
 */
class ModifyScheduleActivity : BindingActivity<ActivityModifyPlanBinding>() {


  override fun initializer() {
    initStatusBar()
    initToolbar()
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




  override fun setupViewBinding(inflater: LayoutInflater): ActivityModifyPlanBinding = ActivityModifyPlanBinding.inflate(inflater)
}