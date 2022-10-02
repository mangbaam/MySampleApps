package mangbaam.practice.recyclerviewclickbetter

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import mangbaam.practice.recyclerviewclickbetter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val tables = List(100) { Order(it + 1) }

        val orderAdapter = OrderAdapter { order ->
            if (!order.valid) {
                Toast.makeText(this, "${order.tableNumber} 번 테이블 : 주문된 음식이 없습니다", Toast.LENGTH_SHORT).show()
            } else {
                val orderInfo = StringBuilder()
                    .append("${order.tableNumber} 번 테이블 : ")
                if (order.gimbob) orderInfo.append("김밥 ")
                if (order.ramen) orderInfo.append("라면 ")
                if (order.dduckbokki) orderInfo.append("떡볶이 ")
                if (order.cutlet) orderInfo.append("돈까스 ")
                orderInfo.append("주문되었습니다")

                Toast.makeText(this, "$orderInfo", Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvTable.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = orderAdapter.also { it.items = tables }
        }
    }
}
