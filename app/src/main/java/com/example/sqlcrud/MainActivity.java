package com.example.sqlcrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // referencias aos botões e controles
    Button botao_vertodos, botao_adicionar;
    EditText texto_nome, numero_idade;
    Switch switch_vizinho;
    ListView lista_pessoas;

    ArrayAdapter usuarioArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao_adicionar = findViewById(R.id.botao_adicionar);
        botao_vertodos = findViewById(R.id.botao_vertodos);
        texto_nome = findViewById(R.id.texto_nome);
        numero_idade = findViewById(R.id.numero_idade);
        switch_vizinho = findViewById(R.id.switch_vizinho);
        lista_pessoas = findViewById(R.id.lista_pessoas);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        MostraUsuariosListView();


        // listeners dos botões
        botao_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PessoasModelo modeloPessoas;

                try{
                    modeloPessoas = new PessoasModelo(-1, texto_nome.getText().toString(), Integer.parseInt(numero_idade.getText().toString()), switch_vizinho.isChecked());
                    Toast.makeText(MainActivity.this, modeloPessoas.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Erro ao criar usuário", Toast.LENGTH_SHORT).show();
                    modeloPessoas = new PessoasModelo(-1, "Erro", 0, false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean sucesso = dataBaseHelper.adicionaUm(modeloPessoas);

                Toast.makeText(MainActivity.this, "Sucesso= " + sucesso, Toast.LENGTH_SHORT).show();
                MostraUsuariosListView();
            }
        });

        botao_vertodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                MostraUsuariosListView();

                //Toast.makeText(MainActivity.this, todasPessoas.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        lista_pessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PessoasModelo usuarioClicado = (PessoasModelo) adapterView.getItemAtPosition(i);
                dataBaseHelper.deletaUm(usuarioClicado);
                MostraUsuariosListView();
                Toast.makeText(MainActivity.this, "Deletado " + usuarioClicado.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void MostraUsuariosListView() {
        usuarioArrayAdapter = new ArrayAdapter<PessoasModelo>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getTodasPessoas());
        lista_pessoas.setAdapter(usuarioArrayAdapter);
    }
}