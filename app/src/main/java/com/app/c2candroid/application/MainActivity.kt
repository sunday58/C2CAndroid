package com.app.c2candroid.application

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.c2candroid.R
import com.app.c2candroid.application.adapters.ExhibitAdapter
import com.app.c2candroid.databinding.ActivityMainBinding
import com.app.c2candroid.model.Exhibit
import com.app.c2candroid.utils.DataState
import com.app.c2candroid.utils.checkInternet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: Dialog
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ExhibitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        fetchData()

    }


    private fun fetchData(){
        if ( checkInternet(this)){
            dialog.dismiss()
            subscribeObservers()
        }else{
            showErrorDialog("Network Error", "please check your internet and try again"){fetchData()}
        }
    }

    private fun subscribeObservers(){
        viewModel.getStateEvent(MainStateEvent.GetExhibitEvents){}
        viewModel.dataState.observe(this, { dataState ->
            run {
                when (dataState) {
                    is DataState.success<List<Exhibit>> -> {
                        displayProgressBar(false)
                        Log.d("items", "${dataState.data}")
                        initAdapter(dataState.data)
                    }
                    is DataState.Error -> {
                        displayProgressBar(false)
                        displayError(dataState.exception.message) { subscribeObservers() }
                    }

                    is DataState.Loading -> {
                        displayProgressBar(true)
                    }
                    is DataState.otherError -> {
                        displayProgressBar(false)
                        displayError(dataState.error) { subscribeObservers() }
                    }
                }
            }
        })
    }


    private fun displayError(message: String?, callback: () -> Unit){
        if (message != null){
            showErrorDialog("Error", message){callback()}
        }else{
            showErrorDialog("Problem Occured",
                "Something went wrong, please try again or contact support for assistant"){callback()}
        }
    }

    private fun displayProgressBar(isDisplay: Boolean){
        binding.progressBar.visibility = if (isDisplay) View.VISIBLE else View.GONE
    }

    private fun initAdapter(item: List<Exhibit>){
        binding.exhibitRecyclerItems.layoutManager = LinearLayoutManager(binding.root.context)
        adapter = ExhibitAdapter(item, this)
        binding.exhibitRecyclerItems.adapter = adapter
    }

    private fun showErrorDialog(titleMessage: String, descMessage: String, retryMess: () -> Unit) {
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog_error)
        val dismiss = dialog.findViewById(R.id.btDismissCustomDialog) as Button
        val title = dialog.findViewById(R.id.title) as TextView
        val desc = dialog.findViewById(R.id.desc) as TextView
        val retry = dialog.findViewById(R.id.retryCustomDialog) as Button

        title.text = titleMessage
        desc.text = descMessage
        dismiss.setOnClickListener {
            dialog.dismiss()
        }
        retry.setOnClickListener {
            retryMess()
            dialog.dismiss()
        }
        dialog.show()

    }


}