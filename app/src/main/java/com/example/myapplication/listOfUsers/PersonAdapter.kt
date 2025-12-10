package com.example.myapplication.listOfUsers

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.PersonItemBinding

class PersonAdapter(private val personList: List<Person>) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonViewHolder {
        val binding = PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PersonViewHolder,
        position: Int
    ) {
        val person = personList[position]

        holder.binding.personName.text = person.name
        holder.binding.personEmail.text = person.email
        holder.binding.personImage.setImageResource(person.imageUrl)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    inner class PersonViewHolder(val binding: PersonItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val selectedPerson = personList[position]
                    Toast.makeText(itemView.context, "wybrano ${selectedPerson.name}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}