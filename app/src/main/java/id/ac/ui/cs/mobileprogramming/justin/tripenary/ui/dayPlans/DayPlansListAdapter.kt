package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.dayPlans

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.DayPlans
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R


class DayPlansListAdapter internal constructor(context: Context?, val itemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<DayPlansListAdapter.DayPlansViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<DayPlans>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayPlansViewHolder {
        return DayPlansViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DayPlansViewHolder, position: Int) {
        val dayPlans: DayPlans = list[position]

        holder.bind(dayPlans, itemClickListener)
    }

    internal fun setDayPlans(dayPlans: List<DayPlans>) {
        this.list = dayPlans
        print("day plans in adapter")
        println(this.list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    inner class DayPlansViewHolder(inflater: LayoutInflater, parent:ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.day_trips_item, parent, false)) {
        private var mTitleView: TextView? = null
        private var mDateView: TextView? = null

        init {
            mTitleView = itemView.findViewById(R.id.day_plan_title)
            mDateView = itemView.findViewById(R.id.day_plan_date)
        }

        fun bind(dayPlans: DayPlans, clickListener: OnItemClickListener) {
            mTitleView?.text = dayPlans.title
            mDateView?.text = dayPlans.date

            itemView.setOnClickListener {
                clickListener.onItemClicked(dayPlans)
            }
        }
    }

}

interface OnItemClickListener {
    fun onItemClicked(dayPlans: DayPlans)
}

