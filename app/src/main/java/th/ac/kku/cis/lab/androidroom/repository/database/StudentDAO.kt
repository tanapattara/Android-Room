package th.ac.kku.cis.lab.androidroom.repository.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

import th.ac.kku.cis.lab.androidroom.repository.entity.Student

@Dao
interface StudentDAO {
    @Insert
    suspend fun insertStudent(student: Student)

    @Query("Select * from students")
    fun gelAllStudents(): Flow<List<Student>>

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM students")
    suspend fun deleteAll()
}