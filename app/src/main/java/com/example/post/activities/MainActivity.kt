package com.example.post.activities

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.post.IPostAdapter
import com.example.post.PostAdapter
import com.example.post.R
import com.example.post.dao.PostDao
import com.example.post.models.Posts
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity(), IPostAdapter {
    private lateinit var postDao: PostDao
    private lateinit var adapter: PostAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fab: View =findViewById(R.id.fab)
        recyclerView=findViewById(R.id.recyclerView)
        fab.setOnClickListener {
            val intent= Intent(this,CreatePost::class.java)
            startActivity(intent)
        }
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        postDao = PostDao()
        val postCollection=postDao.postCollection
        val query=postCollection.orderBy("createdAt",Query.Direction.DESCENDING )
        val recyclerViewOption=FirestoreRecyclerOptions.Builder<Posts>().setQuery(query,Posts::class.java).build()
            adapter=PostAdapter(recyclerViewOption,this)

        recyclerView.adapter=adapter
            recyclerView.layoutManager=LinearLayoutManager(this)
    }
    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onLikeClicked(postId: String) {
            postDao.updateLikes(postId)
    }

}