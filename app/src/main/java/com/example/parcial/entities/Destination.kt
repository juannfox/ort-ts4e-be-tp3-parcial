package com.example.parcial.entities

import android.os.Parcel
import android.os.Parcelable

class Destination(
    var destinationName: String,
    var city: String,
    var price: Int,
    var overview: String,
    var images: MutableList<String>
):  Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.createStringArrayList() ?: mutableListOf()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(destinationName)
        parcel.writeString(city)
        parcel.writeInt(price)
        parcel.writeString(overview)
        parcel.createStringArrayList() ?: mutableListOf()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Destination> {
        override fun createFromParcel(parcel: Parcel): Destination {
            return Destination(parcel)
        }

        override fun newArray(size: Int): Array<Destination?> {
            return arrayOfNulls(size)
        }
    }
}