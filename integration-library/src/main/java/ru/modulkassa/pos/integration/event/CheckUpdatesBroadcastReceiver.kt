package ru.modulkassa.pos.integration.event

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import ru.modulkassa.pos.integration.entity.check.Check
import java.math.BigDecimal

/**
 * Класс для получения сообщений об изменении чека
 */
abstract class CheckUpdatesBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val KEY_CHECK_ID = "check_id"
        const val KEY_CHECK_LINKED_ID = "linked_id"
        const val KEY_CHECK_SUM = "check_sum"
        const val KEY_CHECK_DETAILS = "check"
    }

    private val gson = Gson()

    override fun onReceive(context: Context, intent: Intent?) {
        intent?.let {
            onCheckClosed(
                context,
                it.getStringExtra(KEY_CHECK_ID),
                it.getStringExtra(KEY_CHECK_LINKED_ID),
                BigDecimal(it.getStringExtra(KEY_CHECK_SUM)),
                gson.fromJson(it.getStringExtra(KEY_CHECK_DETAILS), Check::class.java))
        }
    }

    /**
     * Метод сообщающий о том, что чек закрыт
     * @param checkId - ID чека в приложении МодульКасса
     * @param linkedDocId - ID в системе, которая сформировала чек (заказ, чек из другого приложения и тд)
     */
    abstract fun onCheckClosed(context: Context, checkId: String, linkedDocId: String?,
                               sum: BigDecimal, check: Check)
}