package com.mvvm.ati.mail.ui.inbox

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.ati.mail.model.Mail

class InboxViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Inbox Fragment"
    }
    val text: LiveData<String> = _text

    public fun getLiveDate(): LiveData<List<Mail>>{
        return getMailLiveData()
    }

    internal val allMails: LiveData<List<Mail>> = getMailLiveData()

    public fun getMailLiveData(): MutableLiveData<List<Mail>> {
        val mutableLiveData: MutableLiveData<List<Mail>> = MutableLiveData()
       // val mailList: MutableList<Mail> = ArrayList()
        val mailList = arrayListOf<Mail>()

        val mail = Mail()
        mail.mailId = "1"
        mail.sentFrom = "Masum@atilimited.net"
        mail.dateTime = "2019-02-02 08:30 AM"
        mail.message = "This is test message. This is test message. This is test message. This is test message. This is test message. This is test message. This is test message."
        mailList.add(mail)

        val mail1 = Mail()
        mail1.mailId = "2"
        mail1.sentFrom = "Masum2@atilimited.net"
        mail1.dateTime = "2019-02-02 08:30 AM"
        mail1.message = "This is test message"
       // mailList.toMutableList().add(mail1)
        mailList.add(mail1)

        val mail2 = Mail()
        mail2.mailId = "3"
        mail2.sentFrom = "Masum2@atilimited.net"
        mail2.dateTime = "2019-02-02 08:30 AM"
        mail2.message = "This is test message"
        mailList.add(mail2)

        val mail3 = Mail()
        mail3.mailId = "4"
        mail3.sentFrom = "Masum@atilimited.net"
        mail3.dateTime = "2019-02-02 08:30 AM"
        mail3.message = "This is test message"
        mailList.add(mail3)

        mutableLiveData.value = mailList
        return mutableLiveData
    }
}