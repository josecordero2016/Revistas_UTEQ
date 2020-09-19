package com.e.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static com.e.revistasuteq.Clases.Utilitarios.URL_HTML;
import static com.e.revistasuteq.Clases.Utilitarios.URL_PDF;

public class actNavegador extends AppCompatActivity {

    WebView wvPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_navegador);

        wvPrincipal = findViewById(R.id.wv);
        wvPrincipal.getSettings().setJavaScriptEnabled(true);
        wvPrincipal.setWebViewClient(new WebViewClient());
        wvPrincipal.loadUrl(URL_PDF);
    }

    public void mostrarPDF(View view){
        if(URL_PDF==null) Toast.makeText(getApplicationContext(), "No existe el recurso en formato PDF",Toast.LENGTH_LONG).show();
        else{wvPrincipal = findViewById(R.id.wv);
        wvPrincipal.setWebViewClient(new WebViewClient());
        wvPrincipal.loadUrl(URL_PDF);}
    }

    public void mostrarHTML(View view){
        if(URL_HTML==null) Toast.makeText(getApplicationContext(), "No existe el recurso en formato HTML",Toast.LENGTH_LONG).show();
        else{wvPrincipal = findViewById(R.id.wv);
        wvPrincipal.setWebViewClient(new WebViewClient());
        wvPrincipal.loadUrl(URL_HTML);}
    }


}