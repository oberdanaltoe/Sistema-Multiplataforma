package scrcm.com.scrcm.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import scrcm.com.scrcm.R;
import scrcm.com.scrcm.model.UsuarioComum;
import scrcm.com.scrcm.service.Logado;
import scrcm.com.scrcm.service.UsuarioComumService;

public class LoginUser extends AppCompatActivity {

    private EditText edtLogin;
    private EditText edtSenha;
    private Button btnLogar;
    private Button btnCadastro;

    private AlertDialog alerta;
    private static Logado logado = new Logado();
    private static UsuarioComum usuarioComum = new UsuarioComum();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnCadastro = (Button) findViewById(R.id.btnCadastrarUsuario1);

        //verificando se o usuario ja esta logado
        //exemplo: se minimizou a aplicação e não fez logoff
        // assim não fica pedindo login toda vez que minimiza e volta na aplicação
        if (logado.getEstaLogado() == null) {
            logado.setEstaLogado(false);
        }
        if (logado.getEstaLogado()) {
            startActivity(new Intent(LoginUser.this, MainActivity.class));
        }
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginUser.this, CadastroUsuario.class));
            }
        });

    }

    public void validar(View view) {
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();

        if (!login.isEmpty() && !senha.isEmpty()) {
            usuarioComum.setEmail(login);
            usuarioComum.setSenha(senha);
            new AsyncValidar().execute();
        } else {
            alertComposVazios();
        }

    }

    class AsyncValidar extends AsyncTask<Void, Void, Logado> {

        @Override
        protected Logado doInBackground(Void... params) {

            try {
                logado.setEstaLogado(UsuarioComumService.verificaLogin1(usuarioComum));
                return logado;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Logado result) {
            if (logado.getEstaLogado()) {
                Toast.makeText(LoginUser.this, "Usuario Logado", Toast.LENGTH_LONG).show();
                passandoLoginConferido();
            } else {
                alertLoginErro();
            }
        }
    }

    //função para passar o email para a proxima tela para buscar os dados do ususario logado,
    // nesse momento não é necessario buscar seus dados, mas somente v erificar seu login e senha
    public void passandoLoginConferido() {
        if (logado.getEstaLogado()) {
            String email = usuarioComum.getEmail().toString();
            Bundle params = new Bundle();
            params.putString("email", email);

            Intent intent = new Intent(LoginUser.this, MainActivity.class);
            intent.putExtras(params);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginUser.this, "Erro ao conectar. Tente novamente!", Toast.LENGTH_SHORT).show();
        }
    }

    private void alertComposVazios() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Login");
        //define a mensagem
        builder.setMessage("Campo(s) Vázio(s)!");
        builder.setPositiveButton("OK", null);
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    private void alertLoginErro() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Login");
        //define a mensagem
        builder.setMessage("Login ou Senha inválidos!");
        builder.setPositiveButton("OK", null);
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}
