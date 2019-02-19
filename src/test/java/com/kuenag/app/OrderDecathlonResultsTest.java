package com.kuenag.app;

import com.kuenag.app.domain.OrderDecathlonResults;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderDecathlonResultsTest {

    private OrderDecathlonResults orderDecathlonResults;

    @Before
    public void creadOrganizer(){
        orderDecathlonResults = new OrderDecathlonResults();
    }

    @Test
    public void createOrderedResultsReportTestHappyPath(){
        Assert.assertNotNull(orderDecathlonResults.createOrderedResultsReport());
    }

}
