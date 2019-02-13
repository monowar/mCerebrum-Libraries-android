package org.md2k.library.phonesensor.mcerebrum;

import android.content.Context;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.md2k.library.phonesensor.mcerebrum.Configuration;
import org.md2k.library.phonesensor.mcerebrum.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/*
 * Copyright (c) 2016, The University of Memphis, MD2K Center
 * - Syed Monowar Hossain <monowar.hossain@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
public class ConfigurationManagerTest {
    private Context context;
    private Object configFromAsset;
    @Before
    public void setUp() throws Exception {
        context = getTargetContext().getApplicationContext();
        configFromAsset = readConfigurationFromAsset();
        assertNotNull(configFromAsset);
    }

    @After
    public void tearDown() throws Exception {
    }

    public String readConfigurationFromAsset() {
        String str=null;
        try {
            InputStream is = context.getAssets().open("phonesensor.default.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            str = new String(buffer, StandardCharsets.UTF_8);
            return str;
        } catch (IOException ex) {
            fail();
        }
        return str;
    }
    @Test
    public void getConfigurationFromObjectTest(){
        Configuration c = ConfigurationManager.getConfigurationFromObject(configFromAsset);
        assertNotNull(c);
        assertEquals(c.size(),5);
    }
    @Test
    public void getRequiredPermissionList(){
        String[] list = ConfigurationManager.getRequiredPermissionList(context, configFromAsset);
        Log.d("abc","abc");
        assertEquals(list.length, 2);
    }

}
