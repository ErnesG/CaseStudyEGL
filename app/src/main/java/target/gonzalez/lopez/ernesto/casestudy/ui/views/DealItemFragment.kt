package target.gonzalez.lopez.ernesto.casestudy.ui.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dagger.android.support.AndroidSupportInjection
import target.gonzalez.lopez.ernesto.casestudy.R
import target.gonzalez.lopez.ernesto.casestudy.databinding.FragmentDealItemBinding
import target.gonzalez.lopez.ernesto.casestudy.model.Product
import target.gonzalez.lopez.ernesto.casestudy.model.Status
import target.gonzalez.lopez.ernesto.casestudy.viewmodel.DealsViewModel
import javax.inject.Inject

class DealItemFragment : Fragment() {
    private var dealItemBindingFragment: FragmentDealItemBinding? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val args: DealItemFragmentArgs by navArgs()

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
        dealItemBindingFragment = FragmentDealItemBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return dealItemBindingFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.dealId
        activity?.also {
            val viewModel = ViewModelProvider(it,viewModelFactory).get(
                DealsViewModel::class.java
            )
            viewModel.getProductDetail(id).observe(viewLifecycleOwner,
                Observer { response ->
                    response?.apply {
                        when(status) {
                            Status.LOADING -> {
                                setupLoading(true)
                            }
                            Status.SUCCESS -> {
                                setupLoading(false)
                                showUiElements()

                            }
                            Status.ERROR -> {
                                hideUi()
                                setupLoading(false)
                            }
                        }
                    }

                }
            )
        }
    }
    private fun setupLoading(display: Boolean) {
        dealItemBindingFragment?.let {
            with(it) {
                loadingDetailProgressBar.visibility = if(display) {
                     View.VISIBLE
                } else {
                     View.GONE
                }
            }
        }
    }
    private fun loadDataToUi(product: Product) {
        dealItemBindingFragment?.let {
            with(it) {
                productTitle.text = product.title
                productDesc.text = product.description
                regularPriceLabel.text = getString(R.string.regular_price_format_placeholder, "-")
                regularPrice.apply {
                   text = if (product.regularPrice == null) {
                       "$0.00"
                   } else {
                       ((product.regularPrice?.amountInCents ?: 0)/100).toString()
                   }
                }

            }
            (activity as? AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as? AppCompatActivity?)?.supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }
    private fun showUiElements() {
        dealItemBindingFragment?.let {
            with(it) {
                controlsLayout.visibility = View.VISIBLE
                productDesc.visibility = View.VISIBLE
                productTitle.visibility = View.VISIBLE
                regularPrice.visibility = View.VISIBLE
                regularPriceLabel.visibility = View.VISIBLE
                salePriceLbl.visibility = View.VISIBLE
                productImageView.visibility = View.VISIBLE
            }
        }
    }
    private fun hideUi() {
        dealItemBindingFragment?.let {
            with(it) {
                controlsLayout.visibility = View.GONE
                productDesc.visibility = View.GONE
                productTitle.visibility = View.GONE
                regularPrice.visibility = View.GONE
                regularPriceLabel.visibility = View.GONE
                salePriceLbl.visibility = View.GONE
                productImageView.visibility = View.GONE
            }
        }
    }
}