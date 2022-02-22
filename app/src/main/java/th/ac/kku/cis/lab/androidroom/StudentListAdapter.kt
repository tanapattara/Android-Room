package th.ac.kku.cis.lab.androidroom

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import th.ac.kku.cis.lab.androidroom.repository.model.Student

class StudentListAdapter : RecyclerView.Adapter<MyViewHolder>() {
    var studentList = mutableListOf<Student>()
    var clickListener: ListClickListener<Student>? = null
    fun setUsers(students: List<Student>) {
        this.studentList = students.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val student = studentList[position]
        holder.studentname.text = student.studentName
        holder.studentid.text = student.studentID
        holder.layout.setOnClickListener {
            clickListener?.onClick(student, position)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
    fun setOnItemClick(listClickListener: ListClickListener<Student>) {
        this.clickListener = listClickListener
    }
}
class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val studentname:TextView = view.findViewById(R.id.list_student_name)
    val studentid:TextView = view.findViewById(R.id.list_student_id)
    val layout:View = view.findViewById(R.id.item_layout)
}


interface ListClickListener<T> {
    fun onClick(data: T, position: Int)
}