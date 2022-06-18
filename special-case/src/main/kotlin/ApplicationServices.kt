object ApplicationServices {
    fun loggedInUserPurchase(userName: String?, itemName: String?): ReceiptViewModel =
        if (isDownForMaintenance) DownForMaintenance() else DomainServices.purchase(userName, itemName)

    private val isDownForMaintenance: Boolean
        get() = MaintenanceLock.lock
}