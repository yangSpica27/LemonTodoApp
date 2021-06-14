package spica.lemon.plan.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  private lateinit var homeViewModel: HomeViewModel
  override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
    FragmentHomeBinding.inflate(inflater, container, false)

  override fun init() {

  }


}