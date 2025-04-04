class Ticket(val id: Int, val buyerName: String, val eventName: String, val price: Double) {
    fun printData() {
        println("Ticket #$id of $buyerName ($eventName) for $price")
    }
}