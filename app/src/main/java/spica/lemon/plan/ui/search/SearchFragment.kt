package spica.lemon.plan.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater, container, false)

    override fun init() {

    }
}