package it.polito.tdp.metroparis;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;
import it.polito.tdp.metroparis.model.Fermata;
import it.polito.tdp.metroparis.model.model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
public class Controller {
	private model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Fermata> boxArrivo;

    @FXML
    private ComboBox<Fermata> boxPartenza;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCalcola(ActionEvent event) {
    	Fermata partenza= boxPartenza.getValue();
    	Fermata arrivo= boxArrivo.getValue();
    	
    	if(partenza!=null && arrivo!=null && !partenza.equals(arrivo)) {
    		List<Fermata> percorso=model.percorso(partenza, arrivo);
    		txtResult.setText("percorso tra "+partenza.getNome()+"e " +arrivo.getNome());
    		for(Fermata f: percorso)
    			txtResult.appendText(f.getNome()+"\n");
    	}else {
    		txtResult.setText("devi selezionare due stazioni diverse tra loro\n");
    	}
    }

    @FXML
    void handleCrea(ActionEvent event) {
    	this.model.creaGrafo();
    	
    	if(this.model.isGradoLoaded()) {
    	
    	}
    }

    @FXML
    void initialize() {
        assert boxArrivo != null : "fx:id=\"boxArrivo\" was not injected: check your FXML file 'Metro.fxml'.";
        assert boxPartenza != null : "fx:id=\"boxPartenza\" was not injected: check your FXML file 'Metro.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Metro.fxml'.";

    }
    public void setmodel(model model) {
    	this.model=model;
    	List<Fermata> fermate=model.getAllFermate();
    	this.boxArrivo.getItems().setAll(fermate);
    	this.boxPartenza.getItems().setAll(fermate);
    			
    }
}
