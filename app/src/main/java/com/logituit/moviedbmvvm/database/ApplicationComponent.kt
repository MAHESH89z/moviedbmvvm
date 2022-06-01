package com.logituit.moviedbmvvm.database

import android.content.Context
import com.logituit.moviedbmvvm.MainActivity
import com.logituit.moviedbmvvm.api.RetrofitHelper
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitHelper::class])

interface ApplicationComponent{
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context):ApplicationComponent
    }
}
