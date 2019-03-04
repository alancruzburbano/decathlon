package com.kuenag.app.domain;

import com.kuenag.app.model.DecathlonResult;
import com.kuenag.app.model.Results;
import com.kuenag.app.model.TieAcumulator;
import com.kuenag.app.service.ReadData;
import com.kuenag.app.service.ReadDecathlonResults;
import com.kuenag.app.utils.PropertiesUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest({OrderDecathlonResults.class, DecathlonFormula.class})
public class OrderDecathlonResultsTest {

    List<DecathlonResult> competitionResults;
    OrderDecathlonResults orderDecathlonResults;
    ReadData mockReadDecathlonList;
    DecathlonResult mockDecathlonResult;

    @Before
    public void createOrganizer(){
        orderDecathlonResults = spy(new OrderDecathlonResults());
        mockReadDecathlonList = mock(ReadDecathlonResults.class);
        orderDecathlonResults.readDecathlonList = mockReadDecathlonList;
        mockDecathlonResult = mock(DecathlonResult.class);
        competitionResults = new ArrayList<>();
        competitionResults.add(new DecathlonResult("John Smith",12.57,5.00,9.22,1.50,60.39,16.43,21.60,2.60,35.81,"5.25.72",4207));
        competitionResults.add(new DecathlonResult("Jaan Lepp",13.75,4.84,10.12,1.50,68.44,19.18,30.85,2.80,33.88,"6.22.75",3494));
        competitionResults.add(new DecathlonResult("Jane Doe",13.04,4.53,7.79,1.55,64.72,18.74,24.20,2.40,28.20,"6.50.76",3199));

    }

    @Test
    public void createOrderedResultsReport() {
        when(mockReadDecathlonList.readDecathlonResultsFromFile()).thenReturn(competitionResults);
        mockStatic(DecathlonFormula.class);
        when(DecathlonFormula.evaluateParametersByCompetitionType(anyObject())).thenReturn(Double.MIN_NORMAL);
        Results actual = orderDecathlonResults.createOrderedResultsReport();
        Assert.assertEquals(actual.getResults().size(), 3);
    }

    @Test
    public void definePositions() {
        List<DecathlonResult> actual = orderDecathlonResults.definePositions(competitionResults);
        Assert.assertEquals(actual.get(0).getPosition(), "1");
        Assert.assertEquals(actual.get(1).getPosition(), "2");
        Assert.assertEquals(actual.get(2).getPosition(), "3");

    }

    @Test
    public void processTieList() {
        competitionResults.add(new DecathlonResult("Larry Olmos",13.04,4.53,7.79,1.55,64.72,18.74,24.20,2.40,28.20,"6.50.76",3199));
        List<TieAcumulator> tieList = new ArrayList<>();
        tieList.add(new TieAcumulator(2,3199));
        tieList.add(new TieAcumulator(3,3199));
        List<DecathlonResult> expected = orderDecathlonResults.processTieList(tieList,competitionResults);
        Assert.assertTrue(expected.get(2).getPosition().equals("3-4"));
        Assert.assertTrue(expected.get(3).getPosition().equals("3-4"));
    }
}