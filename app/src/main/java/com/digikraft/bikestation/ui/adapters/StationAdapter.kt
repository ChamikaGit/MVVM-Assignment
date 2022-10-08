package com.digikraft.bikestation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.digikraft.bikestation.databinding.RvItemLayoutBinding
import com.digikraft.bikestation.model.bike.Feature

class StationAdapter(private val itemClickLister: ItemClickLister) : RecyclerView.Adapter<StationAdapter.StationListViewHolder>() {

    interface ItemClickLister{
        fun itemClick(feature: Feature)
    }

    private var featuresList = ArrayList<Feature>()

    fun setFeatureList(itemList: ArrayList<Feature>) {
        featuresList = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationListViewHolder {
        return StationListViewHolder(RvItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: StationListViewHolder, position: Int) {
        val item = getFeature(featuresList,position)
        holder.bind(item)
    }

    private fun getFeature(featuresList: ArrayList<Feature>, position: Int): Feature {
        return featuresList[position]
    }

    override fun getItemCount(): Int = featuresList.size

    inner class StationListViewHolder(private val layoutBinding: RvItemLayoutBinding) :
        RecyclerView.ViewHolder(layoutBinding.root) {

            fun bind(feature: Feature){
                layoutBinding.apply {
                    tvName.text  = feature.properties.label
                    tvBikeCount.text = feature.properties.bikes
                    tvPlaceCount.text = feature.properties.bike_racks
                    tvDistance.text = "600m"

                    root.setOnClickListener {
                        itemClickLister.itemClick(feature)
                    }
                }

            }

    }
}