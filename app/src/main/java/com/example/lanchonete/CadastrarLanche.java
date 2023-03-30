    package com.example.lanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lanchonete.repositorio.bdController;

    public class CadastrarLanche extends AppCompatActivity {

    EditText nome, preco, ingre, teor, tipo;
    Button cadastrar,visualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_lanche);

        nome = findViewById(R.id.nome1);
        preco = findViewById(R.id.preco1);
        ingre = findViewById(R.id.ingre1);
        teor = findViewById(R.id.teor1);
        tipo = findViewById(R.id.tipo1);
        cadastrar = findViewById(R.id.btnCadastro);
        visualizar = findViewById(R.id.btnVisualizar);


        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(nome.getText().toString())) {
                    Toast.makeText(CadastrarLanche.this, "Favor insira o nome do Lanche.", Toast.LENGTH_SHORT).show();
                } else if (preco.getText().toString().isEmpty()) {
                    Toast.makeText(CadastrarLanche.this, "Favor insira o preço do lanche.", Toast.LENGTH_SHORT).show();
                } else if (ingre.getText().toString().isEmpty()) {
                    Toast.makeText(CadastrarLanche.this, "Favor insira o ingrediente.", Toast.LENGTH_SHORT).show();
                } else if(teor.getText().toString().isEmpty()){
                    Toast.makeText(CadastrarLanche.this, "Favor insira o teor calórico.", Toast.LENGTH_SHORT).show();
                } else if(tipo.getText().toString().isEmpty()){
                    Toast.makeText(CadastrarLanche.this, "Favor insira o tipo de lanche.", Toast.LENGTH_SHORT).show();
                }else{
                    try{
                        bdController controller = new bdController(CadastrarLanche.this);
                        String result = controller.inserirDados(String.valueOf(nome.getText()),
                                Double.parseDouble(String.valueOf(preco.getText())),
                                String.valueOf(ingre.getText()),
                                Double.parseDouble(String.valueOf(teor.getText())),
                                String.valueOf(tipo.getText()));

                        Toast.makeText(CadastrarLanche.this, "Lanche cadastrado: " + result, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                    }

                }
            }
        });

        visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visualizar= new Intent(CadastrarLanche.this, consultaList.class);
                startActivity(visualizar);
            }
        });
    }
}