package com.example.realtimetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realtimetest.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private var _adapter: CurrencyAdapter? = null
    private val adapter: CurrencyAdapter get() = _adapter!!


    private lateinit var binding: ActivityMainBinding
    private lateinit var realtimeDb: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        _adapter = CurrencyAdapter()

        binding.rvCurrency.adapter = adapter
        binding.rvCurrency.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )

        realtimeDb = FirebaseDatabase.getInstance()



        realtimeDb.getReference("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Toast.makeText(this@MainActivity, "Maǵliwmat keldi", Toast.LENGTH_SHORT).show()
                val tempList = mutableListOf<Currency>()
                snapshot.children.forEach { data ->
                    val tempData = mutableListOf<String>()



                    data.children.forEach {
                        tempData.add(it.value.toString())
                    }
                    val currency = Currency(tempData[1], tempData.first())
                    tempList.add(currency)
                }
                adapter.submitList(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }


}