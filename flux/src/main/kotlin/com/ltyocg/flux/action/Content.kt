package com.ltyocg.flux.action

enum class Content(private val title: String) {
    PRODUCTS("Products - This page lists the company's products."),
    COMPANY("Company - This page displays information about the company.");

    override fun toString(): String = title
}