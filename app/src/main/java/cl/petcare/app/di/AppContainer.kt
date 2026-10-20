package cl.petcare.app.di

import android.content.Context
import cl.petcare.app.data.local.AppDatabase
import cl.petcare.app.data.prefs.UserPreferences
import cl.petcare.app.data.repository.AuthRepository
import cl.petcare.app.data.repository.PetRepository
import cl.petcare.app.notifications.ReminderScheduler

/** Contenedor de dependencias manual (suficiente para este proyecto, sin librerías extra). */
class AppContainer(context: Context) {
    private val db = AppDatabase.get(context)
    val prefs = UserPreferences(context)
    val authRepo = AuthRepository(db.userDao(), prefs)
    val petRepo = PetRepository(db.petDao(), db.careTaskDao(), ReminderScheduler(context.applicationContext))
}
