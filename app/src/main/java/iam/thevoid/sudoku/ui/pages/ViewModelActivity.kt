package iam.thevoid.sudoku.ui.pages

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import iam.thevoid.sudoku.ui.view.MvvmView
import iam.thevoid.sudoku.ui.viewmodel.ViewModel

/**
 * Created by iam on 07/09/2017.
 */
@SuppressLint("Registered")
abstract class ViewModelActivity<VM, VB : ViewDataBinding, V : MvvmView> : BaseActivity() where VM : ViewModel<V> {

    abstract fun layoutId(): Int

    abstract fun variableId(): Int

    abstract fun viewModel(): VM

    private lateinit var viewModel: VM

    private lateinit var viewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutId())
        viewModel = viewModel()
        try {
            viewModel.attachView(this as V)
        } catch (e: ClassCastException) {
            throw IllegalArgumentException("Activity must implements the same interface as uses as third parameter of generic params")
        }
        viewBinding.setVariable(variableId(), viewModel)
        viewModel.onCreate()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}