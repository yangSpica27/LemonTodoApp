package spica.lemon.plan.ui.profile


import android.view.LayoutInflater
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.DialogLoginBinding
import spica.lemon.plan.databinding.FragmentProfileBinding


/**
 * 个人配置页
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

  private lateinit var loginDialog: MaterialDialog

  private lateinit var loginBinding: DialogLoginBinding


  override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding =
    FragmentProfileBinding.inflate(inflater, container, false)

  override fun init() {
    initLoginDialog()
    viewBinding.ivAvatar.setOnClickListener {
      loginDialog.show()
    }
  }


  /**
   * 初始化登录dialog
   */
  private fun initLoginDialog() {
    loginBinding = DialogLoginBinding.inflate(LayoutInflater.from(requireContext()))
    loginDialog = MaterialDialog(requireContext(), BottomSheet())
    with(loginDialog) {
      customView(view = loginBinding.root, scrollable = true, noVerticalPadding = true)
      setPeekHeight(500.dp)
      lifecycleOwner(viewLifecycleOwner)
    }
  }


}