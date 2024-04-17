package com.neb.nebotools.controller;

import com.neb.nebotools.controller.enumeration.State;
import com.neb.nebotools.dao.Dao;
import com.neb.nebotools.dao.InvoiceDao;
import com.neb.nebotools.model.AbstractEntity;
import exception.EntityNotFoundException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.kordamp.ikonli.boxicons.BoxiconsRegular;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public abstract class ControllerListAbstract<T> extends ControllerPrincipalAbstract<T> {
    @FXML
    protected Button btnAdd;

    protected Dao<T> dao;
    private int indexPaginate = 0;

    protected List<T> objects;
    @FXML
    protected TextField search;
    @FXML
    private HBox pagination;

    @FXML
    private Label pagination_display;

    @FXML
    private Button pagination_next;

    @FXML
    private Button pagination_prev;

    private VBox page;

    private ApplicationController principal;

    private ControllerAbstract<T> entityController;

    @FXML
    protected Button searchBtn;

    CheckBox checkBox;

    private int itemPerPage = 8;

    @FXML
    protected TableView<T> table;

    protected abstract void setDao() throws SQLException;

    public Button getAdd() {
        return btnAdd;
    }


    protected abstract void initTable();

    public void setPage(VBox page) {
        this.page = page;
    }

    public void setController(ApplicationController principal, ControllerAbstract<T> entityController) {
        this.principal = principal;
        this.entityController = entityController;
    }

    public void replaceObj(T obj) {
        for (T e : table.getItems())
            if (((AbstractEntity) e).equals((AbstractEntity) obj)) {
                ((AbstractEntity) e).setEntity((AbstractEntity) obj);
                break;
            }
        table.refresh();
    }

    public void addObj(T obj) {
        ((AbstractEntity) obj).setRow(table.getItems().size() + 1);
        table.getItems().add(obj);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        try {
            setDao();
            initTable();
            addColumn();
            setComponent();
            initComponent();
            objects = FXCollections.observableArrayList(dao.getAll(1000));
            search.setOnKeyReleased(a -> {
                searchInTable();
            });

            setPagination();
            for (T t : objects) {
                ((AbstractEntity) t).getCheckBox().selectedProperty().addListener(b -> {
                    verify();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void searchInTable() {
        objects = FXCollections.observableArrayList(search());
        for (T t : objects) {
            checkBox.setIndeterminate(false);
            checkBox.setSelected(false);
            ((AbstractEntity) t).getCheckBox().selectedProperty().addListener(b -> {
                verify();
            });
        }

        setPagination();
    }

    private void setComponent() {
        ComboBox<Integer> comboSetPagination = new ComboBox<Integer>();
        ComboBox<String> comboPrint = new ComboBox<String>();
        comboPrint.getItems().addAll("Enregistrer PDF", "IMPRIMER");
        comboPrint.getStyleClass().add("form-select");
        comboSetPagination.getStyleClass().add("form-select");
        for (int i = 5; i<=15; i++)
            comboSetPagination.getItems().add(i);
        comboSetPagination.getItems().addAll(30,50,75,100);
        comboSetPagination.getSelectionModel().select(3);
        comboSetPagination.setOnAction(a->{
                itemPerPage = comboSetPagination.getSelectionModel().getSelectedItem();
                setPagination();
        });
        for(Integer item: comboSetPagination.getItems()){

        }
        ((HBox)btnAdd.getParent()).getChildren().add(2,comboPrint);
        ((HBox)btnAdd.getParent()).getChildren().add(2,comboSetPagination);
    }

    private void setPagination() {
        HBox parent = (HBox) pagination_next.getParent();
        parent.getChildren().clear();
        parent.getChildren().addAll(pagination_prev, pagination_next);
        for (int i = 0; i <= objects.size() / itemPerPage; i++) {
            Button btn = new Button();
            int finalI = i;
            btn.setOnAction(a -> paginate(finalI));
            btn.setText((i + 1) + "");
            btn.getStyleClass().addAll("page-item");
            if(i==1)
                btn.getStyleClass().addAll("page-active");
            parent.getChildren().add(parent.getChildren().size() - 1, btn);
        }
        indexPaginate = 0;
        pagination_next.setOnAction(a -> {
            if ((indexPaginate + 1) * itemPerPage < objects.size()) {
                indexPaginate++;
                paginate();
            }
        });

        pagination_prev.setOnAction(a -> {
            if (indexPaginate > 0) {
                indexPaginate--;
                paginate();
            }
        });
paginate();
    }

    private void paginate() {
        table.getItems().clear();
        if (!objects.isEmpty() && objects.size() > indexPaginate * itemPerPage) {
            pagination_display.setText("Display " + (indexPaginate * itemPerPage + 1) + " to " + Math.min((indexPaginate + 1) * itemPerPage, objects.size()) + " of " + objects.size() + " entries");
            table.setItems(FXCollections.observableArrayList(objects.subList(indexPaginate * itemPerPage, Math.min((indexPaginate + 1) * itemPerPage, objects.size()))));

            HBox parent = (HBox) pagination_next.getParent();
            for (Node node: parent.getChildren())
            node.getStyleClass().remove("page-active");

                parent.getChildren().get(indexPaginate + 1).getStyleClass().add("page-active");
            }

    }

    private void paginate(int page) {
        indexPaginate = page;
        HBox parent = (HBox) pagination_next.getParent();
        for (Node btn : parent.getChildren())
            btn.getStyleClass().remove("page-active");
        paginate();
    }

    //	public abstract List<MenuItem> getCustomTableButton();

    private void addColumn() {
        TableColumn<T, Integer> index = new TableColumn<T, Integer>();
        TableColumn<T, CheckBox> box = new TableColumn<T, CheckBox>();
        TableColumn<T, String> action = new TableColumn<T, String>();

        index.setText("#");
        action.setText("ACTION");

        index.setStyle("-fx-alignment: center_right");
        box.setStyle("-fx-alignment: center");

        checkBox = new CheckBox();
        checkBox.getStyleClass().add("form-check-input");
        box.setGraphic(checkBox);

        checkBox.selectedProperty().addListener((observable, oldVal, newVal) -> {
            for (T t : objects) {
                ((AbstractEntity) t).getCheckBox().setSelected(newVal);
                ((AbstractEntity) t).getCheckBox().selectedProperty().addListener(a -> {
                    verify();
                });
            }
            table.refresh();
        });

        index.setCellValueFactory(new PropertyValueFactory<T, Integer>("row"));
        box.setCellValueFactory(new PropertyValueFactory<T, CheckBox>("checkBox"));
        action.setCellValueFactory(new PropertyValueFactory<T, String>("id"));

        action.setCellFactory(new Callback<TableColumn<T, String>, TableCell<T, String>>() {

            @Override
            public TableCell<T, String> call(TableColumn<T, String> arg0) {
                return new TableCell<T, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            MenuButton menuBtn = new MenuButton();

                            menuBtn.getItems().addAll(new MenuItem("Détail"), new MenuItem("Modfier"));
                            HBox hb = new HBox();
                            menuBtn.getStyleClass().add("form-select");
                            menuBtn.setMaxWidth(Double.POSITIVE_INFINITY);
                            HBox.setHgrow(menuBtn, Priority.ALWAYS);
                            menuBtn.setPopupSide(Side.TOP);
                            FontIcon icon = new FontIcon();
                            icon.setIconCode(BoxiconsRegular.DOTS_HORIZONTAL_ROUNDED);
                            icon.setIconSize(25);
                            menuBtn.setGraphic(icon);

                            hb.getChildren().add(menuBtn);
                            hb.setAlignment(Pos.CENTER);
                            hb.setSpacing(10);
                            menuBtn.getItems().get(0).setOnAction(e -> {
                                try {

                                    entityController.setEntity(dao.find(item));
                                    entityController.disableField(true);
                                    entityController.setState(State.DETAIL);
                                    principal.switchPane(page, "customer.add", State.DETAIL);

                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            });
                            menuBtn.getItems().get(1).setOnAction(e -> {
                                try {
                                    entityController.setEntity(dao.find(item));
                                    entityController.disableField(false);
                                    entityController.setState(State.MODIFY);
                                    principal.switchPane(page, "customer.add", State.MODIFY);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            });
                            if(dao instanceof InvoiceDao)
                                menuBtn.getItems().remove(1);
                            setGraphic(hb);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        table.getColumns().add(0, index);
        table.getColumns().add(1, box);
        table.getColumns().add(action);

    }

    private void verify() {
        boolean isSelected = true;
        boolean isNotSelected = true;
        for (T t : objects)
            if (((AbstractEntity) t).getCheckBox().isSelected()) {
                isNotSelected = false;
                break;
            }

        for (T t : objects)
            if (!((AbstractEntity) t).getCheckBox().isSelected()) {
                isSelected = false;
                break;
            }

        if (!isSelected && !isNotSelected) {
            checkBox.setIndeterminate(true);
        }else if(isSelected && !isNotSelected) {
            checkBox.setIndeterminate(false);
            checkBox.setSelected(true);
        }else if(!isSelected && isNotSelected) {
            checkBox.setIndeterminate(false);
            checkBox.setSelected(false);
        }
    }


    protected Node createPage(int pageIndex) throws SQLException, EntityNotFoundException {
        int fromIndex = pageIndex * 15;
        int toIndex = Math.min(fromIndex + 15, dao.getSize());
        table.setItems(FXCollections.observableArrayList(dao.getAll(0).subList(fromIndex, toIndex)));
        return new VBox(table);
    }

    public void start() {

    }

    public void refresh() {
        table.refresh();
    }

    public abstract List<T> search();

    //public
}
