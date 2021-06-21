package spica.lemon.plan.ui.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentProfileBinding
import spica.lemon.plan.ui.login.LoginActivity

/**
 * 个人配置页
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
  override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding =
    FragmentProfileBinding.inflate(inflater, container, false)

  override fun init() {
    viewBinding.ivAvatar.setOnClickListener {
      val intent = Intent(requireContext(), LoginActivity::class.java)
      requireActivity().startActivity(intent)
    }
  }


}