package com.scout.mtgapp.data.entity.card

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Card(
    @SerializedName("object") val objectType: String,
    val id: String,
    @SerializedName("oracle_id") val oracleId: String,
    @SerializedName("multiverse_ids") val multiverseIds: List<Int>,
    @SerializedName("mtgo_id") val mtgoId: Int?,
    @SerializedName("tcgplayer_id") val tcgplayerId: Int?,
    @SerializedName("cardmarket_id") val cardmarketId: Int?,
    val name: String,
    val lang: String,
    @SerializedName("released_at") val releasedAt: String,
    val uri: String,
    @SerializedName("scryfall_uri") val scryfallUri: String,
    val layout: String,
    @SerializedName("highres_image") val highresImage: Boolean,
    @SerializedName("image_status") val imageStatus: String,
    @SerializedName("image_uris") val imageUris: ImageUris?,
    @SerializedName("mana_cost") val manaCost: String,
    val cmc: Double,
    @SerializedName("type_line") val typeLine: String,
    @SerializedName("oracle_text") val oracleText: String,
    val power: String?,
    val toughness: String?,
    val colors: List<String>,
    @SerializedName("color_indicator") val colorIndicator: List<String>?,
    @SerializedName("color_identity") val colorIdentity: List<String>,
    val keywords: List<String>,
    val legalities: Legalities,
    val games: List<String>,
    val reserved: Boolean,
    val foil: Boolean,
    val nonfoil: Boolean,
    val finishes: List<String>,
    val oversized: Boolean,
    val promo: Boolean,
    val reprint: Boolean,
    val variation: Boolean,
    @SerializedName("set_id") val setId: String,
    val set: String,
    @SerializedName("set_name") val setName: String,
    @SerializedName("set_type") val setType: String,
    @SerializedName("set_uri") val setUri: String,
    @SerializedName("set_search_uri") val setSearchUri: String,
    @SerializedName("scryfall_set_uri") val scryfallSetUri: String,
    @SerializedName("rulings_uri") val rulingsUri: String,
    @SerializedName("prints_search_uri") val printsSearchUri: String,
    @SerializedName("collector_number") val collectorNumber: String,
    val digital: Boolean,
    val rarity: String,
    @SerializedName("card_back_id") val cardBackId: String,
    val artist: String,
    @SerializedName("artist_ids") val artistIds: List<String>,
    @SerializedName("illustration_id") val illustrationId: String,
    @SerializedName("border_color") val borderColor: String,
    val frame: String,
    @SerializedName("frame_effects") val frameEffects: List<String>?,
    @SerializedName("security_stamp") val securityStamp: String?,
    @SerializedName("full_art") val fullArt: Boolean,
    val textless: Boolean,
    val booster: Boolean,
    @SerializedName("story_spotlight") val storySpotlight: Boolean,
    @SerializedName("edhrec_rank") val edhrecRank: Int?,
    val prices: Prices
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
    val future: String,
    val historic: String,
    val timeless: String,
    val gladiator: String,
    val pioneer: String,
    val explorer: String,
    val modern: String,
    val legacy: String,
    val pauper: String,
    val vintage: String,
    val penny: String,
    val commander: String,
    val oathbreaker: String,
    val standardbrawl: String,
    val brawl: String,
    val alchemy: String,
    val paupercommander: String,
    val duel: String,
    val oldschool: String,
    val premodern: String,
    val predh: String
): Parcelable

@Parcelize
data class Prices(
    val usd: String?,
    @SerializedName("usd_foil") val usdFoil: String?,
    @SerializedName("usd_etched") val usdEtched: String?,
    val eur: String?,
    @SerializedName("eur_foil") val eurFoil: String?,
    val tix: String?
): Parcelable
