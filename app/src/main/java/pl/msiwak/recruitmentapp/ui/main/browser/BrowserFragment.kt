package pl.msiwak.recruitmentapp.ui.main.browser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import pl.msiwak.recruitmentapp.common.observeEvent
import pl.msiwak.recruitmentapp.common.observeFailure
import pl.msiwak.recruitmentapp.databinding.FragmentBrowserBinding
import pl.msiwak.recruitmentapp.ui.base.BaseFragment
import pl.msiwak.recruitmentapp.ui.main.list.ListFragmentDirections
import pl.msiwak.recruitmentapp.util.error.Failure

@AndroidEntryPoint
class BrowserFragment : BaseFragment() {

    private lateinit var binding: FragmentBrowserBinding

    private val mViewModel: BrowserViewModel by viewModels()
    private val args by navArgs<BrowserFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrowserBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = mViewModel
        }

        mViewModel.onInit()
        initObservers()

        initWebView(args.url)

        return binding.root
    }

    private fun initObservers() {
        mViewModel.apply {
            observeEvent(event, { handleEvent(it) })
            observeFailure(errorEvent, { handleError(it) })
        }
    }


    private fun handleEvent(event: BrowserEvents?) {
        when (event) {
            BrowserEvents.NavigateBack -> {
                val action = BrowserFragmentDirections.actionBrowserFragmentToListFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun handleError(event: Failure?) {
        when (event) {
        }
    }

    private fun initWebView(url: String) {
        binding.browserWv.run {
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    mViewModel.onPageLoaded()
                }
            }

            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.setSupportMultipleWindows(false)
            loadUrl(url)
        }
    }

}