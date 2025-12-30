package com.ofekpintok.janitor.features.janitor.domain.model


data class Trip(val id: Int, val bags: List<Bag>) {
    fun printBags(): String {
        val firstBag = bags.getOrNull(0)
        val secondBag = bags.getOrNull(1)

        return StringBuilder("")
            .apply {
                if (firstBag != null) {
                    append(firstBag.index)
                }
                if (secondBag != null) {
                    append(" & ${secondBag.index}")
                }
            }.toString()
    }
}