package com.mvvm.ati.mail.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.ati.mail.R
import com.mvvm.ati.mail.adapter.MailAdapter.MailView
import com.mvvm.ati.mail.model.Mail

class MailAdapter(private val mailList: List<Mail>, private val activity: Activity, private val listener: MailListener) : RecyclerView.Adapter<MailView>() {

    private var isShowCheckbox = false
    private var isAllChecked = false

    interface MailListener{
        fun multipleSelectNotify(isSelect: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mail, parent, false)
        return MailView(view)
    }

    override fun onBindViewHolder(holder: MailView, position: Int) {
        val mail = mailList[position]
        holder.message.text = mail.message
        holder.addressFrom.text = mail.sentFrom
        holder.date.text = mail.dateTime

        if(isShowCheckbox){
            holder.checkbox.visibility = View.VISIBLE
        }else {
            holder.checkbox.visibility = View.GONE
        }

        holder.checkbox.isChecked = isAllChecked

        holder.itemView.setOnLongClickListener {
            isShowCheckbox = !isShowCheckbox
            listener.multipleSelectNotify(isShowCheckbox)
            if(!isShowCheckbox){isAllChecked = false}
            notifyDataSetChanged()
            true
        }
    }

    override fun getItemCount(): Int {
        return mailList.size
    }

    fun notifyAllChecked(isChecked: Boolean) {
        isAllChecked = isChecked
        notifyDataSetChanged()
    }

    inner class MailView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var message: TextView
        var addressFrom: TextView
        var date: TextView
        var checkbox: CheckBox

        init {
            message = itemView.findViewById(R.id.message)
            addressFrom = itemView.findViewById(R.id.address_from)
            date = itemView.findViewById(R.id.date)
            checkbox = itemView.findViewById(R.id.mail_checkbox)
        }
    }

}