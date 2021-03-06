package diplomatssummit.com.diplomatssummit.old

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import diplomatssummit.com.diplomatssummit.R
import kotlinx.android.synthetic.main.event_list_item.view.*

class EventAdapter(val items: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {



    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.external_events_layout, parent, false))
    }

    // Binds each event in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvEvent.text = items.get(position)
    }
}

class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
    val tvEvent = view.event_listing!!
}