package spica.lemon.plan.ui.splash


import android.view.LayoutInflater
import android.view.ViewGroup
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentFirstBinding


class FirstFragment : BaseFragment<FragmentFirstBinding>() {
    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFirstBinding =
        FragmentFirstBinding.inflate(inflater, container, false)

    override fun init() {

    }

}
