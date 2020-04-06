package com.example.nextdoormvvm.buyer.ui.home.timeslot

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.nextdoormvvm.R
import com.example.nextdoormvvm.internal.listners.OnTimeSlotSelectListener
import kotlinx.android.synthetic.main.time_slot_row.view.*


class TimeSlotAdapter(val context: Fragment, private val timeSlots:ArrayList<String>, private val slotCategory:Int):
    androidx.recyclerview.widget.RecyclerView.Adapter<TimeSlotViewHolder>() {
    companion object{
        const val MORNING_SLOTS:Int=1
        const val AFTER_NOON_SLOTS:Int=2
        const val EVENING_SLOTS:Int=3

    }
    var rowIndex:Int=-1
    private  var listener: OnTimeSlotSelectListener = this.context as TimeSlotFragment
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TimeSlotViewHolder {
        return TimeSlotViewHolder(LayoutInflater.from(context.context).inflate(R.layout.time_slot_row,p0,false))
    }

    override fun getItemCount(): Int {
        return timeSlots.size
    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        holder.tvTimeSlot.text= timeSlots[position]


        holder.tvTimeSlot.setOnClickListener {
            rowIndex =position
            listener.updateOther(slotCategory,holder.tvTimeSlot.text.toString())
            notifyDataSetChanged()
        }
        if(rowIndex==position){
            //holder.tv_time_slot.setBackgroundColor(Color.BLUE)
            holder.tvTimeSlot.setTextColor(Color.GREEN)
        }
        else{
            //holder.tv_time_slot.setBackgroundColor(Color.parseColor("#ffffff"))
            holder.tvTimeSlot.setTextColor(Color.parseColor("#000000"))
        }



    }


}
class TimeSlotViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val tvTimeSlot: TextView =view.tv_time_slot
}
