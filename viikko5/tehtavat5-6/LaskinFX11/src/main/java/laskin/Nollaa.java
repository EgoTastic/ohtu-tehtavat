package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {

    private int edeltava;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita(){
        this.edeltava = sovellus.tulos();
        this.sovellus.nollaa();
        syotekentta.setText("");
        tuloskentta.setText("");
        nollaa.disableProperty().set(true);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru(){
        sovellus.setTulos(this.edeltava);
        syotekentta.setText("");
        tuloskentta.setText("" + this.edeltava);
        undo.disableProperty().set(true);
        nollaa.disableProperty().set(false);
    }
}
