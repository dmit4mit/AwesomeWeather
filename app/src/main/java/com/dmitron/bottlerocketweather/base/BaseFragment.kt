package com.dmitron.bottlerocketweather.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.dmitron.bottlerocketweather.BR
import com.dmitron.bottlerocketweather.R
import com.dmitron.bottlerocketweather.base.BaseViewModel.BaseScreenEvent
import com.dmitron.bottlerocketweather.main.MainUiController
import com.dmitron.bottlerocketweather.utils.SnackbarData
import com.dmitron.bottlerocketweather.utils.Event
import com.dmitron.bottlerocketweather.utils.executeAfter
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<B : ViewDataBinding, ModelT : BaseViewModel<*>>(
    viewModelClass: KClass<ModelT>,
) : Fragment() {
    @LayoutRes
    abstract fun layoutId(): Int

    // This property is only valid between onCreateView and onDestroyView.
    protected val binding get() = bindingNullable!!
    private var bindingNullable: B? = null
    protected val mainUiController
        get() = requireActivity() as MainUiController

    protected open val viewModel: ModelT by viewModel(viewModelClass)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNullable = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        binding.executeAfter {
            binding.setVariable(BR.viewModel, viewModel)
            binding.lifecycleOwner = this@BaseFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        subscribeBaseEvents()
        observeViewModel(viewModel)
    }

    /**
     * View setup goes here.
     */
    protected open fun setupViews() {

    }

    /**
     * ViewModel observes goes here.
     */
    protected open fun observeViewModel(viewModel: ModelT) {

    }

    private fun subscribeBaseEvents() {
        viewModel.baseScreenEvent().observe(viewLifecycleOwner, Event.EventObserver {
            when (it) {
                BaseScreenEvent.NavigateBack -> navigateUp()
                is BaseScreenEvent.ShowSnackbar -> showSnackbar(it.snackbarData)
            }
        })
    }

    protected fun navigate(navDirections: NavDirections) = findNavController().navigate(navDirections)

    protected fun navigateUp() {
        findNavController().navigateUp()
    }

    protected fun showSnackbar(data: SnackbarData) {
        val message = when(data) {
            is SnackbarData.StringRes -> getString(data.stringRes)
            is SnackbarData.Text -> data.text
        }
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
    }
}