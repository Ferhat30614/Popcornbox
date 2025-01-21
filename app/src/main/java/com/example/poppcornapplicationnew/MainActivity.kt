

package com.example.poppcornapplicationnew

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

        binding.specialToolbar.setNavigationOnClickListener(){

            onBackPressedDispatcher.onBackPressed()


        }




        controller.addOnDestinationChangedListener{controller,destination,aaa->

            when(destination.id){

                R.id.movieDetails->{
                    binding.toolbar.visibility=View.GONE
                    binding.bottomNav.visibility=View.GONE
                    binding.specialToolbar.visibility=View.VISIBLE
                    binding.specialToolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.Kırmızı))





                    Log.e("SpecialToolbar", "55555555")
                }
                else->{
                    binding.toolbar.visibility=View.VISIBLE
                    binding.bottomNav.visibility=View.VISIBLE
                    binding.specialToolbar.visibility=View.GONE
                    Log.e("SpecialToolbar", "Special Toolbar shown")

                }


            }//sdcdc

        }





    }

    override fun onBackPressed() {

        if(binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }else{
            onBackPressedDispatcher.onBackPressed()
        }

    }
}