package ru.otus.daggerhomework

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.otus.daggerhomework.di.annotations.ActivityContext
import javax.inject.Inject

class ViewModelProducer @Inject constructor(
    @ActivityContext private val context: Context,
    private val colorGenerator: ColorGenerator,
    private val stateColor: MutableStateFlow<Int>
) {

    fun generateColor() {
        if (context !is FragmentActivity) throw RuntimeException("Здесь нужен контекст активити")
        Toast.makeText(context, "Color sent", Toast.LENGTH_LONG).show()
        context.lifecycleScope.launch {
            stateColor.value = colorGenerator.generateColor()
        }
    }
}