package spica.lemon.plan.ui.main

import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityMainBinding

/**
 * 主页容器
 */
class MainActivity : BindingActivity<ActivityMainBinding>() {


  override fun initializer() {



    viewBinding.mainViewpager.adapter = MainPagerAdapter(this)
    viewBinding.mainViewpager.orientation = ViewPager2.ORIENTATION_VERTICAL
    viewBinding.mainViewpager.offscreenPageLimit = 3
    viewBinding.mainViewpager.isUserInputEnabled = false
  }

  override fun setupViewBinding(inflater: LayoutInflater):
          ActivityMainBinding = ActivityMainBinding.inflate(inflater)


}