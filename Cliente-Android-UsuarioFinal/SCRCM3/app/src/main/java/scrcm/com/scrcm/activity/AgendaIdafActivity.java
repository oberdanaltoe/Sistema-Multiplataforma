package scrcm.com.scrcm.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import scrcm.com.scrcm.R;
import scrcm.com.scrcm.model.AgendamentoVisita;
import scrcm.com.scrcm.service.AgendamentoVisitaService;

public class AgendaIdafActivity extends AppCompatActivity {

    private ListView listViewAgendamento;
    private static ArrayList<AgendamentoVisita> listaAgendamento = new ArrayList<>();
    private static ArrayList<String> listaAgendamento1 = new ArrayList<>();
    private List<String> itensAgendamento;
    private String[] itens;
    private Button btnVoltarAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agdanda_idaf);

        btnVoltarAgenda = (Button) findViewById(R.id.btnVoltarAgenda);
        listViewAgendamento = (ListView) findViewById(R.id.listViewAgendamento);
        listarAgenda();

        btnVoltarAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AgendaIdafActivity.this, MainActivity.class));
            }
        });
    }

    public void listarAgenda() {
        listaAgendamento1.clear();
        listaAgendamento.clear();
        new AsyncListarAgenda().execute();
    }

    class AsyncListarAgenda extends AsyncTask<Void, Void, ArrayList<AgendamentoVisita>> {

        @Override
        protected ArrayList<AgendamentoVisita> doInBackground(Void... params) {
            try {
                return AgendamentoVisitaService.listar();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<AgendamentoVisita> result) {
            listaAgendamento = result;
            if (result != null) {
                listarAgendamentoString(listaAgendamento);
            } else {
                Toast.makeText(AgendaIdafActivity.this, "Problema ao carregar Agenda", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void listarAgendamentoString(List<AgendamentoVisita> lista) {

        for (AgendamentoVisita a : lista) {
            Calendar c = Calendar.getInstance();
            c.setTime(a.getData());
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
            String dataConvertida = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
            String cidade;
            cidade = a.getSolicitacaoVisita().getUsuarioComum().getBairro().getCidade().getNomeCidade();
            String montar1 = ("Data: " + dataConvertida + ", Cidade: " + cidade);
            listaAgendamento1.add(montar1);

        }
        ArrayAdapter<String> agendamentoArrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, listaAgendamento1);
        listViewAgendamento.setAdapter(agendamentoArrayAdapter);

    }

}
