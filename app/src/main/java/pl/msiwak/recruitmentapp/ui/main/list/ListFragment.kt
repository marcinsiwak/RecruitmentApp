package pl.msiwak.recruitmentapp.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.msiwak.recruitmentapp.R
import pl.msiwak.recruitmentapp.common.observeEvent
import pl.msiwak.recruitmentapp.common.observeFailure
import pl.msiwak.recruitmentapp.databinding.FragmentListBinding
import pl.msiwak.recruitmentapp.ui.base.BaseFragment
import pl.msiwak.recruitmentapp.ui.main.browser.BrowserFragment.Companion.BUNDLE_URL
import pl.msiwak.recruitmentapp.util.error.Failure
import pl.msiwak.recruitmentapp.util.error.ListFailure

@AndroidEntryPoint
class ListFragment : BaseFragment() {

    private lateinit var binding: FragmentListBinding

    private val mViewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = mViewModel
            listRv.adapter = ListAdapter()
        }

        initObservers()

        mViewModel.onInit()

        return binding.root
    }

    private fun initObservers() {
        mViewModel.apply {
            observeEvent(event, { handleEvent(it) })
            observeFailure(errorEvent, { handleError(it) })
        }
    }


    private fun handleEvent(event: ListEvents?) {
        when (event) {
            is ListEvents.OpenBrowser -> openBrowser(event.url)
            is ListEvents.OpenBrowserForLargeScreen -> openBrowserForLargeScreen(event.url)
            ListEvents.CloseApp -> activity?.finish()
        }
    }

    private fun openBrowser(url: String) {
        val action =
            ListFragmentDirections.actionListFragmentToBrowserFragment(url)
        findNavController().navigate(action)
    }

    private fun openBrowserForLargeScreen(url: String) {
        val navHost =
            childFragmentManager.findFragmentById(R.id.nav_list_fragment) as NavHostFragment

        navHost.navController.navigate(
            R.id.browserFragmentMD,
            bundleOf(BUNDLE_URL to url)
        )
    }

    private fun handleError(event: Failure?) {
        when (event) {
            ListFailure.GetDataFailure -> showErrorToast()
            ListFailure.GetDataFromLocalDbFailure -> showErrorToast()
            else -> Unit
        }
    }

    private fun showErrorToast() {
        Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }
}