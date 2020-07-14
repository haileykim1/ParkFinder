package com.example.parkfinder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.os.AsyncTask
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.jsoup.*;
import org.jsoup.parser.Parser
import java.lang.ref.WeakReference
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }



    private fun init() {
        title="ParkFinder : " + resources.getString(R.string.nav_park_search)
        loadFragment(SearchFragment())

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){

                R.id.nav_search-> {
                    title="ParkFinder : " + resources.getString(R.string.nav_park_search)
                    loadFragment(SearchFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_bookmark-> {
                    title="ParkFinder : " + resources.getString(R.string.nav_park_bookmark)
                    loadFragment(BookmarkFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_setting-> {
                    title="ParkFinder : " + resources.getString(R.string.nav_setting)
                    loadFragment(SettingFragment())
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false
        }

        (navView as NavigationView).setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1-> {
                    //Toast.makeText(this, "앱 버전 정보", Toast.LENGTH_SHORT).show()
                    drawerInfo(getString(R.string.item1des)).show(supportFragmentManager, "drawerItem")
                    return@setNavigationItemSelectedListener true
                }
                R.id.item2-> {
                    //Toast.makeText(this, "Application 사용 설명", Toast.LENGTH_SHORT).show()
                    drawerInfo(getString(R.string.item2des)).show(supportFragmentManager, "drawerItem")
                    return@setNavigationItemSelectedListener true
                }
                R.id.item3-> {
                    //Toast.makeText(this, "개발자 정보", Toast.LENGTH_SHORT).show()
                    drawerInfo(getString(R.string.item3des)).show(supportFragmentManager, "drawerItem")
                    return@setNavigationItemSelectedListener true
                }
                R.id.item4-> {
                    //Toast.makeText(this, "Data 저작권/출처 정보", Toast.LENGTH_SHORT).show()
                    drawerInfo(getString(R.string.item4des)).show(supportFragmentManager, "drawerItem")
                    return@setNavigationItemSelectedListener true
                }
            }
            false
        }


    }

    private fun loadFragment(frag: Fragment) {
        //load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameView, frag)
        //transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_actions, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.action_drawer -> {
                //val i = Intent(this, SettingsActivity::class.java)
                //startActivity(i)
                //drawer 펼치는 action
                drawerLayout.openDrawer(GravityCompat.END)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    public fun setThemeCoral(){
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.CCoral)))

    }

    public fun setThemeDarkViolet(){
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.CDarkViolet)))
    }

    public fun setThemeTurquoise(){
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.CTurquoise)))
    }

    public fun setThemeLightSkyBlue(){
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.CLightSkyBlue)))
    }
}
