package th.ac.kku.cis.lab.androidroom

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import th.ac.kku.cis.lab.androidroom.repository.StudentRepository
import th.ac.kku.cis.lab.androidroom.repository.entity.Student

class StudentViewModel (private val repository: StudentRepository) : ViewModel() {
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Student>> = repository.getAllStudents.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }
}
class StudentViewModelFactory(private val repository: StudentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}