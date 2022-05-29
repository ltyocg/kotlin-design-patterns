package api.gateway.service

import kotlinx.serialization.Serializable

@Serializable
data class DesktopProduct(val price: String?, val imagePath: String?)

@Serializable
data class MobileProduct(val price: String?)