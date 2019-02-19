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
            List<String> linesFromFile = Files.readAllLines(Paths.get(readPath()));
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
    private DecathlonResult processFileLine(String line, int lineNumber) {
        StringTokenizer st = new StringTokenizer(line, PropertiesUtil.getProperty("app.decathlon.file.token.separator"));
        DecathlonResult decathlonResult = null;
        if (st.countTokens() >= Constants.MIN_TOKENS_NUM) {
            decathlonResult = createDecathlonResult.mapToItem(st);
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
    private int verifyHeaders() {
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
    private String readPath() {
        if (isValidPath.test(PropertiesUtil.getProperty("app.decathlon.file.input.path"))) {
            logger.log(Level.INFO,String.format("Read file in provided path: %s", PropertiesUtil.getProperty("app.decathlon.file.input.path")));
            return PropertiesUtil.getProperty("app.decathlon.file.input.path");
        } else {
            File directory = new File(""); //Retrieve the root project path
            logger.log(Level.INFO,String.format("The Path in the property is not valid, using default " +
                    "file in project folder: %s", directory.getAbsolutePath() + Constants.DEFAULT_FILE_PATH));
            return directory.getAbsolutePath() + Constants.DEFAULT_FILE_PATH;
        }
    }
}
