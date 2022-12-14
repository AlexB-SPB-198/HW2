package com.example.hw2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.hw2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list = arrayListOf<String>()
    private lateinit var adapter: TextAdapter
    private var replaceWord = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        invis()
        initAdapter()
        saveText()
    }

    private fun saveText() {
        binding.btnSend.setOnClickListener {
            searchHashtag()
            binding.etMain.text.clear()
        }
    }

    private fun searchHashtag() {
        val words = binding.btnSend.text.split(" ")
        for (word in words) {
            if (word.startsWith("#")) {
                val newWord = word.replace(Regex("[-+,;:{}]"), "")
               list.add(newWord)
            }
        }
    }

    private fun initAdapter() {
        adapter = TextAdapter(list, this::clickListener)
        binding.recyclerMain.adapter = adapter

    }

    @SuppressLint("SetTextI18n")
    private fun clickListener(hashTags: String) {
        binding.etMain.setText(binding.etMain.text.toString().replace(replaceWord,""))
        binding.etMain.setText("${binding.etMain.text}#${hashTags} ")
        binding.etMain.setSelection(binding.etMain.length())
    }


    private fun invis() {
        binding.etMain.setOnClickListener {
            if (list.isNotEmpty()) {
                binding.recyclerMain.isVisible = true
            }
        }
        binding.etMain.addTextChangedListener {
            object : TextWatcher {
                override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val words = text?.split(" ")

                    if (words != null) {
                        for (word in words) {
                            replaceWord = word
                            binding.etMain.isVisible = word.startsWith("#")
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    TODO("Not yet implemented")
                }
            }
        }

    }


}