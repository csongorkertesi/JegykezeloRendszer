import kotlin.system.exitProcess

class Menu(val ticketService: TicketService) {
     fun menu() {
        println("\n\n--- Ticket manager ---")
        println("1) Add ticket")
        println("2) List tickets")
        println("3) Search tickets")
        println("4) Delete ticket")
        println("5) Exit program\n")

        print("Choice: ")
        val choice = readln()
        if (choice.toIntOrNull() == null || (choice.toInt() < 1 || choice.toInt() > 5)) {
            println("\n")
            menu()
        }

        when (choice.toInt()) {
            1 -> ticketService.addTicket()
            2 -> ticketService.displayTickets()
            3 -> {
                print("By name/[event]? ")
                val choice2 = readln().trim().lowercase()
                print("$choice2 query: ")
                val query = readln().trim()
                if (choice2 == "name") {
                    ticketService.getTicketsByBuyer(query).forEach { it.printData() }
                } else {
                    ticketService.getTicketsByEventName(query).forEach { it.printData() }
                }
            }
            4 -> {
                print("Enter id: ")
                val id = readln().toIntOrNull()
                if (id == null) { println("Invalid id."); return }
                ticketService.deleteTicket(id)
            }
            5 -> {
                exitProcess(0)
            }
        }

        menu()
    }
}