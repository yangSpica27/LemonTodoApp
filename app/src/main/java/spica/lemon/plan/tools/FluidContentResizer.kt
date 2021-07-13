package spica.lemon.plan.tools

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.util.Log
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

object FluidContentResizer {

    private var heightAnimator: ValueAnimator = ObjectAnimator()

    fun listen(activity: Activity) {
        Log.e("绑定++++：", "1")
        val viewHolder = ActivityViewHolder.createFrom(activity)
        Log.e("绑定++++：", "2")
        KeyboardVisibilityDetector.listen(viewHolder) {
            Log.e("检测到键盘状态变化u：", "${it.visible}")
            animateHeight(viewHolder, it)
        }
        viewHolder.onDetach {
            heightAnimator.cancel()
            heightAnimator.removeAllUpdateListeners()
        }
    }

    private fun animateHeight(viewHolder: ActivityViewHolder, event: KeyboardVisibilityChanged) {
        val contentView = viewHolder.contentView
        contentView.setHeight(event.contentHeightBeforeResize)

        heightAnimator.cancel()
        heightAnimator = ObjectAnimator.ofInt(event.contentHeightBeforeResize, event.contentHeight)
        heightAnimator.interpolator = FastOutSlowInInterpolator()
        heightAnimator.duration = 300L
        heightAnimator.addUpdateListener {
            Log.e("anim：", "${it.animatedValue as Int}%")
            contentView.setHeight(it.animatedValue as Int)
        }
        heightAnimator.start()
    }

    private fun View.setHeight(height: Int) {
        val params = layoutParams
        params.height = height
        layoutParams = params
    }
}