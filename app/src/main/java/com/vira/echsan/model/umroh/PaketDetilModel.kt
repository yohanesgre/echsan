package com.vira.echsan.model.umroh

import android.os.Parcel
import android.os.Parcelable

data class PaketDetilModel(val travel:String,
                           val lokasiTravel:String,
                           val tglBerangkat:String,
                           val tglPulang:String,
                           val durasi:String,
                           val lokasi:String,
                           val kuota:Int,
                           val point:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(travel)
        parcel.writeString(lokasiTravel)
        parcel.writeString(tglBerangkat)
        parcel.writeString(tglPulang)
        parcel.writeString(durasi)
        parcel.writeString(lokasi)
        parcel.writeInt(kuota)
        parcel.writeInt(point)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PaketDetilModel> {
        override fun createFromParcel(parcel: Parcel): PaketDetilModel {
            return PaketDetilModel(parcel)
        }

        override fun newArray(size: Int): Array<PaketDetilModel?> {
            return arrayOfNulls(size)
        }
    }
}