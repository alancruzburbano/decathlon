package com.kuenag.app.service;

import com.kuenag.app.model.DecathlonResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ReadFromFile.class)
public class ReadDecathlonResultsTest {

    ReadData readDataMock;
    SourceReadable readFromFileMock;
    List<DecathlonResult> tempList;

    @Before
    public void initMock(){
        tempList = new ArrayList<>();
        readDataMock = spy(new ReadDecathlonResults());
        readFromFileMock = mock(ReadFromFile.class);
    }

    @Test
    public void readDecathlonResultsFromFile() {
        tempList.add(new DecathlonResult("John Smith",5.0,9.22,1.50,21.6,2.6,35.81,12.57,60.39,16.43,"5.25.72",4207));
        when(readFromFileMock.readItems()).thenReturn(tempList);
        List<DecathlonResult> actual = readDataMock.readDecathlonResultsFromFile();
        Assert.assertNotNull(actual);

    }
}