class TicketService {
    private val ticketList = mutableListOf<Ticket>()
    private var nextId = 0

    fun addTicket() {
        println("\n--- Purchasing ticket ---")

        print("Buyer name: ")
        val buyerName: String = readln()

        print("Event name: ")
        val eventName: String = readln()

        var price: Double?
        while (true) {
            print("Price: ")
            price = readln().toDoubleOrNull()
            if (price != null) {
                if (price < 0) {
                    println("Price is negative.")
                    continue
                }
                break
            }
        }

        ticketList.addLast(Ticket(nextId++, buyerName, eventName, price!!))
        println("Ticket created:")
        ticketList.last().printData()
    }

    fun displayTickets() {
        println("\n--- Tickets ---")
        if (ticketList.isNotEmpty()) {
            for (ticket in ticketList) {
                ticket.printData()
            }
            println()
            return
        }
        println("\nNo tickets")
        println()
    }

    fun getTicketsByBuyer(buyerName: String): List<Ticket> {
        val found = mutableListOf<Ticket>()
        ticketList.forEach {
            if (it.buyerName == buyerName) { found.add(it) }
        }
        return found
    }

    fun getTicketsByEventName(eventName: String): List<Ticket> {
        val found = mutableListOf<Ticket>()
        ticketList.forEach {
            if (it.buyerName == eventName) { found.add(it) }
        }
        return found
    }

    fun deleteTicket(id: Int) {
        if (id < 0 || id > ticketList.size - 1) {
            println("No ticket found for $id")
            return
        }
        ticketList.removeAt(id)
        println("Deleted ticket #$id")
    }

    fun ticketPriceSum(): Double {
        var sum = 0.0;
        ticketList.forEach { sum += it.price }
        return sum
    }

    fun averageTicketPrice(): Double {
        if (ticketList.size < 1) { return -1.0 }
        return ticketPriceSum()/ticketList.size
    }

    fun mostExpensiveTicket(): Ticket {
        val copy = ticketList
        copy.sortBy { it.price }
        return copy[0]
    }
}