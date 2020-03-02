package com.mvvm.ati.mail

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.mvvm.ati.mail.custom_view.ContactsCompletionView
import com.mvvm.ati.mail.model.Contacts
import com.mvvm.ati.mail.ui.settings.KEY_CURRENT_THEME
import com.mvvm.ati.mail.ui.settings.SettingsFragment
import com.tokenautocomplete.TokenCompleteTextView
import com.tokenautocomplete.TokenCompleteTextView.TokenListener
import java.util.*


class MainActivity : AppCompatActivity(), TokenListener<Contacts>{

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val currentTheme : Int? = sharedPref.getInt(KEY_CURRENT_THEME, R.style.BlueAppTheme)
        currentTheme?.let { setTheme(currentTheme) }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            writeMailDialog()
           /* Snackbar.make(view, Html.fromHtml("<font color=\"#ffffff\">Tap to open</font>"), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_inbox, R.id.nav_sent,
                R.id.nav_trash, R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        if (menu is MenuBuilder){
            menu.setOptionalIconsVisible(true)
        }
        return true
    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
          if(item.itemId == R.id.action_settings){
              supportFragmentManager.beginTransaction()
                  .replace(R.id.nav_host_fragment,
                      SettingsFragment(), "").addToBackStack(null).commit()
          }else{
              return super.onOptionsItemSelected(item)
          }
          return true
     }

 //   private var searchView: ContactsCompletionView? = null
    private var selectedEmail: List<Contacts>? = arrayListOf()

    private fun writeMailDialog() {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this, R.style.FullScreenDialog)
        val inflater: LayoutInflater = Objects.requireNonNull(this).getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.dialog_write_mail, null, false)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        val PEOPLE = arrayOf("John Smith", "Kate Eckhart", "Emily Sun", "Frodo Baggins")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, PEOPLE)

        val toContacts: ContactsCompletionView = dialogView.findViewById(R.id.to_contacts)
        val ccContacts: ContactsCompletionView = dialogView.findViewById(R.id.cc_contacts)
        val bccContacts: ContactsCompletionView = dialogView.findViewById(R.id.bcc_contacts)




       val contactList = arrayListOf<Contacts>()
       val c = Contacts ()
        c.name = "masum"
        c.email ="masum@gmail.com"
        contactList.add(c)

        val c2 = Contacts ()
        c2.name = "rahim"
        c2.email ="rahim@gmail.com"
        contactList.add(c2)

        val c3 = Contacts ()
        c3.name = "akram"
        c3.email ="akram@gmail.com"
        contactList.add(c3)

        val c4 = Contacts ()
        c4.name = "razi"
        c4.email ="razi@gmail.com"
        contactList.add(c4)

        setEmailAutoCompletation(toContacts, "To")
        setEmailAutoCompletation(ccContacts, "CC")
        setEmailAutoCompletation(bccContacts, "BCC")
        val adapter2 = ArrayAdapter<Contacts>(this, android.R.layout.simple_dropdown_item_1line, contactList)
        toContacts.setAdapter(adapter2)
        ccContacts.setAdapter(adapter2)
        bccContacts.setAdapter(adapter2)

        alertDialog.window!!.attributes!!.windowAnimations = R.style.animation
        alertDialog.show()
    }

    private fun setEmailAutoCompletation(searchView: ContactsCompletionView, text: String){
        val typedValue =  TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        val color: Int = typedValue.data;

        searchView.setPrefix(text + " : ", color);

        searchView.setTokenListener(this)
        searchView.setTokenClickStyle(TokenCompleteTextView.TokenClickStyle.Select)
       // searchView.allowDuplicates(false)
        searchView.setTokenLimit(15)
        searchView.allowCollapse(true)
        searchView.threshold = 1
    }

    override fun onTokenAdded(token: Contacts?) {
        Log.d("mtest","======="+ (token?.email ?: "nai"))
        selectedEmail?.plus(token)
    }

    override fun onTokenRemoved(token: Contacts?) {
        selectedEmail?.minus(token)
    }

    override fun onTokenIgnored(token: Contacts?) {
        val toast = Toast.makeText(this, "Already added this contact" , Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

  /*  private fun addGroupChips(
        toAutoCompleteTextView: AutoCompleteTextView,
        adapter: ArrayAdapter<String>,
        toChipGroup: ChipGroup
    ) {
        toAutoCompleteTextView.setAdapter<ArrayAdapter<String>>(adapter)
        toAutoCompleteTextView.threshold = 1
        toAutoCompleteTextView.setOnItemClickListener { parent, arg1, position, arg3 ->
            toAutoCompleteTextView.text = null
            val selected = parent.getItemAtPosition(position) as String
            addChipToGroup(selected, toChipGroup)
        }
    }

    private fun addChipToGroup(person: String, chipGroup: ChipGroup) {
        val chip = Chip(this)
        chip.text = person
       // chip.chipIcon = ContextCompat.getDrawable(this, R.drawable.ic_close_black_24dp)
        chip. isCloseIconVisible = true
        chip.minLines = 1
        chip.setChipIconTintResource(R.color.colorPrimary)

        // necessary to get single selection working
        chip.isClickable = true
        chip.isCheckable = false
        chipGroup.addView(chip as View)
        chip.setOnCloseIconClickListener { chipGroup.removeView(chip as View) }
    }*/



}
