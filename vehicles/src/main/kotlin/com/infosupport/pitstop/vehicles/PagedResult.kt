package com.infosupport.pitstop.vehicles

data class PagedResult<T>(val items: List<T>, val pageIndex: Int, val pageSize: Int, val totalItems: Long)