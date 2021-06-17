package spica.lemon.plan.ui.splash


import android.view.LayoutInflater
import android.view.ViewGroup
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentSecondBinding


class SecondFragment : BaseFragment<FragmentSecondBinding>() {

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSecondBinding =
        FragmentSecondBinding.inflate(inflater, container, false)

    override fun init() {

    }


}
