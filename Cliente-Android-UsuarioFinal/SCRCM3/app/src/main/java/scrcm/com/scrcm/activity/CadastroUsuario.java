package scrcm.com.scrcm.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import scrcm.com.scrcm.R;
import scrcm.com.scrcm.model.Bairro;
import scrcm.com.scrcm.model.Cidade;
import scrcm.com.scrcm.model.UsuarioComum;
import scrcm.com.scrcm.service.BairroService;
import scrcm.com.scrcm.service.CidadeService;
import scrcm.com.scrcm.service.UsuarioComumService;


public class CadastroUsuario extends AppCompatActivity {

    private Button btnCadastrar;
    private Button btnCancelar;
    private Button btnLimparTela;
    private AlertDialog alerta;
    public static boolean valida;

    private EditText edtNome;
    private EditText edtCpf;
    private EditText edtTelefone;
    private EditText edtEmail;
    private EditText edtRua;
    private EditText edtNumero;
    private EditText edtPontoReferencia;
    private EditText edtSenha;
    private EditText edtSenhaConfere;

    private static String nome;
    private static String cpf;
    private static String telefone;
    private static String email;
    private static String rua;
    private static int numero;
    private static String pontoReferencia;
    private static String login;
    private static String senha;
    private static Boolean confereSenha;

    private static ArrayList<Cidade> listaCidade = new ArrayList<Cidade>();
    private Spinner spnCidade;
    private static ArrayList<Bairro> listaBairro = new ArrayList<Bairro>();
    private Spinner spnBairro;
    private static java.util.Date dataUtil = new java.util.Date();
    private static Date dataSql = new java.sql.Date(dataUtil.getTime());
    Cidade cidadeSpn;
    private static Bairro bairrob;
    UsuarioComum usuarioComum = new UsuarioComum();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrarUsuario);
        btnCancelar = (Button) findViewById(R.id.btnCancelarUsuario);
        btnLimparTela = (Button) findViewById(R.id.btnLimparTelaUsuario);
        edtNome = (EditText) findViewById(R.id.edtNomeCadastro);
        edtCpf = (EditText) findViewById(R.id.edtCPFcadastro);
        edtTelefone = (EditText) findViewById(R.id.edtTelefoneCadastro);
        edtEmail = (EditText) findViewById(R.id.edtEmailCadastro);
        edtRua = (EditText) findViewById(R.id.edtRuaCadastro);
        edtNumero = (EditText) findViewById(R.id.edtNumeroCadastro);
        edtPontoReferencia = (EditText) findViewById(R.id.edtPontoReferenciaCadastro);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtSenhaConfere = (EditText) findViewById(R.id.edtSenhaConfere);
        spnBairro = (Spinner) findViewById(R.id.spnBairroCadastro);
        spnCidade = (Spinner) findViewById(R.id.spnCidadeCadastro);

        listarCidade();
        spnCidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int position, long id) {
                cidadeSpn = (Cidade) parent.getSelectedItem();
                //Toast.makeText(CidadeActivity.this, "UF: "+ufTeste.toString(), Toast.LENGTH_SHORT).show();
                Bairro bairro = new Bairro();
                bairro.setCidade(cidadeSpn);
                listaBairro.clear();
                listarBuscarCidade(bairro);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSenha.getText().toString().equals(edtSenhaConfere.getText().toString()) && (!validaEmail(edtEmail.getText().toString()))) {
                    inserirUsuario(null);
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CadastroUsuario.this);
                    alert.setTitle("!!Alerta!!");
                    alert.setMessage("Senhas não conferem! Digite novamente.");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                    edtSenhaConfere.setError("Senhas não conferem!");
                    edtSenha.setError("Senhas não conferem!");
                }
                if (!validaEmail(edtEmail.getText().toString())){
                    AlertDialog.Builder alert = new AlertDialog.Builder(CadastroUsuario.this);
                    alert.setTitle("!!Alerta!!");
                    alert.setMessage("E-mail Inválido!");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                }
            }
        });
    }

    private boolean validaEmail(String email2)    {
        boolean check;
        Pattern p;
        Matcher m;
        String EMAIL_STRING = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);
        m = p.matcher(email2);
        check = m.matches();
        if(!check){
            edtEmail.setError("E-mail inválido!");
        }
        return check;
    }

    public void inserirUsuario(View view) {
        boolean confere;
        String numeroconfere = edtNumero.getText().toString();
        nome = edtNome.getText().toString();
        cpf = edtCpf.getText().toString();
        telefone = edtTelefone.getText().toString();
        email = edtEmail.getText().toString();
        rua = edtRua.getText().toString();
        pontoReferencia = edtPontoReferencia.getText().toString();
        senha = edtSenha.getText().toString();
        bairrob = (Bairro) spnBairro.getSelectedItem();
//valida os dados passados
        confere = validarDadosSolicitacao(numeroconfere, nome, cpf, telefone, email, rua, pontoReferencia, senha, bairrob.getNomeBairro());

        if (confere) {
            numero = Integer.parseInt(edtNumero.getText().toString());
            new AsyncInserir().execute();
        } else {
            imprimirAlerta();
        }
    }

    class AsyncInserir extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            usuarioComum.setNome(nome);
            usuarioComum.setCpf(cpf);
            usuarioComum.setRua(rua);
            usuarioComum.setNumero(numero);
            usuarioComum.setPontoReferencia(pontoReferencia);
            usuarioComum.setSenha(senha);
            usuarioComum.setEmail(email);
            usuarioComum.setTelefone(telefone);
            usuarioComum.setDataCadastro(dataSql);
            usuarioComum.setBairro(bairrob);

            try {
                return UsuarioComumService.inserir(usuarioComum);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean resultado) {
            if (resultado) {
                Toast.makeText(CadastroUsuario.this, "Usuário Inserido com Sucesso!", Toast.LENGTH_SHORT).show();
                limparTela(null);
            } else {
                Toast.makeText(CadastroUsuario.this, "Problema na Inserção do Usuário", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void listarCidade() {
        new AsyncListarCidade().execute();
    }

    class AsyncListarCidade extends AsyncTask<Void, Void, ArrayList<Cidade>> {

        @Override
        protected ArrayList<Cidade> doInBackground(Void... params) {
            try {
                return CidadeService.listar();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Cidade> result) {
            listaCidade = result;

            if (result != null) {
                ArrayAdapter<Cidade> cidadeArrayAdapter = new ArrayAdapter<Cidade>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, result);
                spnCidade.setAdapter(cidadeArrayAdapter);
            } else {
                Toast.makeText(CadastroUsuario.this, "Problema na Listagem das Cidades", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void listarBuscarCidade(Bairro b) {
        new AsyncBuscarCidade().execute();
        bairrob = b;
    }

    class AsyncBuscarCidade extends AsyncTask<Void, Void, ArrayList<Bairro>> {

        @Override
        protected ArrayList<Bairro> doInBackground(Void... params) {
            try {
                return BairroService.buscarPorCidade(bairrob);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Bairro> result) {
            listaBairro.clear();
            listaBairro = result;

            if (result != null) {
                ArrayAdapter<Bairro> bairroArrayAdapter = new ArrayAdapter<Bairro>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, listaBairro);
                spnBairro.setAdapter(bairroArrayAdapter);
            } else {
                Toast.makeText(CadastroUsuario.this, "Problema na Listagem das Cidades", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void imprimirAlerta() {
        AlertDialog.Builder alert = new AlertDialog.Builder(CadastroUsuario.this);
        alert.setTitle("!!Alerta!!");
        alert.setMessage("Existe(m) Campo(s) Vázio(s!");
        alert.setPositiveButton("OK", null);
        alert.show();
    }

    public void limparTela(View view) {
        edtNome.setText("");
        edtCpf.setText("");
        edtEmail.setText("");
        edtTelefone.setText("");
        edtRua.setText("");
        edtNumero.setText("");
        edtPontoReferencia.setText("");
        edtSenha.setText("");
        edtSenhaConfere.setText("");
        listarCidade();

    }

    public boolean validarDadosSolicitacao(String valor1, String valor2, String valor3, String valor4,
                                           String valor5, String valor6, String valor7, String valor8, String valor9) {
        boolean valida = true;
        if (valor1.trim().isEmpty()) {
            valida = false;
        } else if (valor2.trim().isEmpty()) {
            valida = false;
        } else if (valor3.trim().isEmpty()) {
            valida = false;
        } else if (valor4.trim().isEmpty()) {
            valida = false;
        } else if (valor5.trim().isEmpty()) {
            valida = false;
        } else if (valor6.trim().isEmpty()) {
            valida = false;
        } else if (valor7.trim().isEmpty()) {
            valida = false;
        } else if (valor8.trim().isEmpty()) {
            valida = false;
        } else if (valor9.trim().isEmpty()) {
            valida = false;
        }

        return valida;

    }

    public void Cancelar(View view) {
        finish();
    }
}
