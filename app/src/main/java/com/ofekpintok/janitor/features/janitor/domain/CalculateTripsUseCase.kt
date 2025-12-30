package com.ofekpintok.janitor.features.janitor.domain

import com.ofekpintok.janitor.features.janitor.domain.model.Bag
import com.ofekpintok.janitor.features.janitor.domain.model.Trip

class CalculateTripsUseCase {

    operator fun invoke(bags: List<Bag>): List<Trip> {
        val sortedBagsList = bags.sortedBy { it.weight }

        var right = bags.size - 1
        var left = 0

        return mutableListOf<Trip>().apply {
            while (right >= left) {
                val rightBag = sortedBagsList[right]
                val leftBag = sortedBagsList[left]

                if (right == left) {
                    add(
                        Trip(
                            id = size,
                            bags = listOf(rightBag)
                        )
                    )
                    break
                }

                if (rightBag.weight + leftBag.weight > 3) {
                    add(
                        Trip(
                            id = size,
                            bags = listOf(rightBag)
                        )
                    )
                    right--
                } else {
                    add(
                        Trip(
                            id = size,
                            bags = listOf(rightBag, leftBag)
                        )
                    )
                    right--
                    left++
                }
            }
        }
    }
}