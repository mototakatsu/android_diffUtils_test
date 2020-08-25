package com.example.diffutils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutils.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: ListAdapter
    var nextId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initList()
    }

    private fun initList() {
        adapter = ListAdapter(layoutInflater)
        // 編集画面 データ表示順を逆にし、最後に追加されたものを先頭に表示させる
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        binding.listItem.layoutManager = layoutManager
        binding.listItem.adapter = adapter

        binding.btnAddMulti.setOnClickListener { v -> actionAddMulti()}
        binding.btnAddSingle.setOnClickListener { v -> actionAddSingle()}
    }

    private fun actionAddMulti() {
        val newItems: MutableList<SampleItem> = adapter.items.toMutableList()
        newItems.addAll(listOf(SampleItem(nextId++), SampleItem(nextId++), SampleItem(nextId++)))
        adapter.update(newItems)
    }

    private fun actionAddSingle() {
        val newItems: MutableList<SampleItem> = adapter.items.toMutableList()
        newItems.add(SampleItem(nextId++))
        adapter.update(newItems)
    }
}