package th.ac.kku.cis.lab.androidroom.repository

import android.content.Context
import android.util.Log
import th.ac.kku.cis.lab.androidroom.repository.database.AppDatabase
import th.ac.kku.cis.lab.androidroom.repository.database.StudentDAO
import th.ac.kku.cis.lab.androidroom.repository.model.Student

class StudentRepository(context: Context) {
    var db: StudentDAO = AppDatabase.getInstance(context)?.studentDAO()!!

    //Fetch All the students
    fun getAllStudents(): List<Student> {
        Log.d("LAB", "get all Student data")
        return db.gelAllStudents()
    }

    // Insert new student
    fun insertStudent(student: Student) {
        Thread(Runnable {
            db.insertStudent(student)
            Log.d("LAB", "Inserted Student")
        }).start()
    }

    // update user
    fun updateStudent(student: Student) {
        db.updateStudent(student)
        Log.d("LAB", "Update Student data")
    }

    // Delete user
    fun deleteStudent(student: Student) {
        db.deleteStudent(student)
        Log.d("LAB", "Delete Student data")
    }
}