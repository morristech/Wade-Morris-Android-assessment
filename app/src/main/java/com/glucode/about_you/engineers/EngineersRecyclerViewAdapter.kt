package com.glucode.about_you.engineers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.glucode.about_you.databinding.ItemEngineerBinding
import com.glucode.about_you.engineers.models.Engineer
import coil.load
import com.glucode.about_you.R

class EngineersRecyclerViewAdapter(
    private var engineers: List<Engineer>,
    private val onClick: (Engineer) -> Unit
) : RecyclerView.Adapter<EngineersRecyclerViewAdapter.EngineerViewHolder>() {

    override fun getItemCount() = engineers.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineerViewHolder {
        return EngineerViewHolder(
            ItemEngineerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EngineerViewHolder, position: Int) {
        holder.bind(engineers[position], onClick)
    }

    fun updateEngineers(newEngineers: List<Engineer>) {
        val diffCallback = EngineerDiffCallback(engineers, newEngineers)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        engineers = newEngineers
        diffResult.dispatchUpdatesTo(this)
    }

    inner class EngineerViewHolder(private val binding: ItemEngineerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(engineer: Engineer, onClick: (Engineer) -> Unit) {
            binding.name.text = engineer.name
            binding.role.text = engineer.role
            binding.root.setOnClickListener { onClick(engineer) }

            binding.profileImage.load(engineer.defaultImageName) {
                crossfade(true)
                placeholder(R.drawable.ic_person)
            }
        }
    }
}

class EngineerDiffCallback(private val oldList: List<Engineer>, private val newList: List<Engineer>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}