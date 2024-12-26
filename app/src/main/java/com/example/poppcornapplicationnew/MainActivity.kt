package com.example.poppcornapplicationnew

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.poppcornapplicationnew.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navHostFragment=supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment
        val controller=navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNav,controller)



        NavigationUI.setupWithNavController(binding.navigationView,controller)

        binding.toolbar.title = "Popcorn Application"

        val toggle = ActionBarDrawerToggle(this,binding.drawer,binding.toolbar,
            0,0)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()


        //yukarısı iyi çalışıyor


        val baslik=binding.navigationView.inflateHeaderView(R.layout.navigation_baslik)
        val textviewbaslik=baslik.findViewById(R.id.textViewBaslik) as TextView

        textviewbaslik.text="Baslik"













    }

    override fun onBackPressed() {

        if(binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }else{
            onBackPressedDispatcher.onBackPressed()
        }

    }
}