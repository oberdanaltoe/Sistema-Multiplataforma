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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import scrcm.com.scrcm.R;
import scrcm.com.scrcm.model.SolicitacaoVisita;
import scrcm.com.scrcm.model.TipoAbrigo;
import scrcm.com.scrcm.model.TipoAbrigoSolicitacao;
import scrcm.com.scrcm.model.UsuarioComum;
import scrcm.com.scrcm.service.SolicitacaoVisitaService;
import scrcm.com.scrcm.service.TipoAbrigoService;
import scrcm.com.scrcm.service.TipoAbrigoSolicitacaoService;


public class SolicitacaoActivity extends AppCompatActivity {

    private Button btnSalvar;
    private Button btnLimparTela;
    private Button btnCancelar;
    private EditText edtQtdMediaAnimais;
    private EditText edtQtdAnimaisMordidos;
    private EditText ediObservacoes;

    private TextView propriedadeLocais;
    private TextView tempodoOcorrido;
    private TextView abrigos;

    private Spinner spnPossuiPropiedade;
    private Spinner spnHouveMorteRegiao;
    private Spinner spnPropriedadeLocaisProx;
    private Spinner spnTempoOcorrido;
    private Spinner spnConheceAbrigo;
    private Spinner spnSolRecolhimentoCer;

    private CheckBox cbCasaVelha;
    private CheckBox cbOcoArvore;
    private CheckBox cbCaverna;
    private CheckBox cbTuneuTrem;
    private CheckBox cbForroCasa;
    private CheckBox cbBueiro;

    private static String observacoes;
    private static String possuiPropriedade;
    private static String conheceAbrigo;
    private static String solRecolhimento;
    private static String houveMorteRegiao;
    private static String propriedadeLocaisProximos;
    private static String tempoOcorrido;

    private static java.util.Date dataUtil = new java.util.Date();
    private static Date dataSql = new java.sql.Date(dataUtil.getTime());

    private static int qtdMediaAnimais;
    private static int qtdAnimaisMordidos;

    public static boolean valida;

    private ArrayList<TipoAbrigo> listaAbrigos = new ArrayList<>();
    private static ArrayList<TipoAbrigoSolicitacao> listaAbrigoSolicitacao = new ArrayList<>();

    TipoAbrigo tipoAbrigo1 = new TipoAbrigo();
    TipoAbrigoSolicitacao tipoAbrigoSolicitacao1 = new TipoAbrigoSolicitacao();
    TipoAbrigo tipoAbrigo2 = new TipoAbrigo();
    TipoAbrigoSolicitacao tipoAbrigoSolicitacao2 = new TipoAbrigoSolicitacao();
    TipoAbrigo tipoAbrigo3 = new TipoAbrigo();
    TipoAbrigoSolicitacao tipoAbrigoSolicitacao3 = new TipoAbrigoSolicitacao();
    TipoAbrigo tipoAbrigo4 = new TipoAbrigo();
    TipoAbrigoSolicitacao tipoAbrigoSolicitacao4 = new TipoAbrigoSolicitacao();
    TipoAbrigo tipoAbrigo5 = new TipoAbrigo();
    TipoAbrigoSolicitacao tipoAbrigoSolicitacao5 = new TipoAbrigoSolicitacao();
    TipoAbrigo tipoAbrigo6 = new TipoAbrigo();
    TipoAbrigoSolicitacao tipoAbrigoSolicitacao6 = new TipoAbrigoSolicitacao();
    SolicitacaoVisita solicitacaoVisita = new SolicitacaoVisita();

    private String[] arraySimNao = new String[]{" ", "Sim", "Não"};
    private String[] arrayPropLocaisProx = new String[]{" ", "Propriedade", "Regiões Proximas"};
    private String[] arrayTempoMorte = new String[]{" ", "Menos de uma Semana", "Duas Semanas", "Mais de um mês"};

    private static String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);
//recebendo parametro: codigo do usuario que logou
        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null) {
                codigo = params.getString("codigo");
            }
        }

        propriedadeLocais = (TextView) findViewById(R.id.textView12);
        tempodoOcorrido = (TextView) findViewById(R.id.textView11);
        abrigos = (TextView) findViewById(R.id.textView14);

        spnConheceAbrigo = (Spinner) findViewById(R.id.spnConheceAbrigo);
        spnPossuiPropiedade = (Spinner) findViewById(R.id.spnPossuiPropiedade);
        spnHouveMorteRegiao = (Spinner) findViewById(R.id.spnMorteRegiao);
        spnTempoOcorrido = (Spinner) findViewById(R.id.spnTempoOcorrido);
        spnSolRecolhimentoCer = (Spinner) findViewById(R.id.spSolRecolhimentoCer);
        spnPropriedadeLocaisProx = (Spinner) findViewById(R.id.spnPropriedadeLocaisProx);

        cbBueiro = (CheckBox) findViewById(R.id.cbBueiro);
        cbCasaVelha = (CheckBox) findViewById(R.id.cbCasaVelha);
        cbCaverna = (CheckBox) findViewById(R.id.cbCaverna);
        cbForroCasa = (CheckBox) findViewById(R.id.cbForroCasa);
        cbTuneuTrem = (CheckBox) findViewById(R.id.cbTuneuTrem);
        cbOcoArvore = (CheckBox) findViewById(R.id.cbOcoArvore);

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnLimparTela = (Button) findViewById(R.id.btnLimparTela);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        ediObservacoes = (EditText) findViewById(R.id.edtObservacoes);
        edtQtdMediaAnimais = (EditText) findViewById(R.id.edtQtdMediaAnimais);
        edtQtdAnimaisMordidos = (EditText) findViewById(R.id.edtQtdAnimaisMordidos);

        //colocando esse checkbox como invisiveis para que somente fiquem visisveis atraves de uma determinada resposta
        abrigos.setVisibility(View.INVISIBLE);
        spnPropriedadeLocaisProx.setVisibility(View.INVISIBLE);
        propriedadeLocais.setVisibility(View.INVISIBLE);
        spnTempoOcorrido.setVisibility(View.INVISIBLE);
        tempodoOcorrido.setVisibility(View.INVISIBLE);
        cbCasaVelha.setVisibility(View.INVISIBLE);
        cbBueiro.setVisibility(View.INVISIBLE);
        cbCaverna.setVisibility(View.INVISIBLE);
        cbForroCasa.setVisibility(View.INVISIBLE);
        cbTuneuTrem.setVisibility(View.INVISIBLE);
        cbOcoArvore.setVisibility(View.INVISIBLE);

        limparTela(null);

        spnConheceAbrigo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int position, long id) {
                String simnao;
                simnao = (String) parent.getSelectedItem();
                if (simnao.equals("Sim")) {
                    abrigos.setVisibility(View.VISIBLE);
                    cbCasaVelha.setVisibility(View.VISIBLE);
                    cbBueiro.setVisibility(View.VISIBLE);
                    cbCaverna.setVisibility(View.VISIBLE);
                    cbForroCasa.setVisibility(View.VISIBLE);
                    cbTuneuTrem.setVisibility(View.VISIBLE);
                    cbOcoArvore.setVisibility(View.VISIBLE);
                } else {
                    abrigos.setVisibility(View.INVISIBLE);
                    cbCasaVelha.setVisibility(View.INVISIBLE);
                    cbBueiro.setVisibility(View.INVISIBLE);
                    cbCaverna.setVisibility(View.INVISIBLE);
                    cbForroCasa.setVisibility(View.INVISIBLE);
                    cbTuneuTrem.setVisibility(View.INVISIBLE);
                    cbOcoArvore.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        spnHouveMorteRegiao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int position, long id) {
                String simnao;
                simnao = (String) parent.getSelectedItem();
                if (simnao.equals("Sim")) {
                    spnPropriedadeLocaisProx.setVisibility(View.VISIBLE);
                    spnTempoOcorrido.setVisibility(View.VISIBLE);
                    propriedadeLocais.setVisibility(View.VISIBLE);
                    tempodoOcorrido.setVisibility(View.VISIBLE);
                } else {
                    spnPropriedadeLocaisProx.setVisibility(View.INVISIBLE);
                    spnTempoOcorrido.setVisibility(View.INVISIBLE);
                    propriedadeLocais.setVisibility(View.INVISIBLE);
                    tempodoOcorrido.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    //[Início] Listar TipoAbrigos ---------------------------------------------------------------------
    public void listarTipoAbrigo(View view) {
        new SolicitacaoActivity.AsyncListar().execute();
    }

    class AsyncListar extends AsyncTask<Void, Void, ArrayList<TipoAbrigo>> {

        @Override
        protected ArrayList<TipoAbrigo> doInBackground(Void... params) {
            try {
                return TipoAbrigoService.listar();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<TipoAbrigo> result) {
            listaAbrigos = result;

            if (result != null) {
            } else {
                Toast.makeText(SolicitacaoActivity.this, "Problema na Listagem das UFs", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void inserirSolicitacao(View view) {
        boolean confere;

        String x = edtQtdMediaAnimais.getText().toString();
        String y = edtQtdAnimaisMordidos.getText().toString();

        observacoes = ediObservacoes.getText().toString();
        possuiPropriedade = spnPossuiPropiedade.getSelectedItem().toString();
        conheceAbrigo = spnConheceAbrigo.getSelectedItem().toString();
        solRecolhimento = spnSolRecolhimentoCer.getSelectedItem().toString();
        houveMorteRegiao = spnHouveMorteRegiao.getSelectedItem().toString();
        propriedadeLocaisProximos = spnPropriedadeLocaisProx.getSelectedItem().toString();
        tempoOcorrido = spnTempoOcorrido.getSelectedItem().toString();

        if (conheceAbrigo.equals("Sim")) {
            pegarCombobox();
        }

        confere = validarDadosSolicitacao(observacoes, possuiPropriedade, conheceAbrigo, solRecolhimento, houveMorteRegiao, propriedadeLocaisProximos, tempoOcorrido, x, y);
        valida = confere;
        //verificando valor de confere, somente para testes de validação
        System.out.print("\n\n\n confere: " + confere);
        if (confere) {
            qtdMediaAnimais = Integer.parseInt(edtQtdMediaAnimais.getText().toString());
            qtdAnimaisMordidos = Integer.parseInt(edtQtdAnimaisMordidos.getText().toString());
            new AsyncInserir().execute();
        } else {
            imprimirAlertaCamposVazios();
        }
    }

    class AsyncInserir extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            UsuarioComum usuarioComum = new UsuarioComum();
            usuarioComum.setCdUsuarioComum(Integer.valueOf(codigo));

            solicitacaoVisita.setPossuiPropriedade(possuiPropriedade);
            solicitacaoVisita.setQtdMediaAnimais(qtdMediaAnimais);
            solicitacaoVisita.setQtdAnimaisMordidos(qtdAnimaisMordidos);
            solicitacaoVisita.setCasosMorteRegiao(houveMorteRegiao);
            solicitacaoVisita.setProprieLocaisProximos(propriedadeLocaisProximos);
            solicitacaoVisita.setTempoOcorridoMorte(tempoOcorrido);
            solicitacaoVisita.setConheceAbrigo(conheceAbrigo);
            solicitacaoVisita.setSolicitarRecolhimento(solRecolhimento);
            solicitacaoVisita.setObservacoes(observacoes);
            solicitacaoVisita.setDataSolcitacao(dataSql);
            solicitacaoVisita.setListTipoAbrigoSolicitacao(listaAbrigoSolicitacao);
            solicitacaoVisita.setUsuarioComum(usuarioComum);
            solicitacaoVisita.setStatus(1);

            if (valida) {
                try {
                    if (SolicitacaoVisitaService.inserir(solicitacaoVisita)) {
                        inserirListaAbrigos();
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean resultado) {
            if (resultado) {
                Toast.makeText(SolicitacaoActivity.this, "Solicitação Inserida com Sucesso!", Toast.LENGTH_SHORT).show();
                limparTela(null);
            } else {
                Toast.makeText(SolicitacaoActivity.this, "Existem Campos Vázios!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void inserirListaAbrigos() {

        if (solicitacaoVisita.getListTipoAbrigoSolicitacao() != null) {
            for (TipoAbrigoSolicitacao tas : solicitacaoVisita.getListTipoAbrigoSolicitacao()) {

                SolicitacaoVisita sv1 = new SolicitacaoVisita();
                TipoAbrigoSolicitacaoService tass = new TipoAbrigoSolicitacaoService();
                SolicitacaoVisitaService svs1 = new SolicitacaoVisitaService();
                int buscaUltimo1 = 0;
                try {
                    buscaUltimo1 = svs1.buscarUltimo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sv1.setCdSolicitacao(buscaUltimo1);
                tas.setSolicitacaoVisita(sv1);
                try {
                    tass.inserir(tas);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void carregarCombobox() {
        listarTipoAbrigo(null);
        ArrayAdapter<String> adapterSimNao = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, arraySimNao);
        ArrayAdapter<String> adapterPropriedadeLocaisProx = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, arrayPropLocaisProx);
        ArrayAdapter<String> adapterTempoMorte = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, arrayTempoMorte);

        spnConheceAbrigo.setAdapter(adapterSimNao);
        spnSolRecolhimentoCer.setAdapter(adapterSimNao);
        spnHouveMorteRegiao.setAdapter(adapterSimNao);
        spnPossuiPropiedade.setAdapter(adapterSimNao);
        spnPropriedadeLocaisProx.setAdapter(adapterPropriedadeLocaisProx);
        spnTempoOcorrido.setAdapter(adapterTempoMorte);
    }

    public void pegarCombobox() {

        listaAbrigoSolicitacao.clear();
        if (cbOcoArvore.isChecked()) {
            tipoAbrigo1.setCdAbrigo(1);
            tipoAbrigo1.setNomeAbrigo("Oco de Àrvores");
            tipoAbrigoSolicitacao1.settAbrigo(tipoAbrigo1);
            listaAbrigoSolicitacao.add(tipoAbrigoSolicitacao1);
        }
        if (cbCasaVelha.isChecked()) {
            tipoAbrigo2.setCdAbrigo(3);
            tipoAbrigo2.setNomeAbrigo("Casa velha");
            tipoAbrigoSolicitacao2.settAbrigo(tipoAbrigo2);
            listaAbrigoSolicitacao.add(tipoAbrigoSolicitacao2);
        }
        if (cbBueiro.isChecked()) {
            tipoAbrigo3.setCdAbrigo(2);
            tipoAbrigo3.setNomeAbrigo("Bueiro");
            tipoAbrigoSolicitacao3.settAbrigo(tipoAbrigo3);
            listaAbrigoSolicitacao.add(tipoAbrigoSolicitacao3);
        }
        if (cbTuneuTrem.isChecked()) {
            tipoAbrigo4.setCdAbrigo(4);
            tipoAbrigo4.setNomeAbrigo("Túnel de Trem");
            tipoAbrigoSolicitacao4.settAbrigo(tipoAbrigo4);
            listaAbrigoSolicitacao.add(tipoAbrigoSolicitacao4);
        }
        if (cbCaverna.isChecked()) {
            tipoAbrigo5.setCdAbrigo(5);
            tipoAbrigo5.setNomeAbrigo("Cavernas");
            tipoAbrigoSolicitacao5.settAbrigo(tipoAbrigo5);
            listaAbrigoSolicitacao.add(tipoAbrigoSolicitacao5);
        }
        if (cbForroCasa.isChecked()) {
            tipoAbrigo6.setCdAbrigo(6);
            tipoAbrigo6.setNomeAbrigo("Forro de Casa");
            tipoAbrigoSolicitacao6.settAbrigo(tipoAbrigo6);
            listaAbrigoSolicitacao.add(tipoAbrigoSolicitacao6);
        }
//verifica o tamanho da lista, somente para teste
        if (listaAbrigoSolicitacao.size() != 0) {
            System.out.print("\n Tamanho da lista: " + listaAbrigoSolicitacao.size());

        } else {
            Toast.makeText(SolicitacaoActivity.this, "Problema ao Pegar Codigos", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean validarDadosSolicitacao(String valor, String valor1, String valor2, String valor3, String valor4, String valor5, String valor6, String valor7, String valor8) {
        boolean validaConfere = false;
        //trim remove os espaços, isEmpty ver se está vazio
        //isEmpty, que devolve true se a String for vazia ou false caso contrário.

        if (!valor2.trim().isEmpty()) {
            if (conheceAbrigo.equals("Sim")) {
                if (listaAbrigoSolicitacao.size() == 0) {
                    validaConfere = false;
                    imprimirAlertaAbrigos();
                } else {
                    validaConfere = true;
                }
            } else if (conheceAbrigo.equals("Não")) {
                if (listaAbrigoSolicitacao.size() == 0) {
                    validaConfere = true;
                } else {
                    listaAbrigoSolicitacao.clear();
                    cbOcoArvore.setChecked(false);
                    cbCaverna.setChecked(false);
                    cbForroCasa.setChecked(false);
                    cbTuneuTrem.setChecked(false);
                    cbBueiro.setChecked(false);
                    cbCasaVelha.setChecked(false);
                    validaConfere = true;
                }
            }
        } else if (valor2.trim().isEmpty()) {
            validaConfere = false;
        } else if (valor.trim().isEmpty()) {
            validaConfere = false;
        } else if (valor1.trim().isEmpty()) {
            validaConfere = false;
        } else if (valor3.trim().isEmpty()) {
            validaConfere = false;
        } else if (valor4.trim().isEmpty()) {
            validaConfere = false;
        } else if (!valor4.trim().isEmpty()) {
            if (houveMorteRegiao.equals("Sim")) {
                if (propriedadeLocaisProximos.isEmpty()) {
                    validaConfere = false;
                }
                if (tempoOcorrido.isEmpty()) {
                    validaConfere = false;
                } else {
                    validaConfere = true;
                }
            }
        } else if (valor5.trim().isEmpty()) {
            validaConfere = false;
        } else if (valor6.trim().isEmpty()) {
            validaConfere = false;
        } else if (valor7.isEmpty()) {
            validaConfere = false;
        } else if (valor8.isEmpty()) {
            validaConfere = false;
        }

        return validaConfere;
    }

    public void Cancelar(View view) {
        finish();
    }

    public void imprimirAlertaCamposVazios() {
        AlertDialog.Builder alert = new AlertDialog.Builder(SolicitacaoActivity.this);
        alert.setTitle("!!Alerta!!");
        alert.setMessage("Existem campos que precisam ser preenchidos!");
        alert.setPositiveButton("OK", null);
        alert.show();
    }

    public void imprimirAlertaAbrigos() {
        AlertDialog.Builder alert = new AlertDialog.Builder(SolicitacaoActivity.this);
        alert.setTitle("!!Alerta!!");
        alert.setMessage("Você respondeu que conhece abrigo, algum abrigo precisa ser selecionado!");
        alert.setPositiveButton("OK", null);
        alert.show();
    }

    //limpando os campos para um novo cadastro
    public void limparTela(View view) {
        carregarCombobox();
        edtQtdMediaAnimais.setText("");
        edtQtdAnimaisMordidos.setText("");
        ediObservacoes.setText("");
        cbOcoArvore.setChecked(false);
        cbCaverna.setChecked(false);
        cbForroCasa.setChecked(false);
        cbTuneuTrem.setChecked(false);
        cbBueiro.setChecked(false);
        cbCasaVelha.setChecked(false);

    }
}
