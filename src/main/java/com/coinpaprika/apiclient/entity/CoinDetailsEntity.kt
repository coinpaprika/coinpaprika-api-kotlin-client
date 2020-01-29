package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class CoinDetailsEntity(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("rank") val rank: Int,
    @SerializedName("is_new") val isNew: Boolean,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("description") val description: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("open_source") val openSource: Boolean?,
    @SerializedName("started_at") val startedAt: String?,
    @SerializedName("development_status") val developmentStatus: String?,
    @SerializedName("hardware_wallet") val hardwareWallet: Boolean?,
    @SerializedName("proof_type") val proofType: String?,
    @SerializedName("org_structure") val organizationStructure: String?,
    @SerializedName("hash_algorithm") val algorithm: String?,
    @SerializedName("tags") val tags: List<TagEntity>?,
    @SerializedName("type") val type: String,
    @SerializedName("team") val team: List<TeamMemberEntity>?,
    @SerializedName("links") val links: LinksEntity?,
    @SerializedName("whitepaper") val whitepaper: WhitepaperEntity?,
    @SerializedName("last_data_at") val lastDataUpdate: String?
)