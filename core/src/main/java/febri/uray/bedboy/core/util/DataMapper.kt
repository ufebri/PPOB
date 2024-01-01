package febri.uray.bedboy.core.util

import febri.uray.bedboy.core.data.source.local.entity.PriceListEntity
import febri.uray.bedboy.core.data.source.remote.response.ResponseData
import febri.uray.bedboy.core.domain.model.MenuList
import febri.uray.bedboy.core.domain.model.ProductList

object DataMapper {

    fun mapEntitiesToDomainMenu(input: List<PriceListEntity>): List<MenuList> =
        input.map { MenuList(idMenu = it.productCategory ?: "", nameMenu = it.productCategory ?: "") }

    fun mapEntitiesToDomainPriceList(input: List<PriceListEntity>): List<ProductList> = input.map {
        ProductList(
            it.productCode,
            it.productDescription,
            it.productNominal,
            it.productDetails,
            it.productPrice,
            it.productType,
            it.activePeriod,
            it.status,
            it.iconUrl,
            it.productCategory
        )
    }

    fun mapResponseToEntitiesPriceList(input: ResponseData): List<PriceListEntity> {
        val mListEntity = ArrayList<PriceListEntity>()
        input.pricelist.map {
            val priceList = PriceListEntity(
                it.productCode,
                it.productDescription,
                it.productNominal,
                it.productDetails,
                it.productPrice,
                it.productType,
                it.activePeriod,
                it.status,
                it.iconUrl,
                it.productCategory
            )
            mListEntity.add(priceList)
        }
        return mListEntity
    }
}