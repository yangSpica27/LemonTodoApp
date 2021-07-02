package spica.lemon.plan.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import spica.lemon.plan.ui.gallery.GalleryFragment
import spica.lemon.plan.ui.home.HomeFragment
import spica.lemon.plan.ui.profile.ProfileFragment
import spica.lemon.plan.ui.search.SearchFragment

/**
 * 主页的viewPager的adapter
 */
class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val fragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        HOME to { HomeFragment() },
        GALLERY to { GalleryFragment() },
        SEARCH to { SearchFragment() },
        PROFILE to { ProfileFragment() },
    )

    override fun getItemCount(): Int = fragmentsCreators.size


    override fun createFragment(position: Int): Fragment {
        return fragmentsCreators[position]?.invoke() ?: HomeFragment()
    }

    companion object {
        const val HOME = 0
        const val GALLERY = 1
        const val SEARCH = 2
        const val PROFILE = 3
    }
}