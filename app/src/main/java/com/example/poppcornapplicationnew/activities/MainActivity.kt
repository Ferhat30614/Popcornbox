package com.example.poppcornapplicationnew.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.poppcornapplicationnew.R
import com.example.poppcornapplicationnew.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment
        val controller = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNav, controller)
        NavigationUI.setupWithNavController(binding.navigationView, controller)

        binding.toolbar.title = "Popcorn Application"

        val toggle = ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, 0, 0)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()

        // Navigation Drawer başlığı ve RadioGroup işlemleri
        val title = binding.navigationView.inflateHeaderView(R.layout.navigation_baslik)
        val textViewTitle = title.findViewById<TextView>(R.id.textViewBaslik)
        val radioGroupTypes = title.findViewById<RadioGroup>(R.id.radioGroupTürler)
        val radioGroupCategories = title.findViewById<RadioGroup>(R.id.radioGroupKategoriler)


        textViewTitle.text = "Film & Dizi"

 // RadioGroup için dinleyici ekliyoruz

        radioGroupTypes.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonFilmler -> {
                    // Filmler fragment'ine geç
                    binding.drawer.closeDrawer(GravityCompat.START)
                    controller.navigate(R.id.filmlerFragment)
                }
                R.id.radioButtonDiziler -> {
                    // Diziler fragment'ine geç
                    binding.drawer.closeDrawer(GravityCompat.START)
                    controller.navigate(R.id.dizilerFragment)
                }
            }
        }
// radioGroupKategoriler için işlemler

        radioGroupCategories.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonAksiyon -> {
                    // Filmler fragment'ine geç
                    binding.drawer.closeDrawer(GravityCompat.START)
                    Toast.makeText(this, "Aksiyon Seçildi!", Toast.LENGTH_SHORT).show()



                }
                R.id.radioButtonKomedi -> {
                    // Diziler fragment'ine geç
                    binding.drawer.closeDrawer(GravityCompat.START)
                    Toast.makeText(this, "Komedi seçildi!", Toast.LENGTH_SHORT).show()


                }
                R.id.radioButtonMacera -> {
                    // Diziler fragment'ine geç
                    binding.drawer.closeDrawer(GravityCompat.START)
                    Toast.makeText(this, "Macera seçildi!", Toast.LENGTH_SHORT).show()

                }
            }
        }

        // special Toolbara tıklayınca geriye girt diyoruz  burda
        binding.specialToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        // special Toolbar hangi durumlarda gürünüz hangi durumlarda görünmez
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieDetails -> {
                    binding.toolbar.visibility = View.GONE
                    binding.bottomNav.visibility = View.GONE
                    binding.specialToolbar.visibility = View.VISIBLE
                    binding.specialToolbar.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.gray
                    ))
                    binding.specialToolbar.title = "Film adı"

                }
                R.id.TVShowDetails -> {
                    binding.toolbar.visibility = View.GONE
                    binding.bottomNav.visibility = View.GONE
                    binding.specialToolbar.visibility = View.VISIBLE
                    binding.specialToolbar.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.gray
                    ))
                    binding.specialToolbar.title = "Dizi adı"

                }
                else -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.bottomNav.visibility = View.VISIBLE
                    binding.specialToolbar.visibility = View.GONE
                }
            }
        }
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
