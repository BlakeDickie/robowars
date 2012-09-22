/*
 * CardTypeManager.java
 *
 * Created on July 4, 2004, 11:30 AM
 */

package robowars.common;

import java.util.*;
import java.sql.*;

import robowars.editor.typepanels.CardDetailsEditor;

/**
 *
 * @author  Blake Dickie
 */
public class CardTypeManager {
    private static CardTypeManager instance = null;
    
    public SortedSet types;
    
    private String[] CARD_TYPES = {"Planet"};
    
    
    /** Creates a new instance of CardTypeManager */
    private CardTypeManager() {
        types = new TreeSet();
        try {
            DatabaseConnector conn = DatabaseConnector.getInstance();
            ResultSet rs = conn.doQuery("Select type_name From CardTypes");
            while(rs.next())
                types.add(rs.getString("type_name"));
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    
    
    public static synchronized CardTypeManager getInstance()
    {
        if (instance == null)
            instance = new CardTypeManager();
        return instance;
    }
    
    public Set getTypes()
    {
        return new TreeSet(types);
    }
    
    public CardDetailsEditor getEditorPanelFor(String type)
    {
        try {
            Class panelClass = getClass().forName("robowars.editor.typepanels." + type + "EditPanel");
            return (CardDetailsEditor)panelClass.newInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
}
