package io.github.nikita756.responsifintech.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.nikita756.responsifintech.model.Bayar
import io.github.nikita756.responsifintech.R

class BayarAdapter(private val list: ArrayList<Bayar>) : RecyclerView.Adapter<BayarAdapter.BayarViewHolder>(){
    inner class BayarViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(bayar: Bayar){
        with(itemView){
            findViewById<ImageView>(R.id.imgrekom)
             findViewById<TextView>(R.id.txtrekomendasi)
        }
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BayarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bayar, parent,false)
        return BayarViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BayarViewHolder, position: Int) {
 holder.bind(list[position])
    }
}
