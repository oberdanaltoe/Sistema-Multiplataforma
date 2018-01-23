package scrcm.com.scrcm.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import scrcm.com.scrcm.R;
import scrcm.com.scrcm.model.Mensagens;
import scrcm.com.scrcm.service.MensagemService;


public class MensagensActivity extends AppCompatActivity {

    private Button btnVoltar;
    private ListView listViewMensagens;
    private ArrayList<Mensagens> mensagensArrayList = new ArrayList<Mensagens>();
    private static Mensagens mensagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagens);

        btnVoltar = (Button) findViewById(R.id.btnVoltarMenuMensagens);
        listViewMensagens = (ListView) findViewById(R.id.listViewMensagens);

        listarMensagens();
        listViewMensagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codigoPosicao = position;
                listViewMensagens.getPositionForView(view);
                Object objeto = listViewMensagens.getItemAtPosition(codigoPosicao);
                mensagens = (Mensagens) objeto;
                detalhesMensagem(mensagens);
            }
        });
    }

    public void listarMensagens() {
        new AsyncListarMensagens().execute();
    }

    class AsyncListarMensagens extends AsyncTask<Void, Void, ArrayList<Mensagens>> {
        @Override
        protected ArrayList<Mensagens> doInBackground(Void... params) {
            try {
                return MensagemService.listar();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Mensagens> result) {
            mensagensArrayList = result;
            if (result != null) {
                ArrayAdapter<Mensagens> mensagensArrayAdapter = new ArrayAdapter<Mensagens>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, mensagensArrayList);
                listViewMensagens.setAdapter(mensagensArrayAdapter);
            } else {
                Toast.makeText(MensagensActivity.this, "Problema ao carregar Mensagens!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Voltar(View view) {
        startActivity(new Intent(MensagensActivity.this, MainActivity.class));
    }

    //criação de alerte para exibição de detalhes da mensagem
    //cria um alert que dentro dele mostrara os dados criados abaixo
    public void detalhesMensagem(Mensagens me) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MensagensActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.detalhes_mensagem, null);

        Button btnVoltar = (Button) mView.findViewById(R.id.btnVoltarM);

        final TextView edtTitulo = (TextView) mView.findViewById(R.id.edtTituloMensagem);
        final TextView edtMensagem = (TextView) mView.findViewById(R.id.edtMensagem);
        if (me != null) {
            edtTitulo.setText(me.getTitulo());
            edtMensagem.setText(me.getMensagem());
        }
        // cria o builder e exibe ele
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        // criação de onclick, para botoes ou list view
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

}
