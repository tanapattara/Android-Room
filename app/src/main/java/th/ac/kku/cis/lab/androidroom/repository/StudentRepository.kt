package th.ac.kku.cis.lab.androidroom.repository

import android.content.Context
import th.ac.kku.cis.lab.androidroom.repository.database.AppDatabase
import th.ac.kku.cis.lab.androidroom.repository.database.StudentDAO
import th.ac.kku.cis.lab.androidroom.repository.model.Student

class StudentRepository(context: Context) {
    var db: StudentDAO = AppDatabase.getInstance(context)?.studentDAO()!!

    //Fetch All the students
    fun getAllStudents(): List<Student> {
        return db.gelAllStudents()
    }

    // Insert new student
    fun insertStudent(users: Student) {
        Thread(Runnable {
            db.insertStudent(users)
        }).start()
    }

    // update user
    fun updateStudent(users: Student) {
        db.updateStudent(users)
    }

    // Delete user
    fun deleteStudent(users: Student) {
        db.deleteStudent(users)
    }
}