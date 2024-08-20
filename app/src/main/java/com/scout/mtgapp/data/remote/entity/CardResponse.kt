package com.scout.mtgapp.data.remote.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardResponse(
    @SerializedName("object") val objectType: String,
    val id: String,
    val name: String,
    @SerializedName("scryfall_uri") val scryfallUri: String,
    @SerializedName("image_uris") val imageUris: ImageUris?,
    val cmc: Double,
    @SerializedName("type_line") val typeLine: String,
    @SerializedName("oracle_text") val oracleText: String,
    val power: String?,
    val toughness: String?,
    val colors: List<String>,
    val legalities: Legalities,
    @SerializedName("set_id") val setId: String,
    val set: String,
    @SerializedName("set_name") val setName: String,
    @SerializedName("set_type") val setType: String,
    val rarity: String,
    @SerializedName("edhrec_rank") val edhrecRank: Int?
): Parcelable

@Parcelize
data class ImageUris(
    val small: String,
    val normal: String,
    val large: String,
    val png: String,
    @SerializedName("art_crop") val artCrop: String,
    @SerializedName("border_crop") val borderCrop: String
): Parcelable

@Parcelize
data class Legalities(
    val standard: String,
    val historic: String,
    val timeless: String,
    val pioneer: String,
    val explorer: String,
    val commander: String,
    val brawl: String,
    val alchemy: String
): Parcelable
