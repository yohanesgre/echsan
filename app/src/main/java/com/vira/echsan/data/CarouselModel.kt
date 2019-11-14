package com.vira.echsan.data

import alirezat775.lib.carouselview.CarouselModel
import com.vira.echsan.data.entities.Promo

class CarouselModel constructor(private var item: Promo) : CarouselModel() {
    fun getPromo(): Promo {
        return item
    }
}
