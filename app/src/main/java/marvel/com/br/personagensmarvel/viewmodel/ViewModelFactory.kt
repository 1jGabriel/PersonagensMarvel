package marvel.com.br.personagensmarvel.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject


class ViewModelFactory<T : BaseViewModel> @Inject constructor(viewModel: T) : ViewModelProvider.Factory {

    private val mViewModel: T = viewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mViewModel as? T ?: throw IllegalArgumentException("Unknow class name")
    }

}