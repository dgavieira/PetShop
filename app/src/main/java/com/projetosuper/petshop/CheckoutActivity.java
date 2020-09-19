package com.projetosuper.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.projetosuper.petshop.model.Compra;

//como pegar um double de outra activity
// https://stackoverflow.com/questions/14643051/how-to-retrieve-a-double-value-from-one-activity-to-another
public class CheckoutActivity extends AppCompatActivity {
    double valor = 0;
    Compra compra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //recupera o bundle da activity pai
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //recupera os itens individualmente do bundle
        valor = extras.getDouble("VALOR_TOTAL_COMPRA");
        compra = (Compra) extras.getSerializable("Compra");
    }
}