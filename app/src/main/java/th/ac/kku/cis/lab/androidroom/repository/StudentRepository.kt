package th.ac.kku.cis.lab.androidroom.repository

import kotlinx.coroutines.flow.Flow
import androidx.annotation.WorkerThread
import th.ac.kku.cis.lab.androidroom.repository.database.StudentDAO
import th.ac.kku.cis.lab.androidroom.repository.entity.Student

class StudentRepository(private val studentDAO: StudentDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val getAllStudents: Flow<List<Student>> = studentDAO.gelAllStudents()
    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(student: Student) {
        studentDAO.insertStudent(student)
    }
    /*
    var db: StudentDAO = AppDatabase.getInstance(context)?.studentDAO()!!
    //Fetch All the students
    fun getAllStudents(): Flow<List<Student>> {
        Log.d("LAB", "get all Student data")
        return db.gelAllStudents()
    }

    // Insert new student
    fun insertStudent(student: Student) {
        db.insertStudent(student)
    }

    // update user
    suspend fun updateStudent(student: Student) {
        db.updateStudent(student)
        Log.d("LAB", "Update Student data")
    }

    // Delete user
    suspend fun deleteStudent(student: Student) {
        db.deleteStudent(student)
        Log.d("LAB", "Delete Student data")
    }
    */

}