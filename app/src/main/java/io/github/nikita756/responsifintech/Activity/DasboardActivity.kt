package io.github.nikita756.responsifintech.Activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.nikita756.responsifintech.Fragment.HomeFragment
import io.github.nikita756.responsifintech.Fragment.SettingFragment
import io.github.nikita756.responsifintech.R
import io.github.nikita756.responsifintech.qrscanner.QRScannerFragment
import io.github.nikita756.responsifintech.scanner_history.ScannedHistoryFragment

class DasboardActivity : AppCompatActivity() {

    private lateinit var currentFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dasboard)

        supportFragmentManager.beginTransaction().replace(R.id.nav_container, HomeFragment()).commit()
        val bottomNav : BottomNavigationView = findViewById(R.id.bottom_nav_menu)

        bottomNav.setOnNavigationItemReselectedListener(navListener)

    }
    val navListener = BottomNavigationView.OnNavigationItemReselectedListener {
        when(it.itemId){
            R.id.navigation_home -> {
                currentFragment=HomeFragment()
            }
            R.id.navigation_payement -> {
                currentFragment=QRScannerFragment()
            }
            R.id.navigation_setting -> {
                currentFragment=SettingFragment()
            }
            R.id.navigation_history -> {
                currentFragment=ScannedHistoryFragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.nav_container, currentFragment).commit()
        true
    }
}