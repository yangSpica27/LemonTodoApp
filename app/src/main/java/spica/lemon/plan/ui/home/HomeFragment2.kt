package spica.lemon.plan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import spica.lemon.plan.base.LazyFragment
import spica.lemon.plan.layout.HomeFragmentLayout

class HomeFragment2 : LazyFragment() {


    private lateinit var layout: HomeFragmentLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layout = HomeFragmentLayout(requireContext())
        return layout;
    }

    override fun lazyInit() {

    }
}