package th.ac.kku.cis.lab.androidroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import th.ac.kku.cis.lab.androidroom.repository.StudentRepository
import th.ac.kku.cis.lab.androidroom.repository.model.Student

class NewStudentActivity : AppCompatActivity() {
    var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        var etStudentID:EditText = findViewById(R.id.etStudentID)
        var etStudentName:EditText = findViewById(R.id.etStudentName)
        var etStudentEmail:EditText = findViewById(R.id.etStudentEmail)

        student = intent.getParcelableExtra("student")

        student?.let {
            etStudentID.setText(it.studentID)
            etStudentName.setText(it.studentName)
            etStudentEmail.setText(it.studentEmail)
        } ?: kotlin.run {

        }

        val repo = StudentRepository(this)

        val btnSave:Button = findViewById(R.id.btnSave)
        btnSave.setOnClickListener {
            if (etStudentID.text.isNotEmpty() && etStudentName.text.isNotEmpty() && etStudentEmail.text.isNotEmpty()) {

                student?.let {
                    val editedStudent = Student(it.id,
                        etStudentID.text.toString(),
                        etStudentName.text.toString(),
                        etStudentEmail.text.toString()
                    )
                    repo.updateStudent(editedStudent)
                } ?: kotlin.run {
                    val user = Student(null,
                        etStudentID.text.toString(),
                        etStudentName.text.toString(),
                        etStudentEmail.text.toString()
                    )
                    repo.insertStudent(user)
                }

            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}