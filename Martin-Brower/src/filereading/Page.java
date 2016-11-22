/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filereading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author talk2
 */
public class Page
{

    private String _title;
    private Map<String, String> _summaryRow;
    private Map<String, Integer> _headerRow;
    private Map<String, ArrayList<String>> _contents;

    public Page()
    {
        _summaryRow = new HashMap<>();
        _headerRow = new HashMap<>();
        _contents = new HashMap<>();
    }

    /**
     * @return the _contents
     */
    public Map<String, ArrayList<String>> getContents()
    {
        return _contents;
    }

    /**
     * @param _contents the _contents to set
     */
    public void setContents(Map<String, ArrayList<String>> _contents)
    {
        this._contents = _contents;
    }

    /**
     * @return the _headerRow
     */
    public Map<String, Integer> getHeaderRow()
    {
        return _headerRow;
    }

    /**
     * @param _headerRow the _headerRow to set
     */
    public void setHeaderRow(Map<String, Integer> _headerRow)
    {
        this._headerRow = _headerRow;
    }

    /**
     * @return the _title
     */
    public String getTitle()
    {
        return _title;
    }

    /**
     * @param _title the _title to set
     */
    public void setTitle(String _title)
    {
        this._title = _title;
    }

    /**
     * @return the _summaryRow
     */
    public Map<String, String> getSummaryRow()
    {
        return _summaryRow;
    }   

    /**
     * @param _summaryRow the _summaryRow to set
     */
    public void setSummaryRow(Map<String, String> _summaryRow)
    {
        this._summaryRow = _summaryRow;
    }
    
}
