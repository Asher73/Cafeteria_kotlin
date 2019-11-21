package com.cafetexpress.cafete.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cafetexpress.cafete.activities.EnvioUsuarioActivity
import com.cafetexpress.cafete.R
import com.cafetexpress.cafete.model.usuarios

class AdapterUsuario internal constructor(
    context: Context
) : RecyclerView.Adapter<AdapterUsuario.UsrViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var user = emptyList<usuarios>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsrViewHolder {
        val itemView = inflater.inflate(R.layout.item_usuario, parent, false)
        return UsrViewHolder(itemView)
    }

    override fun getItemCount() = user.size

    override fun onBindViewHolder(holder: UsrViewHolder, position: Int) {
        val current = user[position]
        holder.edNoControl.text = current.noc
        holder.edNombre.text = current.nomb
        holder.edCarrera.text = current.car
        holder.edPwd.text = current.pwd
        //holder.edTiempo.text=current.tiempo


        var intent = Intent(inflater.context, EnvioUsuarioActivity::class.java)

        holder.card.setOnClickListener {
            val context=inflater.context as Activity
            context.startActivity(intent)
        }
    }

    inner class UsrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: TextView=itemView.findViewById(R.id.card_producto)
        val edNoControl: TextView = itemView.findViewById(R.id.tvNoControl)
        val edNombre: TextView = itemView.findViewById(R.id.tvNombreUsr)
        val edCarrera: TextView = itemView.findViewById(R.id.tvCarrera)
        val edPwd: TextView = itemView.findViewById(R.id.tvPwd)
        //val edTiempo: TextView =itemView.findViewById(R.id.tvTiempo)
    }

    fun setList(UserList: List<usuarios>) {
        this.user = UserList
        notifyDataSetChanged()
    }
}