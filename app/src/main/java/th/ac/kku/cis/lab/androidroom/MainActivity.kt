package th.ac.kku.cis.lab.androidroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import th.ac.kku.cis.lab.androidroom.repository.StudentRepository
import th.ac.kku.cis.lab.androidroom.repository.model.Student

class MainActivity : AppCompatActivity() {

    lateinit var adapter: StudentListAdapter
    val repo: StudentRepository by lazy {
        StudentRepository(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = StudentListAdapter()
        var recyclerview_student:RecyclerView = findViewById(R.id.recyclerview_student)
        recyclerview_student.layoutManager = LinearLayoutManager(this)
        recyclerview_student.adapter = adapter


        adapter.setOnItemClick(object : ListClickListener<Student>{
            override fun onClick(data: Student, position: Int) {
                Log.d("LAB", "item click")
                val intent = Intent(this@MainActivity,NewStudentActivity::class.java)
                intent.putExtra("student", data)
                startActivity(intent)
            }
        })



        var btnNewStudent:Button = findViewById(R.id.btnNew)
        btnNewStudent.setOnClickListener{
            val intent = Intent(this,NewStudentActivity::class.java)
            startActivity(intent)
        }

        fetchUsers()
    }
    override fun onResume() {
        super.onResume()
        fetchUsers()
    }

    fun fetchUsers() {
        val allUsers = repo.getAllStudents()
        adapter.setUsers(allUsers)
    }
}