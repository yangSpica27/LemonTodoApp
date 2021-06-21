package spica.lemon.plan.ui.login

import android.view.LayoutInflater
import com.gyf.immersionbar.ktx.immersionBar
import spica.lemon.plan.R
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityLoginBinding

class LoginActivity : BindingActivity<ActivityLoginBinding>() {

  override fun initializer() {
    immersionBar {
      statusBarColor(R.color.white)
      navigationBarColor(R.color.white)
      statusBarDarkFont(true)
    }

    setSupportActionBar(viewBinding.toolbar)
    with(supportActionBar) {
      this?.setHomeButtonEnabled(true)
      this?.setDisplayShowTitleEnabled(false)
      this?.setDisplayHomeAsUpEnabled(true)
    }
  }

  override fun setupViewBinding(inflater: LayoutInflater): ActivityLoginBinding = ActivityLoginBinding.inflate(inflater)
}