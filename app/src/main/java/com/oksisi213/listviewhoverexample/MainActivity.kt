package com.oksisi213.listviewhoverexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	companion object {
		val TAG = MainActivity::class.java.simpleName
	}


	private val adapter by lazy {
		MyAdapter()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		listView.adapter = adapter
		adapter.notifyDataSetChanged()
	}


	private inner class MyAdapter : BaseAdapter() {
		var items = ArrayList<String>().apply {
			for (i in 0..100) add("Test $i")
		}

		override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

			val view = if (convertView == null) {
				TextView(parent?.context).apply {
					setBackgroundResource(R.drawable.list_selector)
				}
			} else {
				convertView
			}

			(view as TextView).text = items[position]

			view.setOnHoverListener(object : View.OnHoverListener {
				override fun onHover(v: View?, event: MotionEvent?): Boolean {
					when (event?.actionMasked) {
						MotionEvent.ACTION_HOVER_ENTER -> {
							Log.e(TAG, "onHover: ACTION_HOVER_ENTER")
							v?.isHovered = true
							return true
						}
						MotionEvent.ACTION_HOVER_EXIT -> {
							Log.e(TAG, "onHover: ACTION_HOVER_EXIT")
							v?.isHovered = false
							return true
						}
					}
					return false
				}
			})
			return view

		}

		override fun getItem(position: Int): String = items[position]

		override fun getItemId(position: Int): Long = position.toLong()

		override fun getCount(): Int = items.size

	}
}
