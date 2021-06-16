package spica.lemon.plan.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import spica.lemon.plan.R
import spica.lemon.plan.databinding.ActivityMainBinding

/**
 * 最外层Fragments的容器
 */
class MainActivity : AppCompatActivity() {


  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.fab.setOnClickListener { view ->
      Snackbar.make(view, "添加您的计划", Snackbar.LENGTH_LONG)
        .setAction("OK", null).show()
    }

    val navController = findNavController(R.id.nav_host_fragment_content_main)
    binding.bottomNavigation.setupWithNavController(navController)
  }


  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    return navController.navigateUp()
  }
}