package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TipoEventoConsultarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editidTipoEvento;
    EditText editnombreEvento;
    Spinner spinnerTipoEvento;
    String[] items;
    ArrayList<TipoEvento> list;
    TipoEvento tipoEvento=new TipoEvento();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_consultar);
        helper = new ControlReserveLocal(this);

        list = helper.ListTipoEventos();
        items = new String[list.size()];
        for (int i=0;i<list.size();i++){
            tipoEvento = list.get(i);
            items[i] = String.valueOf(tipoEvento.getIdTipoEvento());
        }
        spinnerTipoEvento=(Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerTipoEvento.setAdapter(adapter);
        editnombreEvento = (EditText) findViewById(R.id.editnombreevento);

    }
    public void consultarTipoEvento (View v){

        helper.abrir();
        String tipoEvento_id=spinnerTipoEvento.getSelectedItem().toString();
        TipoEvento tipoEvento = helper.consultarTipoEvento(tipoEvento_id);
        helper.cerrar();
        if (tipoEvento == null) {
            Toast.makeText(this, "Tipo de evento no registrado", Toast.LENGTH_LONG).show();
            editnombreEvento.setText(" ");
        } else {

            editnombreEvento.setText(tipoEvento.getNomTipoEvento());
        }
    }
    public void limpiarTexto(View v){
        editnombreEvento.setText("");
        //editidTipoEvento.setText("");
    }
}
