package searchengine;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import searchengine.Queries;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;


public class Results {


 
    private static ArrayList<String> tweetsStore = new ArrayList<String>();
    private static ArrayList<Document> resultsStore = new ArrayList<Document>();


	public static void main(String[] args) throws IOException{

				
	}
	

	public static JSONObject queryResults(ArrayList<Document> results, ArrayList<String> tweetsresults) throws MalformedURLException, IOException{
		
		resultsStore.addAll(results);
		tweetsStore.addAll(tweetsresults);
		
		JSONObject jObject = new JSONObject();
	    JSONArray jArray = new JSONArray();

		try
		{
		    int i = 0;
		    for (Document result : resultsStore)
		    {
		         JSONObject resultJSON = new JSONObject();
		         
		         resultJSON.put("tweet", tweetsStore.get(i));
		         resultJSON.put("user", result.getField("user").stringValue());
		         resultJSON.put("name", result.getField("name").stringValue());
		         resultJSON.put("location", result.getField("location").stringValue());
		         resultJSON.put("age", result.getField("age").stringValue());
		         resultJSON.put("gender", result.getField("sex").stringValue());
		         resultJSON.put("urlimg", result.getField("urlimg").stringValue());	

		         jArray.put(resultJSON);
		         i++;
		    }
		    jObject.put("ResultList", jArray);
		} catch (JSONException jse) {
		    jse.getStackTrace();
		}
		resultsStore.clear();
		tweetsStore.clear();
		return jObject;
	}
}