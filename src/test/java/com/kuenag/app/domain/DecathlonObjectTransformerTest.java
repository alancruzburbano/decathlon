package com.kuenag.app.domain;

import com.kuenag.app.model.DecathlonResult;
import com.kuenag.app.model.Results;
import com.kuenag.app.utils.PropertiesUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PropertiesUtil.class, DecathlonObjectTransformer.class})
public class DecathlonObjectTransformerTest {

    DecathlonObjectTransformer decathlonObjectTransformer;
    private List<DecathlonResult> expectedItems;

    @Before
    public void init(){
        decathlonObjectTransformer = new DecathlonObjectTransformer();
        expectedItems = new ArrayList<>();
        DecathlonResult item1 = new DecathlonResult("John Smith",12.57,5.00,9.22,1.50,60.39,16.43,21.60,2.60,35.81,"5.25.72",4207);
        item1.setPosition("1");
        DecathlonResult item2 = new DecathlonResult("Jaan Lepp",13.75,4.84,10.12,1.50,68.44,19.18,30.85,2.80,33.88,"6.22.75",3494);
        item2.setPosition("2");
        DecathlonResult item3 = new DecathlonResult("Jane Doe",13.04,4.53,7.79,1.55,64.72,18.74,24.20,2.40,28.20,"6.50.76",3199);
        item3.setPosition("3-4");
        DecathlonResult item4 = new DecathlonResult("Larry Olmos",13.04,4.53,7.79,1.55,64.72,18.74,24.20,2.40,28.20,"6.50.76",3199);
        item4.setPosition("3-4");
        expectedItems.add(item1);
        expectedItems.add(item2);
        expectedItems.add(item3);
        expectedItems.add(item4);
    }

    @Test
    public void testJaxbObjectToXML(){
        mockStatic(PropertiesUtil.class);
        when(PropertiesUtil.getProperty("app.decathlon.file.output.path")).thenReturn("src/test/resources/output");
        when(PropertiesUtil.getProperty("app.decathlon.file.output.name")).thenReturn("decathlon_result_test.xml");
        Results resToTransform = new Results(expectedItems);
        Assert.assertTrue(decathlonObjectTransformer.jaxbObjectToXML(resToTransform));
    }

    @Test
    public void jaxObjectToXmlException(){
        mockStatic(PropertiesUtil.class);
        when(PropertiesUtil.getProperty("app.decathlon.file.output.name")).thenReturn("not_valid@");
        Results resToTransform = new Results(expectedItems);
        Assert.assertFalse(decathlonObjectTransformer.jaxbObjectToXML(resToTransform));
    }
}