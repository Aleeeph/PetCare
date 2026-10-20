package cl.petcare.app

import android.app.Application
import cl.petcare.app.di.AppContainer
import cl.petcare.app.notifications.NotificationHelper

class PetCareApp : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
        NotificationHelper.createChannel(this)
    }
}
