package com.kuenag.app.domain;

import com.kuenag.app.model.DecathlonResult;
import com.kuenag.app.model.Results;
import com.kuenag.app.model.TieAcumulator;
import com.kuenag.app.service.ReadData;
import com.kuenag.app.service.ReadDecathlonResults;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that perform operations over DecathlonResult list of items
 *
 * @author Alvaro Andres Cruz Burbano
 */
public class OrderDecathlonResults {

    Logger logger = Logger.getLogger(OrderDecathlonResults.class.getName());
    ReadData readDecathlonList;
    DecathlonObjectTransformer decathlonObjectTransformer;

    public OrderDecathlonResults() {
        this.readDecathlonList = new ReadDecathlonResults();
    }

    public Results createOrderedResultsReport(){
        logger.log(Level.INFO, "Decathlon report generation has started");
        List<DecathlonResult> competitionResults = readDecathlonList.readDecathlonResultsFromFile();
        competitionResults.stream().filter(Objects::nonNull);
        for (DecathlonResult itemResult: competitionResults) {
            logger.log(Level.INFO, String.format("Calculating score for athlete: %s",itemResult.getAthleteName()));
            itemResult.setTotalPoints(DecathlonFormula.evaluateDecathlonFormula(itemResult));
        }
        logger.log(Level.INFO, "Organizing results by score...");
        Collections.sort(competitionResults, Collections.reverseOrder(Comparator.comparing(o -> o.getTotalPoints())));
        Results res = new Results(definePositions(competitionResults));
        decathlonObjectTransformer = new DecathlonObjectTransformer();
        if(decathlonObjectTransformer.jaxbObjectToXML(res))
            logger.log(Level.INFO, "Report generated, review output directory...");
        return res;
    }

    /**
     * This method define the position based on the final score by athlete and resolve ties if is the case
     * @param orderedResults
     * @return
     */
    public List<DecathlonResult>  definePositions(List<DecathlonResult> orderedResults){
        logger.log(Level.INFO, "Defining positions based on final results...");
        int index=0;
        List<TieAcumulator> tieList = new ArrayList<>();
        while(index < orderedResults.size()){
            int currentPos = orderedResults.get(index).getTotalPoints();
            if(!(tieList.size()>0))
                tieList.add(new TieAcumulator(index,currentPos));
            if(index<orderedResults.size()-1) {
                int nextPos = orderedResults.get(index + 1).getTotalPoints();
                if (currentPos == nextPos) {
                    tieList.add(new TieAcumulator(index + 1, nextPos));
                }
                else{
                    orderedResults = processTieList(tieList,orderedResults);
                    tieList.clear();
                }
            }
            else{
                orderedResults = processTieList(tieList,orderedResults);
                tieList.clear();
            }
            index++;
        }
        return orderedResults;
    }

    public List<DecathlonResult> processTieList(List<TieAcumulator> tieList, List<DecathlonResult> orderedResults) {
        String tagPosition = "";
        for (TieAcumulator tie : tieList) {
            tagPosition = tagPosition + String.valueOf(tie.getIndexPosition() + 1) + "-";
        }
        tagPosition = tagPosition.substring(0, tagPosition.length() - 1);
        for (TieAcumulator tie : tieList) {
            orderedResults.get(tie.getIndexPosition()).setPosition(tagPosition);
        }
        return orderedResults;
    }

}
