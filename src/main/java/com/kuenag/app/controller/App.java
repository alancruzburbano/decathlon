package com.kuenag.app.controller;

import com.kuenag.app.domain.OrderDecathlonResults;

public class App 
{

    public static void main( String[] args )
    {
        OrderDecathlonResults result = new OrderDecathlonResults();
        result.createOrderedResultsReport();
    }
}
