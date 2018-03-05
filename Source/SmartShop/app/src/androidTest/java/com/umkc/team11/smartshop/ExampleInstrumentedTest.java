package com.umkc.team11.smartshop;

import android.content.ClipData;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.umkc.team11.smartshop", appContext.getPackageName());
    }

    private SearchData dt;

    @Before
    public void set()
    {
        dt = new SearchData(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void testPreConditions()
    {
        Assert.assertNotNull(dt);
    }

    @Test
    public void testGetItems()
    {
        ArrayList<ItemData> list = dt.getShoppingItems("");
        Assert.assertEquals(list.size(), 10);
    }
}
