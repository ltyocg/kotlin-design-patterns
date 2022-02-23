fun main() {
    with(FilterManager()) {
        addFilter(NameFilter())
        addFilter(ContactFilter())
        addFilter(AddressFilter())
        addFilter(DepositFilter())
        addFilter(OrderFilter())
        Client().filterManager = this
    }
}