package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.seed.SeedHymns
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("TRIUNE HYMN", appName)
    }

    @Test
    fun `verify seed hymns data populated`() {
        val hymns = SeedHymns.initialHymns
        assertTrue("Seed hymns should not be empty", hymns.isNotEmpty())
        val amazingGrace = hymns.find { it.hymnNumber == 1 }
        assertEquals("Amazing Grace", amazingGrace?.title)
    }
}
