package com.example.progettomgc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Controller {
    private static String NAMESPACE = "http://unicam.it/negozio_di_elettronica#";
    @FXML
    private TextField maintxt;
    @FXML
    private TextField clienttxt;
    @FXML
    private TextField STtxt;
    @FXML
    private MenuButton typemenu;
    @FXML
    private MenuButton STmenu;
    @FXML
    private MenuItem typecomputer;
    @FXML
    private MenuItem typeaccessori;
    @FXML
    private MenuItem typetelefono;
    @FXML
    private MenuItem typetelevisore;
    @FXML
    private MenuItem STproc;
    @FXML
    private MenuItem STmem;
    @FXML
    private MenuItem STcon;
    @FXML
    private MenuItem STris;
    @FXML
    private MenuItem STSO;
    @FXML
    private MenuItem STprice;
    @FXML
    private MenuItem STmarca;
    @FXML
    private MenuItem STdisp;
    @FXML
    private ListView<String> mainList;
    @FXML
    private ListView<String> STlist;
    @FXML
    private Label clientLabel;
    private String type;
    private String STtype;

    private OntologyQuery ask = new OntologyQuery("negozio_elettronica.rdf", "http://unicam.it/negozio_di_elettronica#");
    private Modifier mod = new Modifier("negozio_elettronica.rdf", "http://unicam.it/negozio_di_elettronica#");
    //DB locale dove vengono salvati gli elementi
    private Elements elements = new Elements(ask);
    private ObservableList<String> listToString(ArrayList<Element> l){
        ArrayList<String> nameList = new ArrayList<>();
        for(int i = 0; i < l.size(); i++){
            nameList.add(l.get(i).getName());
        }
        ObservableList<String> observableList = FXCollections.observableArrayList(nameList);
        return observableList;
    }
    public void onSearchBtn(){
        ArrayList<Element> elList = elements.search(type);
        //trasformo la lista di elementi in una lista di string observable per poterla visualizzare
        ObservableList<String> nameList = listToString(elList);
        mainList.setItems(nameList);
    }
    public void onAddBtn() throws FileNotFoundException {
        String name = maintxt.getText().replace(" ", "_");
        Element e = new Element(name, NAMESPACE);
        e.setType(type);
        //add element to the local database
        elements.add(e);
        //add element to the ontology
        mod.addElement(e);
        //richiamo la ricerca per dare un feedback dell aggiunzione di un nuovo elemento
        onSearchBtn();
    }
    public void onDelBtn() throws FileNotFoundException {
        //rimuovo l'elemento anche dalla lista locale
        Element del = elements.searchByName(mainList.getSelectionModel().getSelectedItem());
        elements.remove(del);
        mod.deleteElement(del);
        //richiamo la ricerca per dare un feedback dell' eliminazione di un elemento
        onSearchBtn();

    }

    public void onSelST(){
        Element el = elements.searchByName(mainList.getSelectionModel().getSelectedItem());
        ArrayList<String> propertiesList = el.getObjectProperties();
        //inserisco la lista trovata nella view
        ObservableList<String> observableList = FXCollections.observableArrayList(propertiesList);
        STlist.setItems(observableList);
    }

    public void onSearchClient(){
        ArrayList<Element> elList = elements.search("Cliente");
        ObservableList<String> l = listToString(elList);
        mainList.setItems(l);
    }
    public void onAddClient() throws FileNotFoundException {
        String name = clienttxt.getText().replace(" ", "_");
        Element e = new Element(name, NAMESPACE);
        e.setType("Cliente");
        mod.addElement(e);

    }
    public void onAddST() throws FileNotFoundException {
        Element source = elements.searchByName(mainList.getSelectionModel().getSelectedItem());
        mod.addObjectProperty(source, STtxt.getText().replace(" ", "_"), STtype);
        source.setObjectProperties(ask.getProperties(source));
    }
    public void onspecsearch(){
        ArrayList<Element> elList = ask.getElementsByProperty(STtype, STtxt.getText().replace(" ", "_"));
        ObservableList l = listToString(elList);
        mainList.setItems(l);
    }
    public void onClientSel(){
        clientLabel.setText(mainList.getSelectionModel().getSelectedItem());
    }
    public void onAddPurchase() throws FileNotFoundException {
        Element client = elements.searchByName(clientLabel.getText().replace(" ", "_"));
        mod.addObjectProperty(client, mainList.getSelectionModel().getSelectedItem(), "haAcquistato");
        //aggiungo le modifiche anche al DB locale
        client.setObjectProperties(ask.getProperties(client));
    }
    public void selComputer(){
        type = ProductType.Computer.name();
        typemenu.setText(typecomputer.getText());
    }
    public void selTelefono(){
        type = ProductType.Telefono.name();
        typemenu.setText(typetelefono.getText());
    }
    public void selTelevisore(){
        type = ProductType.Televisore.name();
        typemenu.setText(typetelevisore.getText());
    }
    public void selAccessori(){
        type = ProductType.Accessori.name();
        typemenu.setText(typeaccessori.getText());
    }
    public void selMem(){
        STtype = Properties.haCapacitàDiMemoria.name();
        STmenu.setText(STmem.getText());
    }
    public void selCon(){
        STtype = Properties.haConnettività.name();
        STmenu.setText(STcon.getText());
    }
    public void selRis(){
        STtype = Properties.haRisoluzione.name();
        STmenu.setText(STris.getText());
    }
    public void selProc(){
        STtype = Properties.haProcessore.name();
        STmenu.setText(STproc.getText());
    }
    public void selSO(){
        STtype = Properties.haSistemaOperativo.name();
        STmenu.setText(STSO.getText());
    }
    public void selBrand(){
        STtype = Properties.haMarca.name();
        STmenu.setText(STmarca.getText());
    }
    public void selDisp(){
        STtype = Properties.haDisponibilità.name();
        STmenu.setText(STdisp.getText());
    }
    public void selPrice(){
        STtype = Properties.haPrezzo.name();
        STmenu.setText(STprice.getText());
    }
}