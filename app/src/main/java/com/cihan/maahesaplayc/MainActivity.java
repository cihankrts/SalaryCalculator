package com.cihan.maahesaplayc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Farazi verilen başlangıç maaşları
    // profiler 2000, TL 3000, SPV 4000
    // airsider 1500, ofis 1300, sofor 1000
    // dil tazminatı 125, x-ray 150, tatil günleri 100
    // rapor 2 gün ve fazlası -200
    // noshow -200
    // ücretsiz izin -100
    // standart altı -100, standart 0, standart üstü 100, uzman 200
    //mesai saati 10 tl



    private int toplam_maas = 0;
    private int supervisor = 4000;
    private int tl = 3000;
    private int profiler = 2000;
    private int airsider = 1500;
    private int ofis = 1300;
    private int sofor = 1000;
    private int dil_tazminati = 125;
    private int xRay = 150;
    private int tatil_gunleri = 100;
    private int noShow = 200;
    private int Rapor = 100;
    private int ucretsizIzin = 100;
    private int standart_alti = 100;
    private int standart = 0;
    private int standart_ustu = 100;
    private int uzman = 200;
    private int mesaiSaati = 10;


    private Spinner Personel;
    private Spinner Statu;
    private Spinner XRayYesNo;
    private EditText mEdtTextDilTazminati;
    private EditText mEdtTextTatilGunleri;
    private EditText mEdtTextnoShow;
    private EditText mEdtTextRapor;
    private EditText mEdtTextUcretsizIzin;
    private EditText mEdtMesaiSaati;
    private Button mHesapla;
    private TextView mtxtMaas;

    public void init(){
        Personel = (Spinner) findViewById(R.id.spinnerPersonel);
        ArrayAdapter<CharSequence> adapterPersonel = ArrayAdapter.createFromResource(this,
                R.array.Personeller,
                R.layout.spinner_personeller);
        adapterPersonel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Personel.setAdapter(adapterPersonel);

        Statu = (Spinner) findViewById(R.id.spinnerStatu);
        ArrayAdapter<CharSequence> adapterStatu = ArrayAdapter.createFromResource(this,
                R.array.Statu,
                R.layout.spinner_personeller);
        adapterStatu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Statu.setAdapter(adapterStatu);

        XRayYesNo = (Spinner) findViewById(R.id.spinnerXRayYesNo);
        ArrayAdapter<CharSequence> adapterXRay = ArrayAdapter.createFromResource(this,
                R.array.YesNo,
                R.layout.spinner_personeller);
        adapterXRay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        XRayYesNo.setAdapter(adapterXRay);


        mEdtTextDilTazminati = (EditText) findViewById(R.id.edtTextDilTazminati);
        mEdtTextTatilGunleri = (EditText) findViewById(R.id.edtTextTatilGunleri);
        mEdtMesaiSaati = (EditText) findViewById(R.id.edtTextMesaiSaati);
        mEdtTextnoShow = (EditText) findViewById(R.id.edtTextNoShow);
        mEdtTextRapor = (EditText) findViewById(R.id.edtTextRapor);
        mEdtTextUcretsizIzin = (EditText) findViewById(R.id.edtTextUcretsizIzin);
        mHesapla = (Button) findViewById(R.id.btnHesapla);
        mtxtMaas = (TextView) findViewById(R.id.txtMaas);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        mHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hesapla();

                mtxtMaas.setText(toplam_maas + " TL + AGİ");

                toplam_maas = 0;


            }
        });



    }

    private void hesapla(){

        String sPersonel = Personel.getSelectedItem().toString();
        String sStatu = Statu.getSelectedItem().toString();
        String sXRayYesNo = XRayYesNo.getSelectedItem().toString();
        int sDilTazminati = Integer.parseInt(mEdtTextDilTazminati.getText().toString());
        int sTatilGunu = Integer.parseInt(mEdtTextTatilGunleri.getText().toString());
        int snoShow = Integer.parseInt(mEdtTextnoShow.getText().toString());
        int sRapor = Integer.parseInt(mEdtTextRapor.getText().toString());
        int sUcretsizIzin = Integer.parseInt(mEdtTextUcretsizIzin.getText().toString());
        int sMesaiSaati = Integer.parseInt(mEdtMesaiSaati.getText().toString());









        if(sPersonel.equals("Supervisor"))
            toplam_maas += supervisor;
        else if (sPersonel.equals("Takım Lideri"))
            toplam_maas += tl;
        else if (sPersonel.equals("Profiler"))
            toplam_maas += profiler;
        else if (sPersonel.equals("Airsider"))
            toplam_maas += airsider;
        else if (sPersonel.equals("Ofis görevlisi"))
            toplam_maas += ofis;
        else if (sPersonel.equals("Şoför"))
            toplam_maas += sofor;

        if(sStatu.equals("Standart Altı"))
            toplam_maas -= standart_alti;
        else if (sStatu.equals("Standart"))
            toplam_maas += standart;
        else if (sStatu.equals("Standart Üstü"))
            toplam_maas += standart_ustu;
        else if (sStatu.equals("Uzman"))
            toplam_maas += uzman;

        if(sXRayYesNo.equals("Evet"))
            toplam_maas += xRay;
        else if (sXRayYesNo.equals("Hayır"))
            toplam_maas += 0;

        toplam_maas += (sDilTazminati * dil_tazminati);
        toplam_maas += (sTatilGunu * tatil_gunleri);
        toplam_maas -= (snoShow * noShow);
        toplam_maas += (sMesaiSaati * mesaiSaati);

        if (sRapor <2)
            toplam_maas -= (sRapor * Rapor);
        else if (sRapor >= 2)
            toplam_maas -= (2 * Rapor);

        toplam_maas -= (sUcretsizIzin * ucretsizIzin);







    }

}