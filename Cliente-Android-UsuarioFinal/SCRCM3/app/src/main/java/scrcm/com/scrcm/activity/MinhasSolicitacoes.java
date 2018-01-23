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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import scrcm.com.scrcm.R;
import scrcm.com.scrcm.model.SolicitacaoVisita;
import scrcm.com.scrcm.service.SolicitacaoVisitaService;

public class MinhasSolicitacoes extends AppCompatActivity {

    private static SolicitacaoVisita solicitacaoVisita = new SolicitacaoVisita();
    private static ArrayList<SolicitacaoVisita> listaSolicitacao = new ArrayList<>();
    private ListView listViewSolicitacao;
    private static ArrayList<String> listaSolicitacaoString = new ArrayList<>();
    private static String codigo;
    private Button btnVoltarSolicitacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_solicitacoes);
        btnVoltarSolicitacao = (Button) findViewById(R.id.btnVoltarSolicitacao);

        //recebendo codigo do usuario que ja foi buscado anteriormente para evitar buscar novamente a cada tela
        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null) {
                codigo = params.getString("codigo");
            }
        }

        listViewSolicitacao = (ListView) findViewById(R.id.listaSolicitacao);
        listaSolicitacao.clear();
        listaSolicitacaoString.clear();
        listarSolicitacoes();

        listViewSolicitacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codigoPosicao = position;
                listViewSolicitacao.getPositionForView(view);
                Object objeto = listaSolicitacao.get(codigoPosicao);
                solicitacaoVisita = (SolicitacaoVisita) objeto;
                detalhesSolicitacao(solicitacaoVisita);
            }
        });

        btnVoltarSolicitacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MinhasSolicitacoes.this, MainActivity.class));
            }
        });
    }

    public void listarSolicitacaoString(List<SolicitacaoVisita> lista) {

        for (SolicitacaoVisita so : lista) {
            Calendar c = Calendar.getInstance();
            if (so.getDataSolcitacao() == null) {
                java.util.Date dataUtil = new java.util.Date();
                Date dataSql = new java.sql.Date(dataUtil.getTime());
                c.setTime(dataSql);
            } else {
                c.setTime(so.getDataSolcitacao());
            }
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
            String dataConvertida = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
            String status;

            if (so.getStatus() == 1) {
                status = "Em avaliação!";
            } else if (so.getStatus() == 2) {
                status = "Agendada!";
            } else {
                status = "Insufiente!";
            }

            String montarData = ("Data: " + dataConvertida + ", Situação: " + status);
            listaSolicitacaoString.add(montarData);

        }
        ArrayAdapter<String> agendamentoArrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, listaSolicitacaoString);
        listViewSolicitacao.setAdapter(agendamentoArrayAdapter);

    }

    public void listarSolicitacoes() {
        new AsyncListarSolicitacoes().execute();
    }

    class AsyncListarSolicitacoes extends AsyncTask<Void, Void, ArrayList<SolicitacaoVisita>> {
        @Override
        protected ArrayList<SolicitacaoVisita> doInBackground(Void... params) {
            try {
                return SolicitacaoVisitaService.listarPorUsuario(Integer.valueOf(codigo));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<SolicitacaoVisita> result) {
            listaSolicitacao = result;
            if (result != null) {
                listarSolicitacaoString(result);
            } else {
                Toast.makeText(MinhasSolicitacoes.this, "Problema ao carregar as Solitações", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //função para exibir um alerte com os dados de uma solicitação que foi selecionada
    public void detalhesSolicitacao(SolicitacaoVisita so) {
        //teste apenas
        System.out.print("solicitacaoVisita = " + solicitacaoVisita);
        System.out.print("so = " + so);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MinhasSolicitacoes.this);
        View mView = getLayoutInflater().inflate(R.layout.solicitacao_detalhes, null);

        Button btnVoltar = (Button) mView.findViewById(R.id.btnVoltarDetalhes);

        final TextView edtPossuiPropriedade = (TextView) mView.findViewById(R.id.edtPossuiPropriedadeDetalhes);
        final TextView edtQtdMediaAnimais = (TextView) mView.findViewById(R.id.edtQtdMediaAnimaisDetalhes);
        final TextView edtQtdAnimaisMordidos = (TextView) mView.findViewById(R.id.edtQtdAnimaisMordidosDetalhes);
        final TextView edtCasosdeMorte = (TextView) mView.findViewById(R.id.edtCasosdeMorteDetalhes);
        final TextView edtPropriedadeLocais = (TextView) mView.findViewById(R.id.edtPropriedadeLocaisDetalhes);
        final TextView edtTempoOcorrido = (TextView) mView.findViewById(R.id.edtTempoOcorridoDetalhes);
        final TextView edtConheceAbrigo = (TextView) mView.findViewById(R.id.edtConheceAbrigoDetalhes);
        final TextView edtSolicitarRecolhimento = (TextView) mView.findViewById(R.id.edtSolicitarRecolhimentoDetalhes);
        final TextView edtObservacoes = (TextView) mView.findViewById(R.id.edtObservacoesDetalhes);
        final TextView edtData = (TextView) mView.findViewById(R.id.edtData);

        final String x = String.valueOf(so.getQtdMediaAnimais());
        final String xy = String.valueOf(so.getQtdAnimaisMordidos());

        if (so != null) {
            Calendar c = Calendar.getInstance();
            if (so.getDataSolcitacao() == null) {
                java.util.Date dataUtil = new java.util.Date();
                Date dataSql = new java.sql.Date(dataUtil.getTime());
                c.setTime(dataSql);
            } else {
                c.setTime(so.getDataSolcitacao());
            }
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
            String dataConvertida = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());

            edtPossuiPropriedade.setText(so.getPossuiPropriedade());
            edtQtdMediaAnimais.setText(x);
            edtQtdAnimaisMordidos.setText(xy);
            edtConheceAbrigo.setText(so.getConheceAbrigo());
            edtCasosdeMorte.setText(so.getCasosMorteRegiao());
            edtPropriedadeLocais.setText(so.getProprieLocaisProximos());
            edtTempoOcorrido.setText(so.getTempoOcorridoMorte());
            edtSolicitarRecolhimento.setText(so.getSolicitarRecolhimento());
            edtObservacoes.setText(so.getObservacoes());
            edtData.setText(dataConvertida);

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
