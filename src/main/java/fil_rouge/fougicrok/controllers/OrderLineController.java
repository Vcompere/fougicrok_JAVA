package fil_rouge.fougicrok.controllers;

import fil_rouge.fougicrok.DAL.Products;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderLineController implements Initializable
{
    public ImageView img;
    public Label label_name;
    public Label label_qty;
    public Label label_uPrice;
    public Label label_tprice;
    public Label label_descri;

    int qty;
    double uP, tP;
    Products prod;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }

    public void go1(Image img1, String name, String descri, int qty, double uP, double tP)
    {
        img.setImage(img1);
        label_name.setText(name);
        label_descri.setText(descri);
        label_qty.setText(String.valueOf(qty));
        label_uPrice.setText(String.valueOf(uP));
        label_tprice.setText(String.valueOf(tP));
    }

    public void go(int qty, double uP, double tP, Products prod)
    {
        this.qty=qty;
        this.uP=uP;
        this.tP=tP;
        this.prod=prod;

        Image img1 = new Image("img/products/"+prod.getImgFull());
        img.setImage(img1);

        label_qty.setText("x"+String.valueOf(qty));
        label_uPrice.setText("("+String.valueOf(uP)+"€)");
        label_tprice.setText(String.valueOf(tP)+"€");
        label_name.setText(prod.getLabel());
        label_descri.setText(prod.getDesc());
    }

    public void test(MouseEvent mouseEvent) {
        System.out.println(prod.getLabel());
    }
}
