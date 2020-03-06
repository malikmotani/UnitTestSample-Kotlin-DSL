package com.unittestsample.app.presentation.user

import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.unittestsample.app.R
import com.unittestsample.app.domain.account.entity.User
import com.unittestsample.app.presentation.common.Resource
import com.unittestsample.app.presentation.common.Status
import com.unittestsample.app.presentation.common.base.BaseViewModelFragment
import com.unittestsample.app.presentation.common.base.SafeObserver
import kotlinx.android.synthetic.main.fragment_random_users.*

class RandomUserFragment : BaseViewModelFragment<RandomUserViewModel>() {
    private val randomUserAdapter by lazy { RandomUserAdapter() }

    override fun getContentResource() = R.layout.fragment_random_users

    override fun injectDagger() {
        initPresenterComponent()?.inject(this)
    }

    override fun buildViewModel(): RandomUserViewModel {
        return ViewModelProviders.of(this, viewModelFactory)[RandomUserViewModel::class.java]
    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        viewModel.randomUserLiveEvent.observe(
            viewLifecycleOwner,
            SafeObserver(this::handleRandomUserResponse)
        )
    }

    override fun initViews() {
        super.initViews()
        rvUser.adapter = randomUserAdapter
    }

    private fun handleRandomUserResponse(response: Resource<ArrayList<User>>) {
        when (response.status) {
            Status.LOADING -> progressBar.show()
            Status.ERROR -> handleRandomUserError(response)
            Status.SUCCESS -> handleRandomSuccess(response.data)
        }
    }

    private fun handleRandomSuccess(response: ArrayList<User>?) {
        progressBar.hide()
        response?.let {
            // handle success here
            randomUserAdapter.setItems(it)
        }
    }

    private fun handleRandomUserError(response: Resource<ArrayList<User>>) {
        progressBar.hide()
        response.throwable?.let {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        }
    }
}