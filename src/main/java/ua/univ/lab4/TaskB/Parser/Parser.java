package ua.univ.lab4.TaskB.Parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.univ.lab4.TaskB.Parser.Tokens.IToken;
import ua.univ.lab4.TaskB.Parser.Tokens.Listing;
import ua.univ.lab4.TaskB.Parser.Tokens.Word;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static Logger infoLogger  = LogManager.getLogger("InfoLogger");
    private static Logger errorLogger = LogManager.getLogger("ErrorLogger");

    private final File file;
    private List<IToken> tokens = new ArrayList<>();
    private boolean isParsed = false;

    public Parser(File file) {
        this.file = file;
        infoLogger.info("New instance of parser was created! File - " + file.getPath());
    }

    public Parser(String pathToFile) {
        this.file = new File(pathToFile);
        infoLogger.info("New instance of parser was created! File - " + file.getPath());
    }

    public List<IToken> parse() {
        if(isParsed) {
            infoLogger.info(String.format("Attempt to parse same file multiple times (%s)", file.getPath()));
            return tokens;
        }

        infoLogger.info("Parsing " + file.getPath());

        try {
            Scanner sc = new Scanner(file);

            while(sc.hasNext()) {
                String str = sc.next().trim();
                switch (str) {
                    case "<code>":
                        str = "";
                        while(true) {
                            String nxt = sc.next().trim();
                            if(nxt.equals("</code>")) break;
                            str += nxt + ' ';
                        }
                        tokens.add(new Listing(str));

                        infoLogger.info(String.format("Token - listing: \"%s\"", str));
                        break;
                    default:
                        tokens.add(new Word(str));
                        infoLogger.info(String.format("Token - word: \"%s\"", str));
                }
            }

        } catch (Exception ex) {
            errorLogger.error(ex);
            return null;
        }

        infoLogger.info("Parsing successfully finished!");

        isParsed = true;
        return tokens;
    }

}
