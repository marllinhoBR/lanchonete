package com.example.lanchonete;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lanchonete.repositorio.CriaBD;
import com.example.lanchonete.repositorio.bdController;

public class atualizar extends AppCompatActivity {

   EditText  txtNome,txtPreco, txtIngre, txtTeor, txtTipo;
   Button delete, atualiza;
   String id;
   Cursor cursor;
   AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        txtNome = findViewById(R.id.nome2);
        txtTipo = findViewById(R.id.tipo2);
        txtTeor = findViewById(R.id.teor2);
        txtPreco= findViewById(R.id.preco2);
        txtIngre = findViewById(R.id.ingre2);
        atualiza = findViewById(R.id.editar);
        delete = findViewById(R.id.delete);

        id = this.getIntent().getStringExtra("codigo");

        bdController controller = new bdController(atualizar.this);

        cursor = controller.carregarCampos(Integer.parseInt(id));

        CriaBD banco = new CriaBD(atualizar.this);

        txtNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(banco.nome)));
        txtTipo.setText(cursor.getString(cursor.getColumnIndexOrThrow(banco.tipo)));
        txtTeor.setText(cursor.getString(cursor.getColumnIndexOrThrow(banco.teor)));
        txtPreco.setText(cursor.getString(cursor.getColumnIndexOrThrow(banco.preco)));
        txtIngre.setText(cursor.getString(cursor.getColumnIndexOrThrow(banco.ingre)));


        atualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.atualizar(Integer.parseInt(id), txtNome.getText(),
                        Double.parseDouble(String.valueOf(txtPreco.getText())),
                        String.valueOf(txtIngre.getText()),
                        Double.parseDouble(String.valueOf(txtTeor.getText())),
                        String.valueOf(txtTipo.getText()));
                Intent intent = new Intent(atualizar.this, consultaList.class);
                startActivity(intent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(atualizar.this);
                builder.setTitle("ALERTA!!!");

                builder.setMessage("quer mesmo excluir?");

                builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(atualizar.this, "excluido" , Toast.LENGTH_SHORT).show();

                        controller.deletar(Integer.parseInt(id));
                        Intent intent = new Intent(atualizar.this, consultaList.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(atualizar.this, "negativo", Toast.LENGTH_SHORT).show();
                    }
                });

                alerta = builder.create();

                alerta.show();




            }
        });
    }
}