package io.keepcoding.eh_ho.topics

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.Topic
import io.keepcoding.eh_ho.TopicsRepo
import io.keepcoding.eh_ho.inflate
import kotlinx.android.synthetic.main.fragment_topics.*

class TopicsFragment : Fragment() {

    var topicsInteractionListener: TopicsInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is TopicsInteractionListener)
            topicsInteractionListener = context
        else
            throw IllegalArgumentException("Context doesn't implement ${TopicsInteractionListener::class.java.canonicalName}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.fragment_topics)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TopicsAdapter {
            this.topicsInteractionListener?.onShowPosts(it)
        }

        buttonCreate.setOnClickListener {
            this.topicsInteractionListener?.onCreateTopic()
        }

        adapter.setTopics(TopicsRepo.topics)

        listTopics.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listTopics.adapter = adapter
    }

    override fun onDetach() {
        super.onDetach()
        topicsInteractionListener = null
    }

    interface TopicsInteractionListener {
        fun onCreateTopic()
        fun onShowPosts(topic: Topic)
    }

}