package target.gonzalez.lopez.ernesto.casestudy.ui.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import target.gonzalez.lopez.ernesto.casestudy.databinding.FragmentDealListBinding
import target.gonzalez.lopez.ernesto.casestudy.model.Deals
import target.gonzalez.lopez.ernesto.casestudy.model.Status
import target.gonzalez.lopez.ernesto.casestudy.ui.adapters.DealItemAdapter
import target.gonzalez.lopez.ernesto.casestudy.viewmodel.DealsViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [DealListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DealListFragment : Fragment() {

    private var productsListFragmentBinding:FragmentDealListBinding? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val onDealClicked: (Int?) -> Unit = {
        it?.let {
            val action = DealListFragmentDirections.toProductDetail(it)
            this.findNavController().navigate(action)
        }
    }
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        productsListFragmentBinding = FragmentDealListBinding.inflate(layoutInflater, container, false)
        return productsListFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.also {
            val dealsViewModel = ViewModelProvider(it, viewModelFactory).get(
                DealsViewModel::class.java
            )
            dealsViewModel.getDeals().observe(viewLifecycleOwner,
            Observer { deals ->
                deals?.apply {
                    when(status) {
                        Status.LOADING -> {
                           showLoading(true)
                        }
                        Status.SUCCESS -> {
                            showLoading(false)
                            data?.let { deal ->
                                loadData(deal)
                            }
                        }
                        Status.ERROR -> {
                            showLoading(false)
                        }
                    }
                }
            })

        }
    }
    private fun showLoading(show: Boolean) {
        productsListFragmentBinding?.let {
            with(it) {
                if(show) {
                    loadingDetailProgressBar.visibility = View.VISIBLE
                } else {
                    loadingDetailProgressBar.visibility = View.GONE
                }
            }
        }
    }
    private fun loadData(deals: Deals) {
        productsListFragmentBinding?.let {
            with(it) {
                dealsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                dealsRecyclerView.adapter = DealItemAdapter(deals.products, onDealClicked)
            }
        }
    }
}