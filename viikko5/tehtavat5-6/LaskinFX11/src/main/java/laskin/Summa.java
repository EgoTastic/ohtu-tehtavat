package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {

    private int edeltava;

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita(){
        this.edeltava = sovellus.tulos();
        sovellus.plus(Integer.parseInt(syotekentta.getText()));
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
        disabloiNollaus();
        undo.disableProperty().set(false);
        
    }

    @Override
    public void peru(){
        sovellus.setTulos(this.edeltava);
        syotekentta.setText("");
        tuloskentta.setText("" + this.edeltava);
        undo.disableProperty().set(true);
        disabloiNollaus();
    }


    private void disabloiNollaus(){
        if (sovellus.tulos() == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
    }

}


