package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinEntity(val id: String,
                      val name: String,
                      val symbol: String,
                      val rank: Int,
                      @SerializedName("is_new") val isNew: Boolean,
                      @SerializedName("is_active") val isActive: Boolean,
                      val description: String?,
                      val message: String?,
                      @SerializedName("open_source") val openSource: Boolean?,
                      @SerializedName("started_at") val startedAt: String?,
                      @SerializedName("development_status") val developmentStatus: String?,
                      @SerializedName("hardware_wallet") val hardwareWallet: Boolean?,
                      @SerializedName("proof_type") val proofType: String?,
                      @SerializedName("org_structure") val organizationStructure: String?,
                      @SerializedName("hash_algorithm") val algorithm: String?,
                      val tags: List<TagEntity>?,
                      val type: String,
                      val team: List<TeamMemberEntity>?,
                      val links: LinksEntity?,
                      val whitepaper: WhitepaperEntity?): Parcelable