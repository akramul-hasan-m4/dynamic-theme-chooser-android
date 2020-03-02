package com.mvvm.ati.mail.ui.sent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mvvm.ati.mail.R

class SentFragment : Fragment() {

    private lateinit var sentViewModel: SentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sentViewModel =
            ViewModelProviders.of(this).get(SentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sent, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        sentViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}