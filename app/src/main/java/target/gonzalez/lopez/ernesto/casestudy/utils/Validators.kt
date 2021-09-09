package target.gonzalez.lopez.ernesto.casestudy.utils
/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */
fun validateCreditCard(creditCardNumber: String): Boolean {
        if(creditCardNumber.length !in 13..19){
            return false
        }
        val integerDigits = getIntegerDigits(creditCardNumber)
        val toValidateDigit = Character.getNumericValue(creditCardNumber[creditCardNumber.lastIndex])
        val reverseInput = invertedCreditCardDigits(integerDigits)
        productOddElementsInput(reverseInput)
        return (reverseInput.sum() + toValidateDigit) % 10 == 0
    }
    private fun getIntegerDigits(creditCardDigits: String): MutableList<Int> {
        // dropping last digit
        val lastIndex = creditCardDigits.lastIndex
        val lastDigitRemovedInput = creditCardDigits.substring(0, lastIndex)
        val resultList = mutableListOf<Int>()
        for (digit in lastDigitRemovedInput) {
            resultList.add(Character.getNumericValue(digit))
        }
       return resultList
    }
    private fun invertedCreditCardDigits(inputList: MutableList<Int>): MutableList<Int> {
        inputList.reverse()
        return inputList
    }
    private fun productOddElementsInput(reverseInput: MutableList<Int>) {
        for(elementIndex in 0 until reverseInput.size) {
            var element: Int
            if (elementIndex % 2 !=0) {
                element = reverseInput[elementIndex-1] * 2
                if (element > 9) {
                    element -=9
                }
                reverseInput[elementIndex-1] = element
            }
        }
    }
