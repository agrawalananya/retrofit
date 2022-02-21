package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var toDoRecyclerViewAdapter: ToDoAdapter
    private val tag: String = "MainActivityTag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerview()
        addDataSet()
    }

    private fun addDataSet() {
        progress_bar.isVisible = true
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val response = RetrofitInstance.api.getTodo()
                Log.d(tag,response.body().toString())
                if (response.isSuccessful && response.body() != null) {
                    doSomething(response)

                } else {
                    Toast.makeText(applicationContext, "Some error occured", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } catch (e: IOException) {
            Log.e(tag, "IOExceptions occured ")
        } catch (e: HttpException) {
            Log.e(tag, "HttpException occured ")
        }

    }

    private suspend fun doSomething (response:Response<List<ToDo>>) {
        withContext(Dispatchers.Main) {
            progress_bar.isVisible = false
            toDoRecyclerViewAdapter.submitList(response.body()!!)
            toDoRecyclerViewAdapter.notifyDataSetChanged()
        }

    }

    private fun initRecyclerview() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            toDoRecyclerViewAdapter = ToDoAdapter()
            adapter = toDoRecyclerViewAdapter
        }
    }
}