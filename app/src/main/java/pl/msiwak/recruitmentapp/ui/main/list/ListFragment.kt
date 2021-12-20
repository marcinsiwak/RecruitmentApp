package pl.msiwak.recruitmentapp.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.msiwak.recruitmentapp.common.observeEvent
import pl.msiwak.recruitmentapp.common.observeFailure
import pl.msiwak.recruitmentapp.databinding.FragmentListBinding
import pl.msiwak.recruitmentapp.ui.base.BaseFragment
import pl.msiwak.recruitmentapp.util.error.Failure

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
            is ListEvents.OpenBrowser -> {
                val action = ListFragmentDirections.actionListFragmentToBrowserFragment(event.url)
                findNavController().navigate(action)
            }
            ListEvents.CloseApp -> activity?.finish()
        }
    }

    private fun handleError(event: Failure?) {
        when (event) {
        }
    }

}