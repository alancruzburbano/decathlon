package com.kuenag.app.service;

import com.kuenag.app.model.DecathlonResult;
import com.kuenag.app.utils.PropertiesUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PropertiesUtil.class)
public class ReadFromFileTest {

    SourceReadable readFromFileSourceSpy;
    DecathlonResult decathlonMock;

    @Before
    public void init(){
        readFromFileSourceSpy = spy(new ReadFromFile());
        decathlonMock = mock(DecathlonResult.class);
    }

    @Test
    public void testReadItems() {
        when(((ReadFromFile) readFromFileSourceSpy).verifyHeaders()).thenReturn(0);
        when(((ReadFromFile) readFromFileSourceSpy).readPath()).thenReturn("src\\test\\resources\\input\\results_test.csv");
        List<DecathlonResult> actualItems = readFromFileSourceSpy.readItems();
        assertTrue(actualItems.size() > 0);
    }

    @Test
    public void testProcessFileLine(){
        String exampleLine = "John Smith;12.57;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72";
        DecathlonResult actual = ((ReadFromFile) readFromFileSourceSpy).processFileLine(exampleLine,1);
        Assert.assertNotNull(actual);
    }

    @Test
    public void testProcessFileLineBadNumParams(){
        String exampleLine = "Jaan Lepp;13.75;4.84;10.12;1.50;68.44;19.18;30.85;2.80;33.88";
        DecathlonResult actual = ((ReadFromFile) readFromFileSourceSpy).processFileLine(exampleLine,1);
        Assert.assertNull(actual);
    }

    @Test
    public void testProcessFileLineBadParamValue(){
        String exampleLine = "Jaan Lepp;1o3.75;4.84;10.12;1.50;68.44;19.18;30.85;2.80;33.88";
        DecathlonResult actual = ((ReadFromFile) readFromFileSourceSpy).processFileLine(exampleLine,1);
        Assert.assertNull(actual);
    }

    @Test
    public void testReadPathValid(){
        mockStatic(PropertiesUtil.class);
        when(PropertiesUtil.getProperty("app.decathlon.file.input.path")).thenReturn("src\\test\\resources\\input\\results1.csv");
        assertTrue(Files.exists(Paths.get(((ReadFromFile) readFromFileSourceSpy).readPath())));
    }

    @Test
    public void verifyHeadersTest(){
        mockStatic(PropertiesUtil.class);
        when(PropertiesUtil.getProperty("app.decathlon.file.headers")).thenReturn("Y");
        int expected = 1;
        int actual = ((ReadFromFile) readFromFileSourceSpy).verifyHeaders();
        assertEquals(expected, actual );
    }

    @Test
    public void verifyReadItemsException(){
        when(((ReadFromFile) readFromFileSourceSpy).readPath()).thenReturn("src\\test\\resources\\input\\notValidFile.ss");
        List<DecathlonResult> actualItems = readFromFileSourceSpy.readItems();
        assertEquals(0,actualItems.size());
    }

}
