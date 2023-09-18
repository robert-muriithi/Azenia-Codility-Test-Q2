package com.example.azeniacodilitytest

import okhttp3.Interceptor
import okhttp3.Response

/**
 * The task is to implement an interceptor that adds an Authorization header to the request.
 * The header should contain a token that is retrieved from the TokenStorage.
 * If the token is null or blank, the interceptor should not add the header.
 */


class TestInterceptor(private val tokenStorage: TokenStorage): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenStorage.retrieveToken()
        val request = chain.request()

        return if(!token.isNullOrBlank()){
            val authorizedRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(authorizedRequest)
        } else {
            chain.proceed(request)
        }
    }
}

/*class AuthInterceptor(private val tokenStorage: TokenStorage) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenStorage.retrieveToken()
        val request = chain.request()

        return if (!token.isNullOrBlank()) {
            val authorizedRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(authorizedRequest)
        } else {
            chain.proceed(request)
        }
    }
}*/




