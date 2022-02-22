package th.ac.kku.cis.lab.androidroom.repository.database

import androidx.room.*

import th.ac.kku.cis.lab.androidroom.repository.model.Student

@Dao
interface StudentDAO {
    @Insert
    fun insertStudent(student: Student)

    @Query("Select * from students")
    fun gelAllStudents(): List<Student>

    @Update
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)
}