package com.egis.parser;

import org.json.JSONArray;
import org.jsoup.nodes.Document;

/**
 * Created by bright on 2017/05/28.
 */
public class Main {

    private static String url = "https://github.com/egis/handbook/blob/master/Tech-Stack.md"; //todo use external config

    public static void main(String args[]){
        Parser parser = new Parser();
        Document document = parser.getDocumentFromURL(url);
        JSONArray jsonArray = parser.traverseDocument(document);
        parser.printToStdout(jsonArray);
    }
}
