package dam.exer_1

sealed class Event {
    abstract val username: String

    data class Login(override val username: String, val timestamp: Long) : Event()
    data class Purchase(override val username: String, val amount: Double, val timestamp: Long) : Event()
    data class Logout(override val username: String, val timestamp: Long) : Event()
}

fun List<Event>.filterByUser(username: String): List<Event> {
    return this.filter { it.username == username }
}


fun List<Event>.totalSpent(username: String): Double {

    return this.filterByUser(username)
        .filterIsInstance<Event.Purchase>()
        .sumOf { it.amount }
}


fun processEvents(events: List<Event>, handler: (Event) -> Unit) {
    events.forEach { handler(it) }
}

fun main() {

    val events = listOf(
        Event.Login("alice", 1_000),
        Event.Purchase("alice", 49.99, 1_100),
        Event.Purchase("bob", 19.99, 1_200),
        Event.Login("bob", 1_050),
        Event.Purchase("alice", 15.00, 1_300),
        Event.Logout("alice", 1_400),
        Event.Logout("bob", 1_500)
    )

    processEvents(events) { event ->
        when (event) {
            is Event.Login -> println("[LOGIN] ${event.username} logged in at t=${event.timestamp}")
            is Event.Purchase -> println("[PURCHASE] ${event.username} spent \$${event.amount} at t=${event.timestamp}")
            is Event.Logout -> println("[LOGOUT] ${event.username} logged out at t=${event.timestamp}")
        }
    }


    println("Total spent by alice: \$${events.totalSpent("alice")}")
    println("Total spent by bob: \$${events.totalSpent("bob")}")

    println("Events for alice:")
    events.filterByUser("alice").forEach { println(it) }
}