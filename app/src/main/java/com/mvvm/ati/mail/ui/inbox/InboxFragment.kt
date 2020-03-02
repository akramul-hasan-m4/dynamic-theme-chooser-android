package com.mvvm.ati.mail.ui.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.ati.mail.R
import com.mvvm.ati.mail.adapter.MailAdapter
import com.mvvm.ati.mail.databinding.FragmentInboxBinding


class InboxFragment : Fragment(), MailAdapter.MailListener {

    private lateinit var inboxViewModel: InboxViewModel
    private lateinit var binding: FragmentInboxBinding
    private lateinit var checkAll: CheckBox
    private lateinit var trashAll: TextView
    private lateinit var selectLayout: LinearLayout
    private lateinit var adapter: MailAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_inbox,
            container,
            false
        )

        inboxViewModel = ViewModelProviders.of(this).get(InboxViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_inbox, container, false)
        val mailRecView: RecyclerView = root.findViewById(R.id.mail_list)
        val mailProgressbar: ProgressBar = root.findViewById(R.id.mail_progressbar)
        checkAll = root.findViewById(R.id.mail_checkbox_all)
        trashAll = root.findViewById(R.id.trash_all)
        selectLayout = root.findViewById(R.id.select_layout)
        val linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)
        mailRecView.layoutManager = linearLayoutManager

        inboxViewModel.getMailLiveData().observe(this, Observer {
            adapter = activity?.let { it1 -> MailAdapter(it, it1,this)}!!
            mailRecView.adapter = adapter
        })

        checkAll.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            if(isChecked){
                adapter.notifyAllChecked(true)
            }else {
                adapter.notifyAllChecked(false)
            }
        }

        return root
    }

    override fun multipleSelectNotify(isSelect: Boolean) {

        if(isSelect){
            checkAll.isChecked = false
            selectLayout.visibility = View.VISIBLE
        }else {
            selectLayout.visibility = View.GONE
        }
    }

}