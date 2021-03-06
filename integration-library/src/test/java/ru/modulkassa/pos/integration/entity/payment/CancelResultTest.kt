package ru.modulkassa.pos.integration.entity.payment

import android.os.Bundle
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CancelResultTest {

    @Test
    fun FromBundle_ByDefault_ReturnsResult() {
        val bundle = Bundle()

        val result = CancelResult.fromBundle(bundle)

        assertThat(result.slip, equalTo(listOf()))
    }

    @Test
    fun ToBundle_ByDefault_SavesSlip() {
        val result = CancelResult(listOf("some text"))

        val bundle = result.toBundle()

        assertThat(bundle.getStringArrayList("slip").toList(), equalTo(listOf("some text")))
    }

}