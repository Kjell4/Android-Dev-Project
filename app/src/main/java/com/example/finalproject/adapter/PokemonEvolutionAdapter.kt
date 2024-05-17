package com.example.finalproject.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.common.Common
import com.example.finalproject.model.entity.Evolution

class PokemonEvolutionAdapter(internal var context: Context, var evolutionList:List<Evolution>):
RecyclerView.Adapter<PokemonEvolutionAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return evolutionList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.chip.setText(evolutionList!![position].name)
        holder.chip.setBackgroundColor(Common.getColorByType(Common.findPokemonByNum(evolutionList!![position].num!!)!!.type!![0]))
    }

    init {
        if(evolutionList == null) {
            evolutionList = ArrayList()
        }
    }
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        internal var chip: com.robertlevonyan.views.chip.Chip


        init {
            chip = itemView.findViewById(R.id.chip) as com.robertlevonyan.views.chip.Chip
            chip.setOnClickListener {
                LocalBroadcastManager.getInstance(context).sendBroadcast(Intent(Common.KEY_NUM_EVOLUTION).putExtra("num",evolutionList!![adapterPosition].num))
            }
        }
    }
}