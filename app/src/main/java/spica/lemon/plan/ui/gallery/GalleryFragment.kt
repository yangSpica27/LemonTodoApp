package spica.lemon.plan.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import spica.lemon.plan.base.BaseFragment
import spica.lemon.plan.databinding.FragmentGalleryBinding

class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {

  override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGalleryBinding = FragmentGalleryBinding.inflate(inflater, container, false)

  override fun init() {

  }

}