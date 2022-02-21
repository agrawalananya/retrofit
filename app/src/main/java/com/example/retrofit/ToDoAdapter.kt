package com.example.retrofit

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ToDo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("kuchdekhle", "onccreateview holder caled")
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("kuchdekhle", "onBindViewHolder caled")

        when (holder) {
            is TodoViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        Log.d("kuchdekhle","getItemCount caled"+items.size)
        return items.size
    }
    fun submitList(toDoList: List<ToDo>)
    {
        Log.d("kuchdekhle","submitLIst caled")
        items=toDoList
    }

    class TodoViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val toDoName = itemView.todo_name
        val cb = itemView.cbDone

        fun bind(toDo: ToDo) {
            Log.d("kuchdekhle","bind caled")

            toDoName.setText(toDo.title)
            cb.isChecked=toDo.completed
        }
    }
}