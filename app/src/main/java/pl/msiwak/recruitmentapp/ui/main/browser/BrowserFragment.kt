package pl.msiwak.recruitmentapp.ui.main.browser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.msiwak.recruitmentapp.common.observeEvent
import pl.msiwak.recruitmentapp.common.observeFailure
import pl.msiwak.recruitmentapp.databinding.FragmentBrowserBinding
import pl.msiwak.recruitmentapp.databinding.FragmentListBinding
import pl.msiwak.recruitmentapp.ui.base.BaseFragment
import pl.msiwak.recruitmentapp.ui.main.list.ListAdapter
import pl.msiwak.recruitmentapp.util.error.Failure

@AndroidEntryPoint
class BrowserFragment : BaseFragment() {

    companion object {
        const val TAG = "BrowserFragment"
        const val URL = "URL"

        fun newInstance(url: String) = BrowserFragment().apply {
            bundleOf(URL to url)
        }
    }

    private lateinit var binding: FragmentBrowserBinding

    private val mViewModel: BrowserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrowserBinding.inflate(inflater, container, false)

        initObservers()

//        mViewModel.onInit()

        arguments?.getString(URL)?.let {
            initWebView(it)
        }

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


        }
    }

    private fun handleError(event: Failure?) {
        when (event) {
        }
    }

    private fun initWebView(url: String){
        binding.browserWv.run {
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {

                }
            }

            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.setSupportMultipleWindows(false)
            loadUrl(url)
        }
    }

}