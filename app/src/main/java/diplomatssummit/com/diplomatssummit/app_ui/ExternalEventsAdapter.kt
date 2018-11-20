package diplomatssummit.com.diplomatssummit.app_ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.util.DLog
import kotlinx.android.synthetic.main.event_list_item.view.*

class ExternalEventsAdapter(val items: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false))
    }

    // Binds each event in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvEvent.text = items.get(position)
    }
}

class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvEvent = view.event_listing!!
}
