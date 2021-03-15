package com.russmsit.githubclient.github.core.di

import com.russmsit.githubclient.github.core.api.GithubApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GithubModule {
    val networkModule = module {
        single<OkHttpClient.Builder> {
            val timeout: Long = try {
                getProperty("timeout").toLong()
            } catch (exception: Exception) {
                5
            }

            OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
        }

        single<Retrofit> {
            val githubUrl: String = try {
                getProperty("github_url")
            } catch (exception: Exception) {
                ""
            }

            Retrofit.Builder()
                .client(get<OkHttpClient.Builder>().build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(githubUrl)
                .build()
        }

        single<GithubApi> {
            get<Retrofit>().create(GithubApi::class.java)
        }
    }
}