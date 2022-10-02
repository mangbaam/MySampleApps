package mangbaam.practice.recyclerviewclickbetter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mangbaam.practice.recyclerviewclickbetter.databinding.ItemOrderBinding

class OrderAdapter(val orderMenu: (Order) -> Unit) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    var items: List<Order> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class OrderViewHolder(
        private val view: ItemOrderBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(order: Order) {
            view.tvTableNumber.text = "${order.tableNumber} 번 테이블"
            view.cbGimbob.isChecked = order.gimbob
            view.cbRamen.isChecked = order.ramen
            view.cbDduckbokki.isChecked = order.dduckbokki
            view.cbCutlet.isChecked = order.cutlet

            view.btnOrder.setOnClickListener {
                val newOrder = Order(
                    order.tableNumber,
                    view.cbGimbob.isChecked,
                    view.cbRamen.isChecked,
                    view.cbDduckbokki.isChecked,
                    view.cbCutlet.isChecked
                )
                orderMenu(newOrder)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size
}
