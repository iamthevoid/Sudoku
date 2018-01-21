package iam.thevoid.sudoku.pages

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import iam.thevoid.sudoku.viewmodel.ViewModel

/**
 * Created by iam on 07/09/2017.
 */
@SuppressLint("Registered")
open abstract class ViewModelActivity<VM, VB> : BaseActivity() where VM : ViewModel, VB : ViewDataBinding {

    abstract fun layoutId(): Int

    abstract fun variableId(): Int

    abstract fun viewModel(): VM

    lateinit private var viewModel: VM
    lateinit private var viewBinding: VB
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutId())
        viewModel = viewModel()
        viewBinding.setVariable(variableId(), viewModel)
        viewModel.onCreate(this)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause(this)
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy(this)
    }
}