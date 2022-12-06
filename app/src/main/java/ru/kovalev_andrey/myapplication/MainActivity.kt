package ru.kovalev_andrey.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import ru.kovalev_andrey.myapplication.fragments.ListenFragment
import ru.kovalev_andrey.myapplication.fragments.MainFragment
import ru.kovalev_andrey.myapplication.fragments.ProfileFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainFragment) as NavHostFragment?
        val navController = navHostFragment!!.navController
        navController.navigate(R.id.main_fragment)

        if (savedInstanceState != null) {
            navController.restoreState(savedInstanceState)
        }

        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        navigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainFragmentButton -> {
                    navController.saveState()
                    navController.navigate(R.id.main_fragment)
                    return@OnItemSelectedListener true
                }
                R.id.playerFragmentButton -> {
                    navController.saveState()
                    navController.navigate(R.id.player_fragment)
                    return@OnItemSelectedListener true
                }
                R.id.profileFragmentButton -> {
                    navController.saveState()
                    navController.navigate(R.id.profile_fragment)
                    return@OnItemSelectedListener true
                }
            }
            false
        })*/
        val navigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mainFragmentButton -> {
                    loadFragment(MainFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.listenFragmentButton -> {
                    loadFragment(ListenFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profileFragmentButton -> {
                    loadFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFragment, fragment)
        transaction.commit()
    }
}