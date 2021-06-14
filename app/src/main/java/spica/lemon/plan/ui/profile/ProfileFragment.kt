package spica.lemon.plan.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentProfileBinding

/**
 * 个人配置页
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
  override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding =
    FragmentProfileBinding.inflate(inflater, container, false)

  override fun init() {

  }


}