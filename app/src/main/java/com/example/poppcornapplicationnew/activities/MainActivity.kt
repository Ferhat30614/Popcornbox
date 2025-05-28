package com.example.poppcornapplicationnew.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.poppcornapplicationnew.R
import com.example.poppcornapplicationnew.databinding.ActivityMainBinding
import com.example.poppcornapplicationnew.LikeDao



class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var likeDao: LikeDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //  likes tablosundaki tüm veriler burada CSV formatında dışarıya aktarılıyor.
        likeDao = LikeDao(this)
        likeDao.exportLikesToCSV(this)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment
        val controller = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNav, controller)
        NavigationUI.setupWithNavController(binding.navigationView, controller)

        binding.toolbar.title = "Popcorn Application"
        setSupportActionBar(binding.toolbar)

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
                    Toast.makeText(this, "Aksiyon Seçildi!", Toast.LENGTH_SHORT).show()

                    var currentDestinationId=controller.currentDestination?.id
                    binding.drawer.closeDrawer(GravityCompat.START)

                    if (currentDestinationId==R.id.filmlerFragment || currentDestinationId==R.id.actionMovieFragment
                        || currentDestinationId==R.id.adventureMovieFragment || currentDestinationId==R.id.comedyMovieFragment){

                        controller.navigate(R.id.actionMovieFragment)

                    }else if(currentDestinationId==R.id.dizilerFragment || currentDestinationId==R.id.actionSeriesFragment
                        || currentDestinationId==R.id.adventureSeriesFragment || currentDestinationId==R.id.comedySeriesFragment){

                        controller.navigate(R.id.actionSeriesFragment)

                    }else{

                        Toast.makeText(this, "Şu anda başka bir sayfadasınız.", Toast.LENGTH_SHORT).show()
                    }




                }
                R.id.radioButtonKomedi -> {

                    binding.drawer.closeDrawer(GravityCompat.START)
                    Toast.makeText(this, "Komedi seçildi!", Toast.LENGTH_SHORT).show()

                    var currentDestinationId=controller.currentDestination?.id

                    if (currentDestinationId==R.id.filmlerFragment || currentDestinationId==R.id.actionMovieFragment
                        || currentDestinationId==R.id.adventureMovieFragment || currentDestinationId==R.id.comedyMovieFragment){

                        controller.navigate(R.id.comedyMovieFragment)

                    }else if(currentDestinationId==R.id.dizilerFragment || currentDestinationId==R.id.actionSeriesFragment
                        || currentDestinationId==R.id.adventureSeriesFragment || currentDestinationId==R.id.comedySeriesFragment){

                        controller.navigate(R.id.comedySeriesFragment)

                    }else{


                        Toast.makeText(this, "Şu anda başka bir sayfadasınız.", Toast.LENGTH_SHORT).show()
                    }




                }
                R.id.radioButtonMacera -> {
                    // Diziler fragment'ine geç
                    binding.drawer.closeDrawer(GravityCompat.START)
                    Toast.makeText(this, "Macera seçildi!", Toast.LENGTH_SHORT).show()


                    var currentDestinationId=controller.currentDestination?.id

                    if (currentDestinationId==R.id.filmlerFragment || currentDestinationId==R.id.actionMovieFragment
                        || currentDestinationId==R.id.adventureMovieFragment || currentDestinationId==R.id.comedyMovieFragment){

                        controller.navigate(R.id.adventureMovieFragment)

                    }else if(currentDestinationId==R.id.dizilerFragment || currentDestinationId==R.id.actionSeriesFragment
                        || currentDestinationId==R.id.adventureSeriesFragment || currentDestinationId==R.id.comedySeriesFragment){

                        controller.navigate(R.id.adventureSeriesFragment)

                    }else{


                        Toast.makeText(this, "Şu anda başka bir sayfadasınız.", Toast.LENGTH_SHORT).show()
                    }

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search,menu)
        val item=menu.findItem(R.id.action_search)
        val searchView=item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.e("onQueryTextSubmit",query.toString())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e("onQueryTextChange",newText.toString())

        return true
    }
}
