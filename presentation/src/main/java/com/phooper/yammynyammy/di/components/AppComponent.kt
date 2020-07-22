package com.phooper.yammynyammy.di.components

import android.content.Context
import com.ph00.data.di.components.DataComponent
import com.phooper.yammynyammy.App
import com.phooper.yammynyammy.di.modules.NavigationModule
import com.phooper.yammynyammy.di.modules.PresenterModule
import com.phooper.yammynyammy.di.modules.ScreenBindingModule
import com.phooper.yammynyammy.di.modules.UtilsModule
import com.phooper.yammynyammy.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        NavigationModule::class,
        ScreenBindingModule::class,
        UtilsModule::class,
        PresenterModule::class],
    dependencies = [DataComponent::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
            dataComponent: DataComponent
        ): AppComponent
    }

}