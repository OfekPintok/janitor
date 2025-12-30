package com.ofekpintok.janitor.features.janitor.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bag(val index: Int, val weight: Double) : Parcelable
