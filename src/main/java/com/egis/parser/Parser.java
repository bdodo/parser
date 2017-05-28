package com.egis.parser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by bright on 2017/05/24.
 */
public class Parser {

    public static Document getDocumentFromURL(String url){
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void printToStdout(JSONArray array){
        System.out.println(array);
    }

    /**
     * This method traverses the document and */
    public static  JSONArray traverseDocument(Document document){
        Elements headers = getTableHeadings(document);
        int index = 0;
        JSONArray jsonArray = new JSONArray();

        Elements tables = document.getElementsByTag("table");
        tables.remove(tables.last());
        for (Element table:tables) {
            String key = table.select("th").first().text();
            Element tbody = table.select("tbody").first();
            Elements rows = tbody.getElementsByTag("tr");
            String objectName = headers.get(index).text();
            JSONArray jsonInnerArray = new JSONArray();
            for (Element row:  rows) {
                String value = row.select("td").first().text();
                JSONObject jsonObj = new JSONObject();
                jsonObj.put(key, value);
                jsonInnerArray.put(jsonObj);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(objectName, jsonInnerArray);
            jsonArray.put(jsonObject);
            index ++;
        }
        return jsonArray;
    }


    public static Elements getTableHeadings(Document document){
        Elements headers = document.getElementsByTag("h2");
        headers.remove(headers.first());
        headers.remove(headers.last());
        return headers;
    }
}
