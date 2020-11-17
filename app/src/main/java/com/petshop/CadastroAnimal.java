package com.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.petshop.adapter.AnimalViewAdapter;
import com.petshop.db.DAO;
import com.petshop.model.Animais;

import java.util.ArrayList;
import java.util.List;

public class CadastroAnimal extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextRaca;
    Switch switchCategoria;
    Button botaoSalvar;


    Context context;
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_animal);

        context = getApplicationContext();

        editTextNome = findViewById(R.id.editTextNome);
        editTextRaca = findViewById(R.id.editTextRaca);
        switchCategoria = findViewById(R.id.switchCategoria);
        botaoSalvar = findViewById(R.id.botaoSalvar);
        recyclerView = findViewById(R.id.recyclerView);

        buscaBanco();

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verificação de campos nulos.
                if(!(editTextNome.getText().toString().equals("") || editTextRaca.getText().toString().equals(""))){
                    String classe;
                    if (switchCategoria.isChecked()) {
                        classe = "Cachorro";
                    }else{
                        classe = "Gato";
                    }
                    //instancia do DAO.
                    insereAnimais(classe);
                    limpaFormulario();
                    buscaBanco();

                }else{

                    Toast.makeText(getApplicationContext(),"Campos vazios",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void insereAnimais(String classe) {
        DAO dao = new DAO(getApplicationContext());
        Animais animaisParaInserir = new Animais();
        animaisParaInserir.setNome(editTextNome.getText().toString());
        animaisParaInserir.setRaca(editTextRaca.getText().toString());
        animaisParaInserir.setCategoria(classe);
        animaisParaInserir.setFoto("");
        dao.insereAnimal(animaisParaInserir,null);
        dao.close();
    }

    private void buscaBanco() {
        DAO dao2 = new DAO(getApplicationContext());

        List<Animais> animal = dao2.buscaAnimais();

        List<String> nomes = new ArrayList<String>();
        List<String> racas = new ArrayList<String>();
        List<String> categorias = new ArrayList<String>();
        List<String> fotos = new ArrayList<String>();

        String[] dados_nomes = new String[] {};
        String[] dados_racas = new String[] {};
        String[] dados_categorias = new String[] {};
        String[] dados_fotos = new String[] {};


        for(Animais nomeBuscado : animal){
            nomes.add(nomeBuscado.getNome());
            racas.add(nomeBuscado.getRaca());
            categorias.add(String.valueOf(nomeBuscado.getCategoria()));
            fotos.add(String.valueOf(nomeBuscado.getFoto()));

        }

        dados_nomes = nomes.toArray(new String[0]);
        dados_racas = racas.toArray(new String[0]);
        dados_categorias = categorias.toArray(new String[0]);
        dados_fotos = fotos.toArray(new String[0]);

        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new AnimalViewAdapter(context,
                dados_nomes,
                dados_racas,
                dados_categorias,
                dados_fotos);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    private void limpaFormulario() {
        editTextNome.setText("");
        editTextRaca.setText("");
        editTextNome.requestFocus();
        switchCategoria.setChecked(false);
    }


    //Atualiza e busca no banco de dados atualizado.
    @Override
    public void onResume(){
        super.onResume();
        buscaBanco();
    }
}