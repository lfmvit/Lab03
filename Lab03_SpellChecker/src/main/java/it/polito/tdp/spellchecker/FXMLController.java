/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;


public class FXMLController {
	
	private Dictionary model;
	private List<String> inputTextList;
	boolean linear=false;
	
	
	  @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ComboBox<String> comboBox;

	    @FXML
	    private TextArea textArea;

	    @FXML
	    private Button checkButton;

	    @FXML
	    private Text performance;
	    
	    @FXML
	    private Text errorsFound;

	    @FXML
	    private TextArea wrongTextArea;

	    @FXML
	    private Button clearButton;

	    @FXML
	    void doClearText(ActionEvent event) {
	    	textArea.clear();
	    	wrongTextArea.clear();
	    	errorsFound.setText("Wrong words found:");
	    	performance.setText("Check completed in:");
	    }

	    @FXML
	    void doSpellCheck(ActionEvent event) {
	    	
	    	model.loadDictionary(comboBox.getValue());
	    	
	    	inputTextList= new ArrayList<String>();
	    	
	    	String cattura = textArea.getText();
	    	cattura.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
	    	String[] catture= cattura.split(" ");
	    	
	    	for(String s:catture)
	    		inputTextList.add(s);
	    	
	    	if(linear==true) {
	    		
	    		long start= System.nanoTime();
	    		ArrayList<RichWord> checkedlist= new ArrayList<RichWord>(model.spellCheckText(inputTextList));
	    		long end=System.nanoTime();
	    		long elapsed= (end-start)/(long)1000000;
	    		
	    		String visualizza="";
	    		int errori=0;
	    		
	    		for(RichWord rw:checkedlist)
	    			if(rw.isCheck()==false) {
	    			visualizza+= rw.toString();
	    			errori++;
	    			}
	    		
	    		wrongTextArea.setText(visualizza);
	    		errorsFound.setText("Wrong words found:"+errori);
	    		performance.setText("Check completed in "+elapsed+" ms.");
	    	}
	    	else {

	    		long start= System.nanoTime();
	    		ArrayList<RichWord> checkedlist= new ArrayList<RichWord>(model.dichotomicSpellCheckText(inputTextList));
	    		long end=System.nanoTime();
	    		long elapsed= (end-start)/(long)1000000;
	    		
	    		String visualizza="";
	    		int errori=0;
	    		
	    		for(RichWord rw:checkedlist)
	    			if(rw.isCheck()==false) {
	    			visualizza+= rw.toString();
	    			errori++;
	    			}
	    		
	    		wrongTextArea.setText(visualizza);
	    		errorsFound.setText("Wrong words found:"+errori);
	    		performance.setText("Check completed in "+elapsed+" ms.");
	    		
	    	}
	    	

	    }
	    @FXML
	    void doActivation(ActionEvent event) {
	    	if (comboBox.getValue() !=null) {
	    		
	    		textArea.setDisable(false);
				wrongTextArea.setDisable(false);
				checkButton.setDisable(false);
				clearButton.setDisable(false);
				wrongTextArea.clear();
				textArea.clear();
				
	    		
	    	}else {
	    		
	    		textArea.setDisable(true);
	    		wrongTextArea.setDisable(true);
				checkButton.setDisable(true);
				clearButton.setDisable(true);
				textArea.setText("Seleziona una lingua!");
	    		
	    	}

	    }

	    @FXML
	    void initialize() {
	        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert checkButton != null : "fx:id=\"checkButton\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert wrongTextArea != null : "fx:id=\"wrongTextArea\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert errorsFound != null : "fx:id=\"errorsFound\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert performance != null : "fx:id=\"performance\" was not injected: check your FXML file 'Scene.fxml'.";

	    }
	    
	    public void setModel(Dictionary model) {
	    	
	    	wrongTextArea.setDisable(true);
	    	wrongTextArea.setText("Selezionare una lingua");
	    	
	    	textArea.setDisable(true);
	    	comboBox.getItems().addAll("English","Italian");
	    	
	    	checkButton.setDisable(true);
	    	clearButton.setDisable(true);
	 	
	    	
	    	this.model = model;
	    }
	}
