package com.kuenag.app.service;

import com.kuenag.app.model.DecathlonResult;
import com.kuenag.app.utils.Constants;
import com.kuenag.app.utils.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.kuenag.app.utils.TextVerifiable.*;
/**
 * This is the concrete implementation of SourceReadable for
 * file method of lecture, this class reads the application.properties file
 * and perform the creation of a decathlon result list
 *
 * @author Alvaro Andres Cruz Burbano
 */

public class ReadFromFile implements SourceReadable {

    Logger logger = Logger.getLogger(ReadFromFile.class.getName());

    ObjectBuilder createDecathlonResult = (tokens) -> {
        DecathlonResult item = new DecathlonResult();
        String token = tokens.nextToken();
        if(isValidName.test(token)){ item.setAthleteName(token);}
        token = tokens.nextToken();
        if(isValidDecimal.test(token)){ item.setOneHundredMeters(Double.valueOf(token));}
        token = tokens.nextToken();
        if(isValidDecimal.test(token)){ item.setLongJump(Double.valueOf(token));}
        token = tokens.nextToken();
        if(isValidDecimal.test(token)){ item.setShotPut(Double.valueOf(token));}
        token = tokens.nextToken();
        if(isValidDecimal.test(token)){ item.setHighJump(Double.valueOf(token));}
        token = tokens.nextToken();
        if(isValidDecimal.test(token)){ item.setFourHundredMeters(Double.valueOf(token));}
        token = tokens.nextToken();
        if(isValidDecimal.test(token)){ item.setOneHundredTenMetersHurdles(Double.valueOf(token));}
        token = tokens.nextToken();
        if(isValidDecimal.test(token)){ item.setDiscusThrow(Double.valueOf(token));}
        token = tokens.nextToken();
        if(isValidDecimal.test(token)){ item.setPoleVault(Double.valueOf(token));}
        token = tokens.nextToken();
        if(isValidDecimal.test(token)){ item.setJavelinThrow(Double.valueOf(token));}
        token = tokens.nextToken();
        if(isValidTime.test(token)){ item.setOneThousandFiveHundredMeters(token);}
        return item;
    };

    protected boolean validateInputRules(StringTokenizer tokens){
        String token = null;
        int index = 0;
        boolean isValid = true;
        while(tokens.hasMoreTokens() && isValid){
            token = tokens.nextToken();
            switch(index){
                case 0:{
                    isValid = (index == 0 && isValidName.test(token)) ? true : false;
                    break;
                }
                case 1:{
                    isValid = (index == 1 && isValidDecimal.test(token)) ? true : false;
                    break;
                }
                case 2:{
                    isValid = (index == 2 && isValidDecimal.test(token)) ? true : false;
                    break;
                }
                case 3:{
                    isValid = (index == 3 && isValidDecimal.test(token)) ? true : false;
                    break;
                }
                case 4:{
                    isValid = (index == 4 && isValidDecimal.test(token)) ? true : false;
                    break;
                }
                case 5:{
                    isValid = (index == 5 && isValidDecimal.test(token)) ? true : false;
                    break;
                }
                case 6:{
                    isValid = (index == 6 && isValidDecimal.test(token)) ? true : false;
                    break;
                }
                case 7:{
                    isValid = (index == 7 && isValidDecimal.test(token)) ? true : false;
                    break;
                }
                case 8:{
                    isValid = (index == 8 && isValidDecimal.test(token)) ? true : false;
                    break;
                }
                case 9:{
                    isValid = (index == 9 && isValidDecimal.test(token)) ? true : false;
                    break;
                }
                case 10:{
                    isValid = (index == 10 && isValidTime.test(token)) ? true : false;
                    break;
                }
            }
            index++;
        }
        return isValid;
    }

    /**
     * List from file stored in customized path or default path, the parameters comes from application.properties
     * if the system cannot find the path from  app.contact.list.file.path then will use app.contact.list.file.default.path that
     * will use the file placed inside the project.
     *
     * @return contact list
     */
    @Override
    public List<DecathlonResult> readItems() {
        List<DecathlonResult> decathlonResults = new ArrayList<>();
        try {
            String originPathFile = readPath();
            List<String> linesFromFile = Files.readAllLines(Paths.get(originPathFile));
            for (int i = verifyHeaders(); i < linesFromFile.size(); i++) {
                decathlonResults.add(processFileLine(linesFromFile.get(i), i));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, String.format("The system cannot read the file on given path: %1$s message: %2$s", PropertiesUtil.getProperty("app.decathlon.file.input.path"), e.getLocalizedMessage()));
        }
        return decathlonResults;
    }

    /**
     * This method get a line from the file, tokenize the chain according to the character specified
     * in app.decathlon.file.token.separator property
     *
     * @param line from file
     * @return DecathlonResult object according to the model
     */
    protected DecathlonResult processFileLine(String line, int lineNumber) {
        String fileSeparatorCharacter = PropertiesUtil.getProperty("app.decathlon.file.token.separator");
        StringTokenizer st = new StringTokenizer(line, fileSeparatorCharacter);
        DecathlonResult decathlonResult = null;
        if (Constants.TOKENS_NUM != st.countTokens()) {
            if(validateInputRules(st)) {
                decathlonResult = createDecathlonResult.mapToItem(new StringTokenizer(line, fileSeparatorCharacter));
            }
        } else {
            logger.log(Level.SEVERE, String.format("The line #%1$s does not contain the minimum number of tokens required, null register in line: %2$s", lineNumber, line));
        }
        return decathlonResult;
    }

    /**
     * According to app.decathlon.file.headers property the system will return 0 or 1
     *
     * @return the index line where the system must start to read the file
     */
    protected int verifyHeaders() {
        logger.log(Level.INFO,String.format("Validating if file has headers"));
        if ("Y".equalsIgnoreCase(PropertiesUtil.getProperty("app.decathlon.file.headers"))) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * The method verifies if the file in pathFile property exist in the system,
     * else the systems will use the CSV file inside the project as a default alternative
     *
     * @return Custom path or default path.
     */
    protected String readPath() {
        String originPath = PropertiesUtil.getProperty("app.decathlon.file.input.path");
        if (isValidPath.test(originPath)) {
            logger.log(Level.INFO,String.format("Read file in provided path: %s", PropertiesUtil.getProperty("app.decathlon.file.input.path")));
            return originPath;
        } else {
            File directory = new File(""); //Retrieve the root project path
            logger.log(Level.INFO,String.format("The Path in the property is not valid, using default " +
                    "file in project folder: %s", directory.getAbsolutePath() + Constants.DEFAULT_FILE_PATH));
            return directory.getAbsolutePath() + Constants.DEFAULT_FILE_PATH;
        }
    }
}
