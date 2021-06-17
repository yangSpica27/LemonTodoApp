package spica.lemon.plan.ui.splash

import android.animation.ArgbEvaluator
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import spica.lemon.plan.R
import spica.lemon.plan.base.BindingActivity
import spica.lemon.plan.databinding.ActivitySplashBinding
import spica.lemon.plan.ui.main.MainActivity


private const val NUM_PAGES = 3
private const val MEDIUM_DISTANCE = 400
private const val FAR_DISTANCE = 600

/**
 * 欢迎页
 */
class SplashActivity : BindingActivity<ActivitySplashBinding>() {

  lateinit var context: Context
  lateinit var evaluator: ArgbEvaluator
  lateinit var colors: IntArray

  override fun initializer() {
    evaluator = ArgbEvaluator()
    context = this
    initColorsList()
    initPager()
    viewBinding.start.setOnClickListener {
      startActivity(Intent(this, MainActivity::class.java))
    }
  }

  private fun initPager() {
    val pagerAdapter = ScreenSlidePagerAdapter(this)
    viewBinding.pager.adapter = pagerAdapter
    manageViewPagerScrollActions(pagerAdapter)
  }


  private fun initColorsList() {
    colors = intArrayOf(
      context.getColor(R.color.coral),
      context.getColor(R.color.late_gray),
      context.getColor(R.color.teal_200)
    )
  }

  private fun manageViewPagerScrollActions(pagerAdapter: ScreenSlidePagerAdapter) {
    viewBinding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

      override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
      }

      override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
      ) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        changePageBackgroundColorOnSwipe(position, pagerAdapter, positionOffset)
        showDotsAndStars(position, positionOffset)
      }
    })
  }


  private fun showDotsAndStars(position: Int, positionOffset: Float) {
    when (position) {
      0 -> animateDots(positionOffset)
      1 -> animateStars(positionOffset)
    }
  }

  private fun animateStars(positionOffset: Float) {
    viewBinding.dots.animate().alpha(1 - positionOffset).setDuration(0).start()
    hideVegtablesObjectWithAnimation(positionOffset)
    showSpaceObjectsWithAnimation(positionOffset)
  }

  private fun showSpaceObjectsWithAnimation(positionOffset: Float) {
    viewBinding.mars.animate().translationX(0 - (MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
      .setDuration(0).start()
    viewBinding.neptune.animate().translationX((MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
      .setDuration(0).start()
    viewBinding.moon.animate().translationX((MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
      .setDuration(0).start()
    viewBinding.saturn.animate().translationX((MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
      .setDuration(0).start()
    viewBinding.venus.animate().translationY(0 - (FAR_DISTANCE - (FAR_DISTANCE * positionOffset)))
      .setDuration(0).start()
    viewBinding.sun.animate().translationY(0 - (FAR_DISTANCE - (FAR_DISTANCE * positionOffset)))
      .setDuration(0).start()
  }

  private fun hideVegtablesObjectWithAnimation(positionOffset: Float) {
    viewBinding.nightDots.animate().alpha(positionOffset).setDuration(0).start()
    viewBinding.tomato.animate().translationX(0 - ((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
    viewBinding.potato.animate().translationX(0 - ((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
    viewBinding.onion.animate().translationX(((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
    viewBinding.pickel.animate().translationX(((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
    viewBinding.carrots.animate().translationY(0 - ((FAR_DISTANCE * positionOffset))).setDuration(0).start()
    viewBinding.rightHalfCircle.animate().translationY(0 - ((FAR_DISTANCE * positionOffset))).setDuration(0).start()
  }

  private fun animateDots(positionOffset: Float) {
    hidePlantesObjectsWithAnimation(positionOffset)
    viewBinding.dots.animate().alpha(positionOffset).setDuration(0).start()
    showVegtablesObjectsWithAnimation(positionOffset)
  }

  private fun hidePlantesObjectsWithAnimation(positionOffset: Float) {
    animateFragmentObjects(positionOffset)
    viewBinding.leaf.animate().translationX((0 - (MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
    viewBinding.sanawbar.animate().translationX(((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
    viewBinding.earsTree.animate().translationX(((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
    viewBinding.upperPurpleRose.animate().translationY(0 - ((FAR_DISTANCE * positionOffset))).setDuration(0).start()
    viewBinding.purpleRose.animate().translationY(0 - ((FAR_DISTANCE * positionOffset))).setDuration(0).start()
    viewBinding.pointsLeaf.animate().translationY(0 - ((FAR_DISTANCE * positionOffset))).setDuration(0).start()
  }

  private fun animateFragmentObjects(positionOffset: Float) {
    if (findViewById<View>(R.id.two_papers) != null) {
      findViewById<View>(R.id.two_papers).animate().translationY(((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
    }
  }

  private fun showVegtablesObjectsWithAnimation(positionOffset: Float) {
    viewBinding.tomato.animate().translationX(0 - (MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
      .setDuration(0).start()
    viewBinding.potato.animate().translationX(0 - (MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
      .setDuration(0).start()
    viewBinding.onion.animate().translationX((MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
      .setDuration(0).start()
    viewBinding.pickel.animate().translationX((MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
      .setDuration(0).start()
    viewBinding.carrots.animate().translationY(0 - (FAR_DISTANCE - (FAR_DISTANCE * positionOffset)))
      .setDuration(0).start()
    viewBinding.rightHalfCircle.animate()
      .translationY(0 - (FAR_DISTANCE - (FAR_DISTANCE * positionOffset))).setDuration(0)
      .start()
  }

  private fun changePageBackgroundColorOnSwipe(
    position: Int,
    pagerAdapter: ScreenSlidePagerAdapter,
    positionOffset: Float
  ) {
    if (position < pagerAdapter.itemCount - 1 && position < colors.size - 1) {
      viewBinding.pager.setBackgroundColor(
        (evaluator.evaluate(
          positionOffset,
          colors[position],
          colors[position + 1]
        ) as Int)
      )
    } else
      viewBinding.pager.setBackgroundColor(colors[colors.size - 1])
  }


  private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
      return when (position) {
        0 -> {
          FirstFragment()
        }
        1 -> {
          SecondFragment()
        }
        else -> {
          ThirdFragment()
        }
      }
    }
  }


  override fun setupViewBinding(inflater: LayoutInflater): ActivitySplashBinding = ActivitySplashBinding.inflate(inflater)
}