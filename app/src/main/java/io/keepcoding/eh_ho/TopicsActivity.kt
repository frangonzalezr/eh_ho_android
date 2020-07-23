package io.keepcoding.eh_ho

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_topics.*

class TopicsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)

        val adapter = TopicsAdapter {
            goToPosts(it)
        }

        adapter.setTopics(TopicsRepo.topics)

        listTopics.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listTopics.adapter = adapter

    }

    private fun goToPosts(topic: Topic) {
        val intent = Intent(this, PostsActivity::class.java)
        intent.putExtra(EXTRA_TOPIC_ID, topic.id)
        startActivity(intent)
    }
}