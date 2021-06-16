package spica.lemon.plan.ui.modifyplan

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityModifyPlanBinding


/**
 * 自定义计划内容
 */
class ModifyActivity : BindingActivity<ActivityModifyPlanBinding>() {


  override fun initializer() {

  }

  companion object {

    fun startActivity(view: TransformationLayout, context: Activity) {
      val intent = Intent(context, ModifyActivity::class.java)
      TransformationCompat.startActivity(view, intent)
    }
  }

  override fun setupViewBinding(inflater: LayoutInflater): ActivityModifyPlanBinding = ActivityModifyPlanBinding.inflate(inflater)
}