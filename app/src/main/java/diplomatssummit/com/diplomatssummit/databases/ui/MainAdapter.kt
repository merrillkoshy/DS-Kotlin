package diplomatssummit.com.diplomatssummit.databases.ui

import android.content.ClipData
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.databases.core.Item
import diplomatssummit.com.diplomatssummit.databases.core.ItemRepository
import kotlinx.android.synthetic.main.main_item.*
import kotlinx.android.synthetic.main.main_item.view.*
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val mItems: MutableList<Item>
    private val mOnClickListener: View.OnClickListener

    init {
        mItems = ItemRepository.getAll()
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Item
            item.updatedAt = Calendar.getInstance()
            item.save()
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.main_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems[position]
        val date = item.updatedAt.timeInMillis

        val color = "#" + date.toString().substring(7)
        holder.card.setBackgroundColor(Color.parseColor(color))
        holder.title.text = color
        holder.date.text = DateFormat.format("hh:mm:ss", Date(date))

        with (holder.container) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun add() {
        val item = Item()
        mItems.add(0, item)
        item.save()
        notifyItemInserted(0)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card = view.card
        val container = view.container
        val title = view.title
        val date = view.date
    }
}