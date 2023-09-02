package com.example.helloworld.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R
import com.example.helloworld.entities.User

class UserAdapter (
    var userList : MutableList<User>
    ) : RecyclerView.Adapter<UserAdapter.UserHolder>(){ // yo tengo que crear el UserHolder (es una inner class)

        class UserHolder (v:View) : RecyclerView.ViewHolder(v){ // el holder es la parte del adapter que se comunica ocn la vista
            private var view: View // esto que recibe es la vista del item
            init {
                this.view = v
            }
            fun setName(name:String){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
                var txtName: TextView = view.findViewById(R.id.txtName)
                txtName.text = name
            }
            fun setEmail(email:String){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
                var txtEmail: TextView = view.findViewById(R.id.txtEmail)
                txtEmail.text = email
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder { // tengo que implementar estos metodos
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false) // Esto es un copy paste, siempre igual
        return (UserHolder(view))
    }

    override fun getItemCount(): Int { // tengo que implementar estos metodos
        // tengo que devolver la cantidad de elementos de la lista
        return userList.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) { // tengo que implementar estos metodos
        // este metodo si es un poco mas complejo, une la informacion con el viewholder
        holder.setName(userList[position].name)
        holder.setEmail(userList[position].email)
    }


}

