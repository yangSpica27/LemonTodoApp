package spica.lemon.plan.layout

import android.content.Context
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import spica.lemon.plan.R


/**
 * HomeFragment的布局
 */
class HomeFragmentLayout(context: Context) : CustomLayout(context) {

    //顶部导航栏
    val toolbar = MaterialToolbar(context, null, R.style.ToolbarStyle).autoAddViewMaxWidth {
        setPadding(12.dp)
        setNavigationIcon(R.drawable.ic_dashboard)
        title = context.getString(R.string.app_name)
        setTitleTextColor(context.getColor(R.color.white_smoke))
        MenuInflater(context).inflate(R.menu.main, menu)
    }


    //列表
    val recyclerView = RecyclerView(context).autoAddViewMax(matchParent,300.dp) {
        background = ContextCompat.getDrawable(context, R.drawable.bg_half_rounded_light)
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val params = CoordinatorLayout.LayoutParams(matchParent, matchParent)
        params.behavior = AppBarLayout.ScrollingViewBehavior()
        this.layoutParams = params
    }


    init {
        setBackgroundColor(context.getColor(R.color.white_smoke))
        val layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
        this.layoutParams = layoutParams
    }


    override fun onMeasureChildren(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        toolbar.autoMeasure()
        recyclerView.measure(
            measuredWidthWithMargins,
            height - toolbar.measuredHeightWithMargins
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        toolbar.layout(x = 0, y = 0)
        recyclerView.layout(x = 0, y = toolbar.bottom + recyclerView.marginTop)
    }
}