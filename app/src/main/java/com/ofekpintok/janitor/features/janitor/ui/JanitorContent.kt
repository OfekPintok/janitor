package com.ofekpintok.janitor.features.janitor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ofekpintok.janitor.features.janitor.domain.model.Bag
import com.ofekpintok.janitor.features.janitor.domain.model.Trip
import com.ofekpintok.janitor.ui.theme.JanitorTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun JanitorContent(
    bags: ImmutableList<Bag>,
    trips: ImmutableList<Trip>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        JanitorWeightsList(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            bags = bags
        )
        if (trips.isNotEmpty()) {
            HorizontalDivider(
                modifier = Modifier.padding(top = 16.dp),
                color = Color.LightGray
            )
            Spacer(Modifier.height(8.dp))
            TripSummary(modifier = Modifier.padding(horizontal = 8.dp), trips = trips)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun JanitorContentPreview() {
    JanitorTheme {
        val firstBagsPair = persistentListOf(
            Bag(0, 1.1),
            Bag(1, 1.2)
        )
        val secondSingleBag = persistentListOf(
            Bag(2, 2.5),
        )
        JanitorContent(
            bags = firstBagsPair.addAll(secondSingleBag),
            trips = persistentListOf(
                Trip(0, firstBagsPair),
                Trip(1, secondSingleBag)
            )
        )
    }
}

@Composable
fun JanitorWeightsList(bags: ImmutableList<Bag>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Text(
                        modifier = Modifier.weight(0.2f),
                        text = "# Index",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.width(20.dp))
                    Text(
                        modifier = Modifier.weight(0.8f),
                        text = "Weight (1.01 - 3.00) KG",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
                HorizontalDivider()
                Spacer(Modifier.height(8.dp))
            }
            
            items(items = bags, key = { it.index }) {
                WeightItem(it)
                Spacer(Modifier.height(8.dp))
            }
        }
}


@Preview(showBackground = true)
@Composable
private fun JanitorWeightsListPreview() {
    JanitorTheme {
        JanitorWeightsList(
            persistentListOf(
                Bag(0, 1.1),
                Bag(1, 1.2))
        )
    }
}

@Composable
fun WeightItem(bag: Bag, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.weight(0.2f),
            text = "${bag.index}",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.width(40.dp))
        Row(modifier = Modifier.weight(0.8f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${bag.weight} KG",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                modifier = Modifier.padding(4.dp),
                imageVector = Icons.Filled.FitnessCenter,
                tint = when(bag.weight) {
                    in 1.01..1.99 -> Color(0xFF4CAF50)
                    in 2.00..3.00 -> Color(0xFFE91E63)
                    else -> Color.Black
                },
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WeightItemPreview() {
    JanitorTheme {
        WeightItem(Bag(0, 1.1))
    }
}

@Composable
fun TripSummary(trips: ImmutableList<Trip>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text("Optimal trip count ${trips.size}")
        ChipGroup(trips)
    }
}

@Composable
fun ChipGroup(trips: ImmutableList<Trip>, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .heightIn(max = 100.dp)
            .verticalScroll(rememberScrollState())
    ) {
        FlowRow {
            trips.forEach {
                FilterChip(
                    selected = false,
                    onClick = {},
                    label = { Text(it.printBags()) }
                )
            }
        }
    }
}