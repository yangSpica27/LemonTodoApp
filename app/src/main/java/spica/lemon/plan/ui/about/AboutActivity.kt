package spica.lemon.plan.ui.about

import android.view.LayoutInflater
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityAboutBinding

/**
 * 关于界面
 */
class AboutActivity : BindingActivity<ActivityAboutBinding>() {
  override fun initializer() {

  }

  override fun setupViewBinding(inflater: LayoutInflater): ActivityAboutBinding = ActivityAboutBinding.inflate(inflater)


}