package spica.lemon.plan.ui.modifyplan

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityModifyPlanBinding


/**
 * 自定义计划内容
 */
class ModifyActivity : BindingActivity<ActivityModifyPlanBinding>() {


  override fun initializer() {

  }

  companion object {

    fun startActivity(context: Activity) {
      val intent = Intent(context, ModifyActivity::class.java)
      context.startActivity(intent)
    }
  }

  override fun setupViewBinding(inflater: LayoutInflater): ActivityModifyPlanBinding = ActivityModifyPlanBinding.inflate(inflater)
}