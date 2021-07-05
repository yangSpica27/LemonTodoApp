package spica.lemon.plan.ui.home

import android.view.*
import androidx.fragment.app.viewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.drakeet.multitype.MultiTypeAdapter
import com.fondesa.recyclerviewdivider.dividerBuilder
import spica.lemon.plan.R
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentHomeBinding
import spica.lemon.plan.databinding.LayoutFolderBinding
import spica.lemon.plan.model.Schedule

/**
 * 主页
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var adapter: MultiTypeAdapter

    private val items: ArrayList<Schedule> = arrayListOf()

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var dialogBinding: LayoutFolderBinding

    private lateinit var folderDialog: MaterialDialog


    private val moreSettingDialog: MaterialDialog by lazy {
        MaterialDialog(requireContext()).apply {
            lifecycleOwner(this@HomeFragment)
            title(R.string.more_setting)
            customView(R.layout.layout_home_setting)
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)


    override fun init() {
        initDialog()
        initToolbar()
        initRecyclerView()
    }


    /**
     * 初始化顶栏
     */
    private fun initToolbar() {
        viewBinding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_folder -> {
                    folderDialog.show()
                    return@setOnMenuItemClickListener true
                }
                R.id.action_settings -> {
                    moreSettingDialog.show()
                    return@setOnMenuItemClickListener true
                }
            }
            return@setOnMenuItemClickListener false
        }
    }

    /**
     * 初始化底部弹窗
     */
    private fun initDialog() {
        dialogBinding = LayoutFolderBinding.inflate(LayoutInflater.from(requireContext()))
        folderDialog = MaterialDialog(requireContext(), BottomSheet())
        folderDialog.customView(
            view = dialogBinding.root, noVerticalPadding = true,
            dialogWrapContent = true, scrollable = false
        )
        folderDialog.cornerRadius(4F)
        folderDialog.setPeekHeight(500.dp)
        folderDialog.lifecycleOwner(viewLifecycleOwner)
        dialogBinding.btnCancel.setOnClickListener {
            folderDialog.dismiss()
        }
    }

    /**
     * 初始化主页列表
     */
    private fun initRecyclerView() {
        adapter = MultiTypeAdapter(items)
        adapter.register(HomeListDelegate(requireActivity()))
        adapter.setHasStableIds(true)
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