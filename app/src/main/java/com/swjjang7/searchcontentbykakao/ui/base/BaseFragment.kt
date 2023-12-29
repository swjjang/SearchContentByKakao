package com.swjjang7.searchcontentbykakao.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.swjjang7.searchcontentbykakao.BR
import com.swjjang7.searchcontentbykakao.ui.extension.repeatOnStarted
import kotlinx.coroutines.flow.collectLatest

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int,
    private val viewModelClass: Class<VM>,
) : Fragment() {
    private lateinit var _binding: B
    protected val binding: B get() = _binding

    private lateinit var _viewModel: VM
    protected val viewModel: VM get() = _viewModel

    open val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(layoutInflater, layoutResId, container, false)
        _viewModel = ViewModelProvider(this)[viewModelClass]
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.vm, viewModel)
        }

        initBind(binding, viewModel)
        initLayout(binding)
        initViewModel(binding, viewModel)

        repeatOnStarted {
            viewModel.loading.collectLatest {
                if (it) loadingDialog.show() else loadingDialog.dismiss()
            }
        }

        repeatOnStarted {
            viewModel.toast.collectLatest {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    abstract fun initBind(binding: B, viewModel: VM)

    abstract fun initLayout(binding: B)

    abstract fun initViewModel(binding: B, viewModel: VM)
}