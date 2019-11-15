package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.dayEvents

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.DayEvents


class DayEventsListAdapter internal constructor(context: Context?, val itemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<DayEventsListAdapter.DayEventsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<DayEvents>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayEventsViewHolder {
        return DayEventsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DayEventsViewHolder, position: Int) {
        val dayEvents: DayEvents = list[position]

        holder.bind(dayEvents, itemClickListener)
    }

    internal fun setDayEvents(dayEvents: List<DayEvents>) {
        this.list = dayEvents
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    inner class DayEventsViewHolder(inflater: LayoutInflater, parent:ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.event_plans_item, parent, false)) {
        private var mTitleView: TextView? = null
        private var mTimeView: TextView? = null
        private var mLocationView: TextView? = null
        private var mAttachmentImageView: ImageView? = null
        private var mAttachmentTextView: TextView? = null
        private var mDescriptionView: TextView? = null

        init {
            mTitleView = itemView.findViewById(R.id.event_plan_title)
            mTimeView = itemView.findViewById(R.id.event_plan_time)
            mLocationView = itemView.findViewById(R.id.event_location)
            mAttachmentImageView = itemView.findViewById(R.id.event_attachment_image_view)
            mAttachmentTextView = itemView.findViewById(R.id.attachment_image_name)
            mDescriptionView = itemView.findViewById(R.id.event_description)
        }

        fun bind(dayEvents: DayEvents, clickListener: OnItemClickListener) {
            mTitleView?.text = dayEvents.title
            mTimeView?.text = dayEvents.time
            mLocationView?.text = dayEvents.location
            mAttachmentTextView?.text = dayEvents.attachments!!.split("/").last()
            mDescriptionView?.text = dayEvents.description

            val attachmentUri = Uri.parse(dayEvents.attachments)

            mAttachmentImageView?.setImageURI(attachmentUri)


            itemView.setOnClickListener {
                clickListener.onItemClicked(dayEvents)
            }
        }
    }

}

interface OnItemClickListener {
    fun onItemClicked(dayPlans: DayEvents)
}

