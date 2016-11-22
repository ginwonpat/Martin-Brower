/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filereading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

/**
 *
 * @author talk2
 */
public class CsvReader
{

    // Use StringTokenizer
    private final String _fileName;
    private ArrayList<Page> _pages;
    private final String STOP, CASES, DESCRIPTION, WRIN, TRAILER, ROUTE;

    public CsvReader(String fileName)
    {
        // \b(STOP|CASES|DESCRIPTION|WRIN|TRAILER|ROUTE)\b\s*\: - Summary header
        // \b(STOP|CASES|DESCRIPTION|WRIN|TRAILER|ROUTE)\b\s*\:\s*\,*\b[0-9]*\b - Summary with value
        _fileName = fileName;
        _pages = new ArrayList<>();
        STOP = "STOP";
        CASES = "CASES";
        DESCRIPTION = "DESCRIPTION";
        WRIN = "WRIN";
        TRAILER = "TRAILER";
        ROUTE = "ROUTE";
    }    

    // create a map of string and string[]
    // the string holds the column header,
    // the string[] hold the column values
    // read line
    // identify the line as a header row
    // create a hashmap of each column header and position index
    public void process() throws FileNotFoundException
    {
        Map<String, String> summaryRow = new HashMap<>();
        Map<String, Integer> headerRow = new HashMap<>();
        Map<String, ArrayList<String>> contents = new HashMap<>();

        File file = new File(_fileName);
        Scanner scanner = new Scanner(file);
        String currentPageTitle = "";
        Page currentPage = new Page();
        
        String previousLine = "";
        String value = "";
        while (scanner.hasNext())
        {
            
            String currentLine = scanner.nextLine();
            
            if (isPageTitle(currentLine))
            {
                currentPage.setTitle(currentPageTitle);
                currentPage.setContents(contents);
                currentPage.setHeaderRow(headerRow);
                currentPage.setSummaryRow(summaryRow);
                _pages.add(currentPage);
                currentPage = null;
                summaryRow.clear();
                headerRow.clear();
                contents.clear();
            }

            if (currentPage == null)
            {
                currentPage = new Page();
            }

            if (isSummaryRow(currentLine))
            {
                
                summaryRow = getSummaryData(currentLine);
                //System.out.println("Inside summary row");
            }

            if (isHeaderRow(currentLine))
            {
                Map<String, Integer> temp = getHeaderData(currentLine);
                for(String key: temp.keySet())
                {
                    headerRow.put(key, temp.get(key));
                }
            }
            
            if(isContentRow(currentLine))
            {
                
                
                for(String header : headerRow.keySet())
                {
                    
                }
                //value = getContentData(currentLine,headerRow,);
                //contents.get(STOP).add(value);
                //System.out.println("Inside Content Row");
            }
            previousLine = currentLine;
        }
        
    }

    public boolean isContentRow(String row) //the actual data
    {
        String regex = "\\b\\d+\\,+\\d+\\,+\\w+\\s\\w+\\D+\\d+\\,+\\d+\\w\\b";
        return isNeeded(regex,row);
    }
    
    public boolean isHeaderRow(String row)  //all the fields with data
    {
        String regex = "\\b(STOP|CASES|DESCRIPTION|WRIN|TRAILER(\\s(POSITION|POS))?|ROUTE)\\b\\s*\\,+";
        return isNeeded(regex, row);
    }

    public boolean isSummaryRow(String row) //stop route and trailer
    {
        String regex = "\\b(STOP|CASES|DESCRIPTION|WRIN|TRAILER(\\s(POSITION|POS))?|ROUTE)\\b\\s*\\:";
        return isNeeded(regex, row);
    }

    public boolean isPageTitle(String row)
    {
        String regex = "\\bPage\\s*\\d+\\s*of\\s*\\,*\\d+\\b";
        return isNeeded(regex, row);
    }

    private boolean isNeeded(String regex, String row)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(row);
        return matcher.find();
        
    }

    public Map<String, String> getSummaryData(String row)
    {
        Map<String, String> summaryData = new HashMap<>();
        String regex = "\\b(STOP|CASES|DESCRIPTION|WRIN|TRAILER(\\s(POSITION|POS))?|ROUTE)\\b\\s*\\:\\s*\\,*\\b[0-9a-zA-Z]*\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(row);

        while (matcher.find())
        {
            String match = matcher.group();
            int columnIndex = match.indexOf(":");
            String header = match.substring(0, columnIndex).trim();
            int lastCommaIndex = match.lastIndexOf(",");
            String value = match.substring(lastCommaIndex + 1, match.length()).trim();
            summaryData.put(header, value);
        }
        return summaryData;
    }
    
    public Map<String, Integer> getHeaderData(String currentLine)
    {
        Map<String, Integer> headers = new HashMap<>();
        int index = currentLine.indexOf(STOP);
        if(index >= 0) {
            headers.put(STOP, index);
        }       

        index = currentLine.indexOf(CASES);
        if(index >= 0) {
            headers.put(CASES, index);
        }
        
        index = currentLine.indexOf(DESCRIPTION);
        if(index >= 0) {
            headers.put(DESCRIPTION, index);
        }
        
        index = currentLine.indexOf(WRIN);
        if(index >= 0) {
            headers.put(WRIN, index);
        }
        
        index = currentLine.indexOf(TRAILER);
        if(index >= 0) {
            headers.put(TRAILER, index);
        }
        
        index = currentLine.indexOf(ROUTE);
        if(index >= 0) {
            headers.put(ROUTE, index);
        }
        
        return headers;
    }
    
    public String getContentData(String currentLine,int header,int secondHeader)
    {
        
        String string = "";
        int first = header;
        int second = secondHeader;
        //int indexDesc = headers.get(DESCRIPTION);
        //int indexTrailer = headers.get(TRAILER);
        //int indexWRIN = headers.get(WRIN);
        
        System.out.println(currentLine.substring(first,second).replace(",", "").trim());
        return string;
    }

}
