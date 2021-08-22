package com.infosupport.pitstop.customers

data class PagedResult<T>(val items: List<T>, val pageIndex: Int, val pageSize: Int, val totalItems: Long)