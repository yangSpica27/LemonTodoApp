package spica.lemon.plan.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.viewpager2.widget.ViewPager2
import spica.lemon.plan.R
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityMainBinding

/**
 * 主页容器
 */
class MainActivity : BindingActivity<ActivityMainBinding>() {


  override fun initializer() {

    with(viewBinding.mainViewpager) {
      adapter = MainPagerAdapter(this@MainActivity)
      orientation = ViewPager2.ORIENTATION_HORIZONTAL
      offscreenPageLimit = 3
      isUserInputEnabled = false
    }

    viewBinding.bottomNavBar.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.nav_home -> {
          viewBinding.mainViewpager.currentItem = MainPagerAdapter.HOME
          return@setOnNavigationItemSelectedListener true
        }
        R.id.nav_dashboard -> {
          viewBinding.mainViewpager.currentItem = MainPagerAdapter.GALLERY
          return@setOnNavigationItemSelectedListener true
        }

        R.id.nav_profile -> {
          viewBinding.mainViewpager.currentItem = MainPagerAdapter.PROFILE
          return@setOnNavigationItemSelectedListener true
        }
      }
      return@setOnNavigationItemSelectedListener false
    }
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun setupViewBinding(inflater: LayoutInflater):
      ActivityMainBinding = ActivityMainBinding.inflate(inflater)


}