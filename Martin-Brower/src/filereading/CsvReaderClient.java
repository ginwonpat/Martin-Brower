/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filereading;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.*;

/**
 *
 * @author talk2
 */
public class CsvReaderClient
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        // TODO code application logic here
        CsvReader reader = new CsvReader("Bun Manifest 3.csv");
//        boolean isSummaryRow = reader.isSummaryRow(",,ROUTE:      ,,,,,501,,,,STOP:,,,60,,,STORE:,,,,10903,");
//        String text = isSummaryRow ? "Found" : "Not Found";
//        System.out.println(text);
//        boolean isHeaderRow = reader.isHeaderRow(",,,,,,,,,,,,WRIN,TRAILER POSITION,,,DISPATCH,,,,,,LAST 4 of oLPN");
//        String text = isHeaderRow ? "Found" : "Not Found";
//        System.out.println(text);
          reader.process();
//          Map<String, String> summaryData  = reader.getSummaryData(",,ROUTE :    ,,,,,589,,,,STOP:,,,30,,,WRIN:,,,,6076,");
//          for(String v : summaryData.values()) {
//              System.out.println(v);
//          }
          
//          Map<String, Integer> headerData = reader.getHeaderData(",,,,,,,,,,,,WRIN,TRAILER POSITION,,,DISPATCH,,,,,,LAST 4 of oLPN");
//          for(String k : headerData.keySet()) {
//              System.out.printf("<%s,%s>\n", k, headerData.get(k));
//          }      
    }
    
}
