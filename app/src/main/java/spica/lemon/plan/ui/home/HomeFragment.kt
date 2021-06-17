package spica.lemon.plan.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.drakeet.multitype.MultiTypeAdapter
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentHomeBinding
import spica.lemon.plan.model.Plan

/**
 * 主页
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  private lateinit var adapter: MultiTypeAdapter


  private val items: ArrayList<Plan> = arrayListOf()

  private val homeViewModel: HomeViewModel by viewModels()

  override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
    FragmentHomeBinding.inflate(inflater, container, false)


  override fun init() {
    with(viewBinding.container) {
      setPadding(paddingLeft, paddingTop + UltimateBarX.getStatusBarHeight(), paddingRight, paddingBottom)
    }
    adapter = MultiTypeAdapter(items)
    adapter.register(HomeListDelegate(requireActivity()))
    adapter.setHasStableIds(true)
//    requireContext()
//      .dividerBuilder()
//      .showFirstDivider()
//      .showLastDivider()
//      .size(20)
//      .build()
//      .addTo(viewBinding.rvList)

    viewBinding.rvList.adapter = adapter
    homeViewModel.list.observe(this, {
      items.clear()
      items.addAll(it)
    })
  }


}