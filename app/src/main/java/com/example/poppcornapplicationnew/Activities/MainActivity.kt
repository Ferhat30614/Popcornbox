package com.example.poppcornapplicationnew.Activities

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
        val baslik = binding.navigationView.inflateHeaderView(R.layout.navigation_baslik)
        val textViewBaslik = baslik.findViewById<TextView>(R.id.textViewBaslik)
        val radioGroupTürler = baslik.findViewById<RadioGroup>(R.id.radioGroupTürler)
        val radioGroupKategoriler = baslik.findViewById<RadioGroup>(R.id.radioGroupKategoriler)


        textViewBaslik.text = "Film & Dizi"

 // RadioGroup için dinleyici ekliyoruz

        radioGroupTürler.setOnCheckedChangeListener { _, checkedId ->
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

        radioGroupKategoriler.setOnCheckedChangeListener { _, checkedId ->
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

        binding.specialToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

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
                    Log.e("SpecialToolbar", "55555555")
                }
                R.id.TVShowDetails -> {
                    binding.toolbar.visibility = View.GONE
                    binding.bottomNav.visibility = View.GONE
                    binding.specialToolbar.visibility = View.VISIBLE
                    binding.specialToolbar.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.gray
                    ))
                    binding.specialToolbar.title = "Film adı"
                    Log.e("SpecialToolbar", "55555555")
                }
                else -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.bottomNav.visibility = View.VISIBLE
                    binding.specialToolbar.visibility = View.GONE
                    Log.e("SpecialToolbar", "Special Toolbar shown")
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
