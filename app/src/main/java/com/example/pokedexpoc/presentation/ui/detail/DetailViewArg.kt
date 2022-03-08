package com.example.pokedexpoc.presentation.ui.detail

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class DetailViewArg(
    val name: String,
    val imageUrl: String
) : Parcelable
