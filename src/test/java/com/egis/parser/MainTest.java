package com.egis.parser;

import org.json.JSONArray;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by bright on 2017/05/25.
 */
public class MainTest {
    private Parser parserUnderTest;
    private static String url = "https://github.com/egis/handbook/blob/master/Tech-Stack.md"; //use external config

    @Before
    public void setUp() throws Exception{
        parserUnderTest = new Parser();
    }

    @Test
    public void testGetDocumentFromURL(){
        Document doc = parserUnderTest.getDocumentFromURL(url);
        assertTrue(doc instanceof Document);
    }

    @Test
    public void testGetTableHeadings(){
        Document doc = parserUnderTest.getDocumentFromURL(url);
        Elements headers = parserUnderTest.getTableHeadings(doc);
        Assert.assertEquals(3, headers.size());

        Elements allHeaders = doc.getElementsByTag("h2");
        Assert.assertNotEquals(allHeaders.size(), headers.size());
    }

    @Test
    public void testTraverseDocument(){
        Document doc = parserUnderTest.getDocumentFromURL(url);
        JSONArray jsonArray = parserUnderTest.traverseDocument(doc);
        Assert.assertEquals(3, jsonArray.length());
    }

}
