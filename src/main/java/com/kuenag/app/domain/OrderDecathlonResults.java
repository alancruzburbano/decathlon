package com.kuenag.app.domain;

import com.kuenag.app.model.DecathlonResult;
import com.kuenag.app.model.Results;
import com.kuenag.app.model.TieAcumulator;
import com.kuenag.app.service.ReadData;
import com.kuenag.app.service.ReadDecathlonResults;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderDecathlonResults {

    public void createOrderedResultsReport(){
        ReadData readContactList = new ReadDecathlonResults();
        List<DecathlonResult> competitionResults = readContactList.readDecathlonResultsFromFile();
        for (DecathlonResult itemResult: competitionResults) {
            itemResult.setTotalPoints(DecathlonFormula.evaluateDecathlonFormula(itemResult));
        }
        competitionResults.sort(Comparator.comparing(o -> o.getTotalPoints()));
        Results res = new Results(definePositions(competitionResults));
        DecathlonObjectTransformer.jaxbObjectToXML(res);
    }

    private List<DecathlonResult>  definePositions(List<DecathlonResult> orderedResults){
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

    private List<DecathlonResult> processTieList(List<TieAcumulator> tieList, List<DecathlonResult> orderedResults) {
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
