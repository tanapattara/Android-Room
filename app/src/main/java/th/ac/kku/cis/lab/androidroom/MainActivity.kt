package th.ac.kku.cis.lab.androidroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import th.ac.kku.cis.lab.androidroom.repository.entity.Student

class MainActivity : AppCompatActivity() {

    private val newStudentActivityRequestCode = 1
    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as StudentApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_student)
        val adapter = StudentListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

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
            //startActivityForResult(intent, newStudentActivityRequestCode)
            val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                if(it.resultCode == Activity.RESULT_OK){
                    it.data?.getStringExtra("NEW_STUDENT")?.let { reply ->
                        val student = Student(null,reply[1].toString(),reply[2].toString(),reply[3].toString(),)
                        studentViewModel.insert(student)
                    }
                }
            }
        }

    }
}