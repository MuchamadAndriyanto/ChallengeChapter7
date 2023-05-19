package com.example.bismillahjadi.model

import com.google.gson.annotations.SerializedName


@Suppress("RedundantSuppression", "RedundantSuppression", "RedundantSuppression", "unused")
data class ListMovie(
    @Suppress("unused", "unused", "unused") @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @Suppress("unused", "unused") @SerializedName("total_pages")
   val totalPages: Int,
    @Suppress("unused") @SerializedName("total_results")
    val totalResults: Int

)