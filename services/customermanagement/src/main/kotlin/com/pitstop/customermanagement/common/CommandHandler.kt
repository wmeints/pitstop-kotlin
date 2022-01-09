package com.pitstop.customermanagement.common

interface CommandHandler<in A : Request<B>, B : Response> {
    fun execute(request: A): B
}