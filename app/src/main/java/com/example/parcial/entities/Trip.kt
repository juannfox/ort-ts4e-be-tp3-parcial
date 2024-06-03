package com.example.parcial.entities

import android.os.Parcel
import android.os.Parcelable

class Trip(
    var departureAirport: Airport?,
    var arrivalAirport: Airport?,
    var duration: Int,
    var airplane: String?,
    var airline: String?,
    var airlineLogo: String?,
    var travelClass: String?,
    var flightNumber: String?,
    var legroom: String?,
    var overnight: Boolean,
    var price: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable<Airport>(Airport::class.java.classLoader),
        parcel.readParcelable(Airport::class.java.classLoader),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(departureAirport, flags)
        parcel.writeParcelable(arrivalAirport, flags)
        parcel.writeInt(duration)
        parcel.writeString(airplane)
        parcel.writeString(airline)
        parcel.writeString(airlineLogo)
        parcel.writeString(travelClass)
        parcel.writeString(flightNumber)
        parcel.writeString(legroom)
        parcel.writeByte(if (overnight) 1 else 0)
        parcel.writeInt(duration)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trip> {
        override fun createFromParcel(parcel: Parcel): Trip {
            return Trip(parcel)
        }

        override fun newArray(size: Int): Array<Trip?> {
            return arrayOfNulls(size)
        }
    }
}