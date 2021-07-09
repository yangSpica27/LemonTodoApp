package spica.lemon.plan.tools

import android.content.res.Resources
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver

/**
 * view测量的拓展方法
 */
fun View.onNextMeasure(runnable: () -> Unit) {
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            if (isLaidOut) {
                viewTreeObserver.removeOnPreDrawListener(this)
                runnable()

            } else if (visibility == View.GONE) {
                Log.w(
                    "View's visibility is set to Gone. It'll never be measured: %s",
                    resourceName()
                )
                viewTreeObserver.removeOnPreDrawListener(this)
            }
            return true
        }
    })
}

fun View.resourceName(): String {
    var name = "<nameless>"
    try {
        name = resources.getResourceEntryName(id)
    } catch (e: Resources.NotFoundException) {
        e.printStackTrace()
    }
    return name
}