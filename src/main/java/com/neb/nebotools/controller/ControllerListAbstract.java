package com.neb.nebotools.controller;

import com.neb.nebotools.dao.Dao;
import com.neb.nebotools.model.AbstractEntity;
import exception.EntityNotFoundException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class ControllerListAbstract<T> extends ControllerPrincipalAbstract<T> {
    @FXML
    protected Button btnAdd;

    protected Dao<T> dao;

    Pagination pagination_;

    private VBox page;

    private ApplicationController principal;

    private ControllerPrincipalAbstract<T> entityController;

    @FXML
    protected HBox pagination;

    @FXML
    protected Button searchBtn;


    @FXML
    protected TableView<T> table;

    @FXML
    protected Text titleTable;

    protected abstract void setDao();

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
        for(T e: table.getItems())
            if(((AbstractEntity)e).equals((AbstractEntity) obj)){
                ((AbstractEntity)e).setEntity((AbstractEntity) obj);

                break;
            }
        table.refresh();
    }

    public void addObj(T obj) {
        ((AbstractEntity) obj).setRow(table.getItems().size()+1);
        table.getItems().add(obj);
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        try {
            int a = 1/0;
            setDao();
            initTable();
            addColumn();
            table.setItems(FXCollections.observableArrayList(dao.getAll(15)));
            titleTable.setText(dao.getAll(15).size()+"");



            int rowCount= dao.getSize() / 15 + 1;
            pagination_ = new Pagination((dao.getSize() / 15 + 1), 0);
            pagination_.setPageFactory(arg01 -> {
                try {
                    return createPage(arg01);
                } catch (SQLException | EntityNotFoundException e1) {
                    e1.printStackTrace();
                }
                return null;
            });
        } catch (Exception e) {
            System.err.println("Gnerate error for no control list controller abstract");
            //e.printStackTrace();
        }
//        ((VBox)table.getParent()).getChildren().add(pagination_);
//        VBox.setVgrow(pagination_, Priority.ALWAYS);

    }

    //	public abstract List<MenuItem> getCustomTableButton();

    private void addColumn() {
        TableColumn<T, Integer> index = new TableColumn<T, Integer>();
        TableColumn<T, String> action = new TableColumn<T, String>();

        index.setText("#");
        action.setText("action");

        index.setCellValueFactory(new PropertyValueFactory<T, Integer>("row"));
        action.setCellValueFactory(new PropertyValueFactory<T, String>("id"));

        action.setCellFactory(new Callback<TableColumn<T, String>,TableCell<T, String>>(){

            @Override
            public TableCell<T, String> call(TableColumn<T, String> arg0) {
                return new TableCell<T,String>(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!=null) {
                            MenuButton menuBtn = new MenuButton();

                            menuBtn.getItems().addAll(new MenuItem("Détail"),new MenuItem("Modfier"));
                            HBox hb = new HBox();
                            menuBtn.setGraphic(new ImageView());
                            ((ImageView) menuBtn.getGraphic()).setImage(new Image(getClass().getResourceAsStream("/img/more.png")));
                            ((ImageView) menuBtn.getGraphic()).setFitWidth(20);
                            ((ImageView) menuBtn.getGraphic()).setFitHeight(20);
                            hb.getChildren().add(menuBtn);
                            hb.setAlignment(Pos.CENTER);
                            hb.setSpacing(10);
                            menuBtn.getItems().get(0).setOnAction(e->{
                                try {

//                                    moreController.setEntity(dao.find(item));
//                                    principal.switchPane(page, moreController, State.OVERVIEW, dao.find(item));

                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            });
                            menuBtn.getItems().get(1).setOnAction(e->{
                                try {
//                                    Parent root;
//                                    principal.switchPane(page, entityController, State.MODIFY, dao.find(item));
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            });
                            setGraphic(hb);
                        }else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        table.getColumns().add(0,index);
        table.getColumns().add(action);

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
}
