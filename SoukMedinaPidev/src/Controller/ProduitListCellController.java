package Controller;

/*package View;


public class ProduitListCellController  extends ListCell<produits> implements Initializable {

    @FXML
    private TextField nom;
    private TextField prix;
    private TextField quantite;
    private TextField ref;
    @FXML
    private GridPane root;
    private produits model;
    @FXML
    private TextField colprix;
    @FXML
    private TextField colref;
    @FXML
    private TextField colquantite;
    @FXML
    private TextField colNom;

  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // initialize a newly created cell to unselected status
        updateSelected(false);
        // add a un-focused listener to each child-item that triggers commitEdit(...)
        getRoot().getChildrenUnmodifiable().forEach(c -> {
            c.focusedProperty().addListener((obj, prev, curr) -> {
                if (!curr) {
                    commitEdit(model);
                }
            });
        });
        // set ListCell graphic
        setGraphic(root);
    }

    public GridPane getRoot() {
        return root;
    }

    private static final Logger LOG = Logger.getLogger(ProduitListCellController.class.getName());

    public static ProduitListCellController newInstance() {
        FXMLLoader loader = new FXMLLoader(ProduitListCellController.class.getResource("ProduitListCell.fxml"));
        try {
            loader.load();
            return loader.getController();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    protected void updateItem(produits item, boolean empty) {
        super.updateItem(item, empty); // <-- Important
        // make empty cell items invisible
        getRoot().getChildrenUnmodifiable().forEach(c -> c.setVisible(!empty));
        // update valid cells with model data
        if (!empty && item != null && !item.equals(this.model)) {
            nom.textProperty().set(item.Nomm().toString());
            prix.textProperty().set(item.Prixx().toString());
            quantite.textProperty().set(item.Quantite().toString());
            ref.textProperty().set(item.Ref().toString());
        }
        // keep a reference to the model item in the ListCell
        this.model = item;
    }

    @Override
    public void commitEdit(produits newValue) {
        // if newValue isn't defined, use this.model
        newValue = (newValue == null) ? this.model : newValue;
        super.commitEdit(newValue); // <-- important
        // update the model with values from the text fields
        newValue.Nommm(nom.textProperty().get());
        newValue.Prixxx(prix.textProperty().get());
        newValue.phoneNumber(quantite.textProperty().get().toString());
                newValue.phoneNumber(ref.textProperty().get());

    }

    @Override
    public void updateSelected(boolean selected) {
        super.updateSelected(selected);
        // update UI hints based on selected state
        getRoot().getChildrenUnmodifiable().forEach(c -> {
            // setting mouse-transparent to false ensure that
            // the cell will get selected we click on a field in
            // a non-selected cell
            c.setMouseTransparent(!selected);
            // focus-traversable prevents users from "tabbing"
            // out of the currently selected cell
            c.setFocusTraversable(selected);
        });
        if (selected) {
            // start editing when the cell is selected
            startEdit();
        } else {
            if (model != null) {
                // commit edits if the cell becomes unselected
                // we're not keeping track of "dirty" state
                // so this will commit changes even to unmodified cells
                commitEdit(model);
            }
        }
    }
  
    
}
*/
