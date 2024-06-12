package com.example.fetchexercise.model

import kotlinx.serialization.Serializable

/**
 * The class definition for a sortable [ JsonItem ] object, which is parsed
 * from the JSON of the GET request response
 */
@Serializable
data class JsonItem (
    val id : Int,
    val listId : Int,
    val name : String? = null
) : Comparable<JsonItem> {
    override fun compareTo(other: JsonItem): Int {
        return compareValuesBy(this, other, { it.listId }, { it.name?.replace("Item ","")?.toIntOrNull() })
    }
}
