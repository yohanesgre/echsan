package com.vira.echsan.data.mapper

import com.vira.echsan.features.umroh.data.PaketUmroh

class PaketUmrohMapper(private val paketUmrohResponse: PaketUmrohResponse) :
    Mapper<PaketUmroh> {
    override fun getMapping(): PaketUmroh {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
/*
    @WorkerThread
    override fun getMapping(): PaketUmroh {
        return PaketUmroh(
            paketUmrohResponse.id,
            paketUmrohResponse.category_id,
            paketUmrohResponse.name,
            paketUmrohResponse.description,
            paketUmrohResponse.price,
            paketUmrohResponse.point,
            paketUmrohResponse.image,
            paketUmrohResponse.date_of_departure,
            paketUmrohResponse.created_at,
            paketUmrohResponse.updated_at,
            paketUmrohResponse.travel_vendor_id,
            paketUmrohResponse.departure_city_id,
            paketUmrohResponse.product_category_id,
            paketUmrohResponse.quota,
            paketUmrohResponse.discount,
            paketUmrohResponse.slug,
            paketUmrohResponse.date_of_return,
            paketUmrohResponse.departure_plane_id,
            paketUmrohResponse.return_plane_id,
            paketUmrohResponse.departure_information,
            paketUmrohResponse.return_information,
            paketUmrohResponse.day_amount,
            paketUmrohResponse.makkah_hotel,
            paketUmrohResponse.madinah_hotel,
            paketUmrohResponse.makkah_star,
            paketUmrohResponse.madinah_star,
            paketUmrohResponse.discount_price,
            paketUmrohResponse.category,
            paketUmrohResponse.travel_vendor,
            paketUmrohResponse.departure_city,
            paketUmrohResponse.product_category,
            paketUmrohResponse.departure_plane,
            paketUmrohResponse.return_plane
        )
    }*/
}