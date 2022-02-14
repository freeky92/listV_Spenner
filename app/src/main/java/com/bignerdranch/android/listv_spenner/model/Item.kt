package com.bignerdranch.android.listv_spenner.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Item(
    val id: Long,
    val title: String,
    val note: String
) : Parcelable