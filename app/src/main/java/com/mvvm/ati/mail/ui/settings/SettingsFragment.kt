package com.mvvm.ati.mail.ui.settings


import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mvvm.ati.mail.MainActivity
import com.mvvm.ati.mail.R


/**
 * A simple [Fragment] subclass.
 */
const val KEY_CURRENT_THEME = "current_theme"

class SettingsFragment : Fragment(), View.OnClickListener {

    private lateinit var sharedPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View  = inflater.inflate(R.layout.fragment_settings, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Settings"
        val tel : Button = view.findViewById(R.id.teal)
        val green : Button = view.findViewById(R.id.green)
        val ash : Button = view.findViewById(R.id.ash)
        val blue : Button = view.findViewById(R.id.blue)
        val navy : Button = view.findViewById(R.id.navy)
        val orange : Button = view.findViewById(R.id.orange)
        val purple : Button = view.findViewById(R.id.purple)
        val red : Button = view.findViewById(R.id.red)
        val dark : Button = view.findViewById(R.id.dark)
        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        val currentTheme = sharedPref.getInt(KEY_CURRENT_THEME, R.style.AppTheme)

        if(currentTheme ==  R.style.AppTheme){
            setCheck(tel)
        }else if(currentTheme ==  R.style.NavyAppTheme){
            setCheck(navy)
        }

        tel.setOnClickListener(this)
        green.setOnClickListener(this)
        ash.setOnClickListener(this)
        blue.setOnClickListener(this)
        navy.setOnClickListener(this)
        orange.setOnClickListener(this)
        purple.setOnClickListener(this)
        red.setOnClickListener(this)
        dark.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        when (v?.id ?: 0) {
            R.id.teal -> {
                sharePre(R.style.TealAppTheme)
            }
            R.id.green -> {
                sharePre(R.style.GreenAppTheme)
            }
            R.id.ash -> {
                sharePre(R.style.AshAppTheme)
            }
            R.id.blue -> {
                sharePre(R.style.BlueAppTheme)
            }
            R.id.navy -> {
                sharePre(R.style.NavyAppTheme)
            }
            R.id.orange -> {
                sharePre(R.style.OrangeAppTheme)
            }
            R.id.purple -> {
                sharePre(R.style.PurpleAppTheme)
            }
            R.id.red -> {
                sharePre(R.style.RedAppTheme)
            }
            R.id.dark -> {
                sharePre(R.style.DarkAppTheme)
            }
        }
    }

    private fun sharePre(theme : Int) {
        sharedPref.edit().putInt(KEY_CURRENT_THEME, theme).apply()

        activity?.let{
            val intent = Intent (it, MainActivity::class.java)
            it.startActivity(intent)
        }
    }

    private fun setCheck(button : Button){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.setCompoundDrawables(null,null,null, resources.getDrawable(R.drawable.ic_check_black_24dp, null))
        }
    }

}
