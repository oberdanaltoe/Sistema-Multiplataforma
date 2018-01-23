package scrcm.com.scrcm.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import scrcm.com.scrcm.R;
import scrcm.com.scrcm.model.Cidade;
import scrcm.com.scrcm.model.UF;
import scrcm.com.scrcm.service.CidadeService;
import scrcm.com.scrcm.service.UFService;

public class CidadeActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private EditText nomeCidade;
    private Spinner spnCidade;

    UF ufTeste;

   // ArrayList<UF> listaUfs = UFService.listar();
   private ArrayList<UF> listaUfs = new ArrayList<>();

    public CidadeActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidade);


        nomeCidade = (EditText) findViewById(R.id.edtNomeCidade);
        spnCidade = (Spinner) findViewById(R.id.spnUfs);

        nomeCidade.getText().toString();
        listarUFs(null);

        spnCidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ufTeste = (UF) parent.getSelectedItem();
                //Toast.makeText(CidadeActivity.this, "UF: "+ufTeste.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void inserirCidades(View view) {

        new AsyncInserir().execute();
    }

    class AsyncInserir extends AsyncTask<Void, Void, Boolean> {
        // pegar o valor do camp
        String nCidade = nomeCidade.getText().toString();

        //Inserindo o Cliente 'Cliente Android'

        @Override
        protected Boolean doInBackground(Void... params) {

            Cidade cidade = new Cidade();
            cidade.setNomeCidade(nCidade);
            cidade.setuF(ufTeste);


            try {
                return CidadeService.inserir(cidade);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean resultado) {
            if (resultado) {
                Toast.makeText(CidadeActivity.this, "Cidade Inserida com Sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CidadeActivity.this, "Problema na Inserção da Cidade", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //[Início] Listar UFS ---------------------------------------------------------------------
    public void listarUFs(View view) {
        new CidadeActivity.AsyncListar().execute();
    }

    class AsyncListar extends AsyncTask<Void, Void, ArrayList<UF>> {

        @Override
        protected ArrayList<UF> doInBackground(Void... params) {

            try {
                return UFService.listar();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
            //return listaUfs;
        }

        @Override
        protected void onPostExecute(ArrayList<UF> result) {
            listaUfs = result;
            ArrayAdapter<UF> ufArrayAdapter = new ArrayAdapter<UF>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, listaUfs);
            spnCidade.setAdapter(ufArrayAdapter);
            if (result != null) {

                Toast.makeText(CidadeActivity.this, "UFs Listadas com Sucesso: \n" + result.toString(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(CidadeActivity.this, "Problema na Listagem das UFs", Toast.LENGTH_SHORT).show();
            }
        }



    }

}
