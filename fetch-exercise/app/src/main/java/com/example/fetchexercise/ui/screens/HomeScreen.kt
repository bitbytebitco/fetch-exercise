package com.example.fetchexercise.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.fetchexercise.R
import com.example.fetchexercise.model.JsonItem

@Composable
fun HomeScreen(
    fetchUiState: FetchUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when(fetchUiState) {
        is FetchUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxWidth())
        is FetchUiState.Error -> ErrorScreen(modifier = modifier.fillMaxWidth())
        is FetchUiState.Success -> JsonItemList(
            jsonItemList = fetchUiState.jsonItems
        )
    }
}

/**
 * The Composable display for when the GET request is loading
 */
@Composable
fun LoadingScreen(modifier : Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The Composable display for when an exception occurs
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

/**
 * The Card item that displays the json object
 */
@Composable
fun JsonItemCard(jsonItem : JsonItem, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Card(modifier = Modifier) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("id: ")
                        }
                        append("${jsonItem.id}, ")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("listId: ")
                        }
                        append("${jsonItem.listId}, ")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("name: ")
                        }
                        append("${jsonItem.name}")
                    }

                )
            }
        }
    }
}

/**
 * The LazyColumn that contains the list of json item Cards.
 */
@Composable
fun JsonItemList(jsonItemList : List<JsonItem>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(jsonItemList) { item ->
            if(item.name != "" && item.name != null) {
                JsonItemCard(
                    jsonItem = item,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun JsonItemCardPreview() {
    JsonItemCard(JsonItem(1, 4, "test"))
}
