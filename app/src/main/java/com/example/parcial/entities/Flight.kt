package com.example.parcial.entities

import android.os.Parcel
import android.os.Parcelable

class Flight(
    var departure_airport: Airport?,
    var arrival_airport: Airport?,
    var duration: Int,
    var airplane: String?,
    var airline: String?,
    var airline_logo: String?,
    var travel_class: String?,
    var flight_number: String?,
    var legroom: String?,
    var overnight: Boolean
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
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(departure_airport, flags)
        parcel.writeParcelable(arrival_airport, flags)
        parcel.writeInt(duration)
        parcel.writeString(airplane)
        parcel.writeString(airline)
        parcel.writeString(airline_logo)
        parcel.writeString(travel_class)
        parcel.writeString(flight_number)
        parcel.writeString(legroom)
        parcel.writeByte(if (overnight) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Flight> {
        override fun createFromParcel(parcel: Parcel): Flight {
            return Flight(parcel)
        }

        override fun newArray(size: Int): Array<Flight?> {
            return arrayOfNulls(size)
        }
    }
}