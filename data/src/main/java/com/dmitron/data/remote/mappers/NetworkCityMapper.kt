package com.dmitron.data.remote.mappers

import com.dmitron.data.remote.model.NetworkCity
import com.dmitron.data.remote.model.NetworkCityList
import com.dmitron.data.utils.orDefault
import com.dmitron.domain.models.City

internal fun mapNetworkCityList(source: NetworkCityList): List<City> =
    source.cities?.map { mapNetworkCity(it) }.orEmpty()

internal fun mapNetworkCity(source: NetworkCity): City =
    City(
        id = source.geoNameId.orDefault(0),
        name = source.name.orEmpty(),
        countryCode = source.countryCode.orEmpty(),
        imageURLs = mapNetworkImageUrls(source.imageURLs ?: NetworkCity.NetworkImageURLs()),
        alternateNames = source.alternateNames.orEmpty(),
        latitude = source.latitude.orDefault(),
        longitude = source.longitude.orDefault(),
        timezone = source.timezone.orEmpty(),
        modificationDate = source.modificationDate.orEmpty(),
        population = source.population.orDefault(),
    )

internal fun mapNetworkImageUrls(networkImageURLs: NetworkCity.NetworkImageURLs): City.ImageURLs =
    City.ImageURLs(
        mdpiImageURL = networkImageURLs.androidImageURLs?.mdpiImageURL.orEmpty(),
        hdpiImageURL = networkImageURLs.androidImageURLs?.hdpiImageURL.orEmpty(),
        xhdpiImageURL = networkImageURLs.androidImageURLs?.xhdpiImageURL.orEmpty(),
    )