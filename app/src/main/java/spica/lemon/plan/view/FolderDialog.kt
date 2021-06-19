package spica.lemon.plan.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import spica.lemon.plan.databinding.LayoutFolderBinding


class FolderDialog : DialogFragment() {

  private lateinit var viewBinding: LayoutFolderBinding


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    val window: Window? = requireDialog().window
    window?.requestFeature(Window.FEATURE_NO_TITLE)

    viewBinding = LayoutFolderBinding.inflate(inflater, container, false)

    return viewBinding.root
  }
}