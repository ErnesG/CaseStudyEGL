package target.gonzalez.lopez.ernesto.casestudy.ui.views.payment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import dagger.android.support.AndroidSupportInjection
import target.gonzalez.lopez.ernesto.casestudy.R
import target.gonzalez.lopez.ernesto.casestudy.databinding.FragmentPaymentBinding
import target.gonzalez.lopez.ernesto.casestudy.ui.extensions.afterTextChanged
import target.gonzalez.lopez.ernesto.casestudy.utils.validateCreditCard
/**
 * A simple [Fragment] subclass.
 * Use the [PaymentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentFragment : DialogFragment() {
    private var paymentDialogFragmentBinding: FragmentPaymentBinding? = null
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        paymentDialogFragmentBinding = FragmentPaymentBinding.inflate(layoutInflater, container,
            false)
        return paymentDialogFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paymentDialogFragmentBinding?.let {
            with(it) {
                submit.isEnabled = false
                cancelBtn.setOnClickListener { dismiss() }
                cardNumber.afterTextChanged {
                    submit.isEnabled = validateCreditCard(it)
                }
                submit.setOnClickListener {
                    Toast.makeText(root.context,
                        getString(
                            R.string.added_payment_method
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        
    }
}