package com.gangofdevelopers.currencyrates.common.mapper

interface Mapper<in T, out K> {
    fun map(data: T): K
}

interface MapperWithParams<in T, in L : MapperParams, out K> {
    fun map(data: T, params: L): K
}

abstract class MapperParams
