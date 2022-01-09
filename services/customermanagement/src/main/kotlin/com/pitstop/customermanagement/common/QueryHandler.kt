package com.pitstop.customermanagement.common

interface QueryHandler<in A : Request<B>, B : Response> {
    fun execute(request: A): B
}