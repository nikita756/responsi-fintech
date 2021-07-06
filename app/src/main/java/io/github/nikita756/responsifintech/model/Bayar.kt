package io.github.nikita756.responsifintech.model

import android.media.Image
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize


@VersionedParcelize
data class Bayar (
    val img: Image,
    val name : String
): Parcelable
