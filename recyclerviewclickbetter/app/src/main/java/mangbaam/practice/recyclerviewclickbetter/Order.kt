package mangbaam.practice.recyclerviewclickbetter

data class Order(
    val tableNumber: Int,
    val gimbob: Boolean = false,
    val ramen: Boolean = false,
    val dduckbokki: Boolean = false,
    val cutlet: Boolean = false
) {
    val valid get() = gimbob || ramen || dduckbokki || cutlet
}
