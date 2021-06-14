package spica.lemon.plan.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.LinearLayout
import spica.lemon.plan.R
import spica.lemon.plan.databinding.ViewSettingItemBinding

/**
 * 设置的横向小ITEM
 */
class SettingItem : LinearLayout {

  private var viewBinding: ViewSettingItemBinding

  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
    initView(attrs)
  }

  constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
  ) {
    initView(attrs)
  }

  init {
    inflate(context, R.layout.view_setting_item, this)
    viewBinding = ViewSettingItemBinding.bind(this)
  }

  private fun initView(attrs: AttributeSet) {
    val array: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingItem)
    viewBinding.tvTitle.text = array.getText(R.styleable.SettingItem_name)
    viewBinding.image.background = (array.getDrawable(R.styleable.SettingItem_image_src))
    array.recycle()
  }
}
