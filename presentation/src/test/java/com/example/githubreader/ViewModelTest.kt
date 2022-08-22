package com.example.githubreader

import androidx.lifecycle.LiveData
import com.example.githubreader.utils.LiveDataTestObserver

open class ViewModelTest {

    protected fun <T> LiveData<T>.test(): LiveDataTestObserver<T> {
        val testObserver = LiveDataTestObserver<T>()
        observeForever(testObserver)

        return testObserver
    }
}