package target.gonzalez.lopez.ernesto.casestudy.ui.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.afterTextChanged(afterEnteredContentChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("TBD expected behaviour ")
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("TBD expected behaviour")
        }

        override fun afterTextChanged(p0: Editable?) {
            p0?.let { textEdit ->
                afterEnteredContentChanged.invoke(
                    textEdit.toString()
                )
            }
        }

    })
}