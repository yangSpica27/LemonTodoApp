package spica.lemon.plan.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "敬请期待！"
  }
  val text: LiveData<String> = _text
}