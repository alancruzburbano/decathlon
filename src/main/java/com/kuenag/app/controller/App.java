package com.kuenag.app.controller;

import com.kuenag.app.domain.OrderDecathlonResults;

/**
 * This class Initialize the decathlon application, executing the method in the domain package
 * OrderDecathlonResults
 *
 * @author Alvaro Andres Cruz Burbano
 */
public class App 
{

    public static void main( String[] args )
    {
        OrderDecathlonResults result = new OrderDecathlonResults();
        result.createOrderedResultsReport();
    }
}
