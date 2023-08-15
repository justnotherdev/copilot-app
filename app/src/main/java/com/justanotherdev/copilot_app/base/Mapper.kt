package com.justanotherdev.copilot_app.base

interface Mapper<I, O> {
    fun map(input: I): O
}