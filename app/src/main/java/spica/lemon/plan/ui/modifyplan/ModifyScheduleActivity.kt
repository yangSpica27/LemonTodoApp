package spica.lemon.plan.ui.modifyplan

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.google.android.material.transition.MaterialSharedAxis
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityModifyPlanBinding


/**
 * 自定义计划内容
 */
class ModifyScheduleActivity : BindingActivity<ActivityModifyPlanBinding>() {


  override fun initializer() {

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