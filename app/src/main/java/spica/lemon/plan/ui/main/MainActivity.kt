package spica.lemon.plan.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import spica.lemon.plan.R
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivityMainBinding
import spica.lemon.plan.ui.modifyplan.ModifyScheduleActivity

/**
 * 主页容器
 */
@AndroidEntryPoint
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
                    viewBinding.fabPlus.show()
                    viewBinding.mainViewpager.currentItem = MainPagerAdapter.HOME
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_dashboard -> {
                    viewBinding.mainViewpager.currentItem = MainPagerAdapter.GALLERY
                    viewBinding.fabPlus.hide()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_profile -> {
                    viewBinding.mainViewpager.currentItem = MainPagerAdapter.PROFILE
                    viewBinding.fabPlus.hide()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_search ->{
                    viewBinding.mainViewpager.currentItem = MainPagerAdapter.SEARCH
                    viewBinding.fabPlus.hide()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }

        viewBinding.fabPlus.setOnClickListener {
            ModifyScheduleActivity.startActivity(viewBinding.fabPlus, this)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setupViewBinding(inflater: LayoutInflater):
            ActivityMainBinding = ActivityMainBinding.inflate(inflater)


}