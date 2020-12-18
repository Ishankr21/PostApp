package com.example.post.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.post.R
import com.example.post.dao.PostDao

class CreatePost : AppCompatActivity() {
    private lateinit var postDao: PostDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        var edpost:EditText=findViewById(R.id.edPost)
        postDao= PostDao()
        var btnPost:Button=findViewById(R.id.btnPost)
        btnPost.setOnClickListener {
            val input=edpost.text.toString().trim()
            if(input.isNotEmpty())
            {
                postDao.addPost(input)
                finish()

            }
        }

    }
}