import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.parcial.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuración de la Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Referencias al DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        // Fragment container con NavHostFragment
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fc_main) as NavHostFragment

        // Configuración del AppBarConfiguration
        val appBarConfiguration = AppBarConfiguration.Builder(navHostFragment.navController.graph)
            .setOpenableLayout(drawerLayout)
            .build()

        // Conectar el NavController con la ActionBar y el NavigationView
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navigationView, navHostFragment.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = navHostFragment.navController
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp()
    }
}
