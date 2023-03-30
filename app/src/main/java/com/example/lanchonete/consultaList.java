package com.example.lanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.lanchonete.repositorio.CriaBD;
import com.example.lanchonete.repositorio.bdController;

public class consultaList extends AppCompatActivity {

    public ListView listView;
    public Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_list);

        listView = findViewById(R.id.listAlunos);
        btn = findViewById(R.id.btnCadastrar);


        bdController controller = new bdController(consultaList.this);

        Cursor cursor = controller.listarDados();
        CriaBD bancos = new CriaBD(consultaList.this);

        String[] campos = new String[]{bancos.id,bancos.nome, bancos.preco, bancos.ingre, bancos.teor, bancos.tipo};
        int[] idViews = new int[]{R.id.idd,R.id.nome, R.id.preco, R.id.ingredientes, R.id.teor, R.id.tipo};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(consultaList.this, R.layout.activity_visualizar_lanche, cursor, campos, idViews);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String id;
                cursor.moveToPosition(i);
                id = cursor.getString(cursor.getColumnIndexOrThrow(bancos.id));
                Intent intent = new Intent(consultaList.this, atualizar.class);
                intent.putExtra("codigo", id);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(consultaList.this, CadastrarLanche.class);
                startActivity(intent);
            }
        });
    }
}