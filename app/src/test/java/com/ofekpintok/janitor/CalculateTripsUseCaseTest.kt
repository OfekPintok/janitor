package com.ofekpintok.janitor

import com.ofekpintok.janitor.features.janitor.domain.CalculateTripsUseCase
import com.ofekpintok.janitor.features.janitor.domain.model.Bag
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CalculateTripsUseCaseTest {
    val sut = CalculateTripsUseCase()

    @Test
    fun calculateTripZeroBags() {
        assertEquals(
            0,
            sut(bags = emptyList()).size
        )
    }

    @Test
    fun calculateTripSingleBag() {
        assertEquals(
            1,
            sut(bags = listOf(Bag(0, 2.0))).size
        )
    }

    @Test
    fun calculateTripAllBagsAreOverweight() {
        assertEquals(
            3,
            sut(
                bags = listOf(
                    Bag(0, 2.0),
                    Bag(1, 3.0),
                    Bag(2, 2.5)
                )
            ).size
        )
    }

    @Test
    fun calculateTripOddList() {
        assertEquals(
            2,
            sut(
                bags = listOf(
                    Bag(0, 1.99),
                    Bag(1, 1.01),
                    Bag(2, 2.5)
                )
            ).size
        )
    }


    @Test
    fun calculateTripEvenList() {
        assertEquals(
            2,
            sut(
                bags = listOf(
                    Bag(1, 1.01),
                    Bag(2, 2.5)
                )
            ).size
        )
    }

    @Test
    fun calculateTripExactLimit() {
        assertEquals(
            1,
            sut(
                bags = listOf(
                    Bag(1, 1.5),
                    Bag(2, 1.5)
                )
            ).size
        )
    }
}