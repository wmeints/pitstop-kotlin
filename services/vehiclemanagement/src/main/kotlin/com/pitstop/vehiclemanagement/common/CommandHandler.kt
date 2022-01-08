package com.pitstop.vehiclemanagement.common

interface CommandHandler<in A: Request<B>, B: Response> {
    fun execute(request: A): B
}