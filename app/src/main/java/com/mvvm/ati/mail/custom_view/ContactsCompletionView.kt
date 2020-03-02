package com.mvvm.ati.mail.custom_view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mvvm.ati.mail.R
import com.mvvm.ati.mail.model.Contacts
import com.tokenautocomplete.TokenCompleteTextView


class ContactsCompletionView(
    context: Context?,
    attrs: AttributeSet?
) : TokenCompleteTextView<Contacts>(context, attrs) {

    override fun getViewForObject(`object`: Contacts): View {
        val l = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = l.inflate(R.layout.chips_token, parent as ViewGroup, false) as TextView
        view.text = `object`.name
        return view
    }

    override fun defaultObject(completionText: String): Contacts {
        return Contacts(completionText, completionText)
    }

    override fun shouldIgnoreToken(token: Contacts?): Boolean {
        return objects.contains(token)
    }
}