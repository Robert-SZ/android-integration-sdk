package ru.modulkassa.pos.integration.entity.payment

import android.os.Bundle
import ru.modulkassa.pos.integration.entity.Bundable
import java.math.BigDecimal

/**
 * Информация о платеже, который необходимо провести
 */
data class PayRequest(
    /**
     * Идентификатор чека
     */
    val checkId: String,
    /**
     * Сумма к оплате
     */
    val amount: BigDecimal,
    /**
     * Описание платежа
     */
    val description: String
) : Bundable {

    companion object {
        private const val KEY_CHECK_ID = "check_id"
        private const val KEY_AMOUNT = "amount"
        private const val KEY_DESCRIPTION = "description"

        fun fromBundle(bundle: Bundle): PayRequest {
            return PayRequest(
                checkId = bundle.getString(KEY_CHECK_ID),
                amount = BigDecimal(bundle.getString(KEY_AMOUNT)),
                description = bundle.getString(KEY_DESCRIPTION)
            )
        }
    }

    override fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(KEY_CHECK_ID, checkId)
        bundle.putString(KEY_AMOUNT, amount.toPlainString())
        bundle.putString(KEY_DESCRIPTION, description)
        return bundle
    }

}