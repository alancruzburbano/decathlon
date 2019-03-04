package com.kuenag.app.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Properties.class)
public class PropertiesUtilTest {

    Properties defaultProps;

    @Before
    public void initMock(){
        defaultProps = mock(Properties.class);
    }

    @Test
    public void getValidProperty() {
        Assert.assertTrue("xml_results_output.xml".equals(PropertiesUtil.getProperty("app.decathlon.file.output.name")));
    }

    @Test(expected = IOException.class)
    public void testException() throws IOException {
        doThrow(new IOException()).when(defaultProps).load(new FileInputStream("src/main/resources/applicatio.."));
        String readProperty = PropertiesUtil.getProperty("app.decathlon.file.output.name");
        Assert.assertNull(readProperty);
    }

}