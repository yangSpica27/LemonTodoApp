package spica.lemon.plan.ui.home

import android.view.*
import androidx.fragment.app.viewModels
import com.drakeet.multitype.MultiTypeAdapter
import com.fondesa.recyclerviewdivider.dividerBuilder
import spica.lemon.plan.R
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentHomeBinding
import spica.lemon.plan.model.Plan
import spica.lemon.plan.view.FolderDialog

/**
 * 主页
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  private lateinit var adapter: MultiTypeAdapter

  private val items: ArrayList<Plan> = arrayListOf()

  private val homeViewModel: HomeViewModel by viewModels()


  private lateinit var folderDialog: FolderDialog

  override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
    FragmentHomeBinding.inflate(inflater, container, false)



  override fun init() {
    context
    folderDialog = FolderDialog()
    adapter = MultiTypeAdapter(items)
    adapter.register(HomeListDelegate(requireActivity()))
    adapter.setHasStableIds(true)

    viewBinding.toolbar.setOnMenuItemClickListener {
      when (it.itemId) {
        R.id.action_folder -> {
          return@setOnMenuItemClickListener true
        }
      }
      return@setOnMenuItemClickListener false
    }

    requireContext()
      .dividerBuilder()
      .showLastDivider()
      .size(20.dp)
      .colorRes(android.R.color.transparent)
      .build()
      .addTo(viewBinding.rvList)

    viewBinding.rvList.adapter = adapter
    homeViewModel.list.observe(this, {
      items.clear()
      items.addAll(it)
    })
  }


}