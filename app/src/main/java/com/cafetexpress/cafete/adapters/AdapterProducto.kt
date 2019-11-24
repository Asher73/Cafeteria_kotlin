package com.cafetexpress.cafete.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cafetexpress.cafete.R
import com.cafetexpress.cafete.model.productos

class AdapterProducto internal constructor(
    context: Context
) : RecyclerView.Adapter<AdapterProducto.ProductosViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var prod = emptyList<productos>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        val itemView = inflater.inflate(R.layout.item_producto, parent, false)
        return ProductosViewHolder(itemView)
    }

    override fun getItemCount() = prod.size

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        val current = prod[position]
        holder.edId.text = current.id.toString()
        holder.edNombre.text = current.nombre
        holder.edPrecio.text = "$" + current.precio.toString()
        holder.edCantidad.text = current.cantidad.toString()
        holder.edTiempo.text = current.tiempo

        /*var intent = Intent(inflater.context, PruebaActivity::class.java)

        holder.card.setOnClickListener{
            val context=inflater.context as Activity
            context.startActivity(intent)
        }*/
    }

    inner class ProductosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.card_producto)
        val edId: TextView = itemView.findViewById(R.id.tvID)
        val edNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val edPrecio: TextView = itemView.findViewById(R.id.tvPrecio)
        val edCantidad: TextView = itemView.findViewById(R.id.tvCantidad)
        val edTiempo: TextView = itemView.findViewById(R.id.tvTiempo)
    }

    fun setList(ProdList: List<productos>) {
        this.prod = ProdList
        notifyDataSetChanged()
    }
}