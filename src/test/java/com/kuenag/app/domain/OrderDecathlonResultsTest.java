package com.kuenag.app.domain;

import org.junit.Test;

public class OrderDecathlonResultsTest {

    private OrderDecathlonResults orderDecathlonResults;

    @Test
    public void createOrderedResultsReportTestHappyPath(){
        orderDecathlonResults = new OrderDecathlonResults();
        orderDecathlonResults.createOrderedResultsReport();
    }

}
