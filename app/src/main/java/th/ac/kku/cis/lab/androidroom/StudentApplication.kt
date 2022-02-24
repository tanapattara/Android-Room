package th.ac.kku.cis.lab.androidroom

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import th.ac.kku.cis.lab.androidroom.repository.StudentRepository
import th.ac.kku.cis.lab.androidroom.repository.database.StudentDatabase

class StudentApplication: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { StudentDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { StudentRepository(database!!.studentDAO()) }
}
