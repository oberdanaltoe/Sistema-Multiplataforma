package scrcm.com.scrcm.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import scrcm.com.scrcm.R;
import scrcm.com.scrcm.model.UsuarioComum;
import scrcm.com.scrcm.service.Logado;
import scrcm.com.scrcm.service.UsuarioComumService;

public class MainActivity extends AppCompatActivity {

    private Button btnAgendamentoIdaf;
    private Button btnSolicitacaoVisita;
    private Button btnMinhasSolicitacoes;
    private Button btnMensagens;
    private Button btnCadastro;

    private AlertDialog alerta;
    private static Logado logado = new Logado();

    private String email;
    private static UsuarioComum usuarioComumx = new UsuarioComum();
    private static UsuarioComum usuarioComumRes = new UsuarioComum();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recendo email do usuario logado para fazer a busca dos dados do usuario
        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null) {
                email = params.getString("email");
                usuarioComumx.setEmail(email);
                buscarUsuario();
            }
        }

        btnAgendamentoIdaf = (Button) findViewById(R.id.btnAgendaIdaf);
        btnSolicitacaoVisita = (Button) findViewById(R.id.btnSolicitacaoVisita);
        btnMinhasSolicitacoes = (Button) findViewById(R.id.btnMinhasSolicitacoes);
        btnMensagens = (Button) findViewById(R.id.btnMensagens);
        btnCadastro = (Button) findViewById(R.id.btnCadastroUsuario);

        btnAgendamentoIdaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AgendaIdafActivity.class));
            }
        });
        btnSolicitacaoVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passando codigo do usuario para evitar novas buscas de seus dados, somente o codigo sera necessario na proxima classe
                String codigoUser = String.valueOf(usuarioComumRes.getCdUsuarioComum());

                Bundle params = new Bundle();
                params.putString("codigo", codigoUser);
                Intent intent = new Intent(MainActivity.this, SolicitacaoActivity.class);
                intent.putExtras(params);
                startActivity(intent);
                finish();
            }
        });

        btnMinhasSolicitacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passando codigo do usuario para evitar novas buscas de seus dados, somente o codigo sera necessario na proxima classe
                String codigoUser = String.valueOf(usuarioComumRes.getCdUsuarioComum());

                Bundle params = new Bundle();
                params.putString("codigo", codigoUser);
                Intent intent = new Intent(MainActivity.this, MinhasSolicitacoes.class);
                intent.putExtras(params);
                startActivity(intent);
                finish();
            }
        });

        btnMensagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MensagensActivity.class));
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CadastroUsuario.class));
            }
        });
    }

    public void buscarUsuario() {
        new AsyncBuscarUsuario().execute();
    }

    class AsyncBuscarUsuario extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                usuarioComumRes = UsuarioComumService.buscarLoginEmail(usuarioComumx);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        protected void onPostExecute(Boolean result) {
            if (!result) {
                Toast.makeText(MainActivity.this, "Problema ao Buscar Usuário", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void alertComposVazios() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Login");
        //define a mensagem
        builder.setMessage("Campo(s) Vázio(s)!");
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    public void logoff(View view) {
        logado.setEstaLogado(false);
        finish();
    }

}
