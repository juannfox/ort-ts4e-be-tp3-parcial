package com.example.parcial.entities

import android.os.Parcel
import android.os.Parcelable

class Offer(
    var limited_offer: String?,
    var text_tittle: String?,
    var text_description: String?,
    var image_fav: String?,
    var image_card: String?,
    var image_vector: String?,
    ) :Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(limited_offer)
        parcel.writeString(text_tittle)
        parcel.writeString(text_description)
        parcel.writeString(image_fav)
        parcel.writeString(image_card)
        parcel.writeString(image_vector)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Offer> {
        override fun createFromParcel(parcel: Parcel): Offer {
            return Offer(parcel)
        }

        override fun newArray(size: Int): Array<Offer?> {
            return arrayOfNulls(size)
        }
    }
}