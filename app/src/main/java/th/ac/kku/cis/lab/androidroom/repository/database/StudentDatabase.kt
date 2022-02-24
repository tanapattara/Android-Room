package th.ac.kku.cis.lab.androidroom.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import th.ac.kku.cis.lab.androidroom.repository.entity.Student

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase(){

    abstract fun studentDAO() : StudentDAO

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase( context: Context,
                         scope: CoroutineScope
        ): StudentDatabase? {
            return INSTANCE ?: synchronized(this) {
                    var instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(StudentDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                    instance
            }
        }
        private class StudentDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.studentDAO())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(studentDAO: StudentDAO) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            studentDAO.deleteAll()

            var student = Student(null,"001","Mickey Mouse","mickey@disney.com")
            studentDAO.insertStudent(student)
            student = Student(null,"002", "Minnie Mouse", "minnie@disney.com")
            studentDAO.insertStudent(student)
        }


        fun destroyInstance() {
            INSTANCE = null
        }
    }
}