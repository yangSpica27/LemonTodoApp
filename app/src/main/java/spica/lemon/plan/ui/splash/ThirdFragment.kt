package spica.lemon.plan.ui.splash


import android.view.LayoutInflater
import android.view.ViewGroup
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentThirdBinding


class ThirdFragment : BaseFragment<FragmentThirdBinding>() {

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentThirdBinding =
        FragmentThirdBinding.inflate(inflater, container, false)

    override fun init() {

    }


}
