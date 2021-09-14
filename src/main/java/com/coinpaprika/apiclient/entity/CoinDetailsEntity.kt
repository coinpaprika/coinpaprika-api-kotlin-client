package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinDetailsEntity(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "symbol") val symbol: String,
    @Json(name = "rank") val rank: Int,
    @Json(name = "is_new") val isNew: Boolean,
    @Json(name = "is_active") val isActive: Boolean,
    @Json(name = "description") val description: String?,
    @Json(name = "message") val message: String?,
    @Json(name = "open_source") val openSource: Boolean?,
    @Json(name = "started_at") val startedAt: String?,
    @Json(name = "development_status") val developmentStatus: String?,
    @Json(name = "hardware_wallet") val hardwareWallet: Boolean?,
    @Json(name = "proof_type") val proofType: String?,
    @Json(name = "org_structure") val organizationStructure: String?,
    @Json(name = "hash_algorithm") val algorithm: String?,
    @Json(name = "contracts") val contracts: List<ContractEntity>?,
    @Json(name = "tags") val tags: List<TagEntity>?,
    @Json(name = "type") val type: String,
    @Json(name = "team") val team: List<TeamMemberEntity>?,
    @Json(name = "links") val links: LinksEntity?,
    @Json(name = "links_extended") val linksExtended: List<LinkExtendedEntity>?,
    @Json(name = "whitepaper") val whitepaper: WhitepaperEntity?,
    @Json(name = "last_data_at") val lastDataUpdate: String?
)