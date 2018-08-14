package com.example.dmorales.evaluacion2018.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmorales.evaluacion2018.NumericKeyBoardTransformationMethod;
import com.example.dmorales.evaluacion2018.R;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.Nacional;
import com.example.dmorales.evaluacion2018.modelo.Registrado;

import java.io.IOException;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngresoLocalFragment extends Fragment {

    ImageView btnBuscar;
    EditText edtDni;

    CardView cvNoregistrado;
    CardView cvYaregistrado;
    CardView cvRegistro;
    CardView cvError;

    TextView txtErrorCargo;
    TextView txtErrorSede;
    TextView txtErrorLocal;

    TextView txtRegistroCargo;
    TextView txtRegistroDni;
    TextView txtRegistroNombres;
    TextView txtRegistroSede;
    TextView txtRegistroLocal;
    TextView txtRegistroAula;
    TextView txtRegistroBungalow;

    String sede;
    Context context;



    public IngresoLocalFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public IngresoLocalFragment(String sede, Context context) {
        this.sede = sede;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ingresolocal, container, false);
        btnBuscar = (ImageView) rootView.findViewById(R.id.ingresolocal_btnBuscar);
        edtDni = (EditText) rootView.findViewById(R.id.ingresolocal_edtDni);

        cvError = (CardView) rootView.findViewById(R.id.ingresolocal_cvError);
        cvNoregistrado = (CardView) rootView.findViewById(R.id.ingresolocal_cvNoRegistrado);
        cvRegistro = (CardView) rootView.findViewById(R.id.ingresolocal_cvRegistro);
        cvYaregistrado = (CardView) rootView.findViewById(R.id.ingresolocal_cvYaRegistrado);

        txtErrorCargo = (TextView) rootView.findViewById(R.id.ingresolocal_error_txtCargo);
        txtErrorLocal = (TextView) rootView.findViewById(R.id.ingresolocal_error_txtLocal);
        txtErrorSede = (TextView) rootView.findViewById(R.id.ingresolocal_error_txtSede);

        txtRegistroCargo = (TextView) rootView.findViewById(R.id.ingresolocal_txtCargo);
        txtRegistroDni = (TextView) rootView.findViewById(R.id.ingresolocal_txtDni);
        txtRegistroNombres = (TextView) rootView.findViewById(R.id.ingresolocal_txtNombres);
        txtRegistroSede = (TextView) rootView.findViewById(R.id.ingresolocal_txtSede);
        txtRegistroLocal = (TextView) rootView.findViewById(R.id.ingresolocal_txtLocal);
        txtRegistroAula = (TextView) rootView.findViewById(R.id.ingresolocal_txtAula);
        txtRegistroBungalow = (TextView) rootView.findViewById(R.id.ingresolocal_txtbungalow);


        edtDni.setTransformationMethod(new NumericKeyBoardTransformationMethod());
//        edtDni.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
//                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    ocultarTeclado(edtDni);
//                    btnBuscar.requestFocus();
//                    return true;
//                }
//                return false;
//            }
//        });
        return rootView;
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtDni.requestFocus();
        //EDITTEXT BUSCAR
        edtDni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // edtDni.setText(" ");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(edtDni.getText().length()==8){
                    ocultarTeclado(edtDni);
                    String dni = edtDni.getText().toString();
                    if(!dni.equals("")){
                        if(!buscarDNI(dni)) {
                            cvRegistro.setVisibility(View.GONE);
                            cvYaregistrado.setVisibility(View.GONE);
                            cvError.setVisibility(View.GONE);
                            cvNoregistrado.setVisibility(View.VISIBLE);
                            edtDni.setText("");
                            edtDni.requestFocus();
                        }
                        else{edtDni.setText("");
                            edtDni.requestFocus();}
                    }
                    else {
                        Toast.makeText(context, "Ingrese DNI ", Toast.LENGTH_SHORT).show();
                        edtDni.setText("");
                        edtDni.requestFocus();}
                }


            }
        });
        //BOTON BUSCAR
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ocultarTeclado(edtDni);
                //btnBuscar.requestFocus();
                String dni = edtDni.getText().toString();
                if(!dni.equals("")){
                    if(!buscarDNI(dni)) {
                        cvRegistro.setVisibility(View.GONE);
                        cvYaregistrado.setVisibility(View.GONE);
                        cvError.setVisibility(View.GONE);
                        cvNoregistrado.setVisibility(View.VISIBLE);
                        edtDni.setText("");
                        edtDni.requestFocus();
                    }
                    else{edtDni.setText("");
                        edtDni.requestFocus();}
                }
                else {
                Toast.makeText(context, "Ingrese DNI ", Toast.LENGTH_SHORT).show();edtDni.setText("");
                    edtDni.requestFocus();}
            }
        });
    }

    public boolean buscarDNI(String dni){
        boolean encontrado = false;

        try {
            Data data = new Data(context);
            data.open();
            Nacional nacional = data.getNacional(dni);
            data.close();
            if(nacional != null){
                encontrado = true;
                if(sede.equals(nacional.getSede_region())){
                    data = new Data(context);
                    data.open();
                    Registrado registrado = data.getFechaRegistro(nacional.getCodigo());
                    if(registrado != null){
                        cvError.setVisibility(View.GONE);
                        cvNoregistrado.setVisibility(View.GONE);
                        cvYaregistrado.setVisibility(View.VISIBLE);
                        cvRegistro.setVisibility(View.GONE);
                    }else{
                        cvError.setVisibility(View.GONE);
                        cvNoregistrado.setVisibility(View.GONE);
                        cvYaregistrado.setVisibility(View.GONE);
                        cvRegistro.setVisibility(View.VISIBLE);
                        txtRegistroSede.setText(nacional.getSede_region());
                        txtRegistroNombres.setText(nacional.getNombres());
                        txtRegistroDni.setText(nacional.getCodigo());
                        txtRegistroLocal.setText(nacional.getNom_local());
                        txtRegistroCargo.setText(nacional.getCargo());
                        txtRegistroAula.setText("Aula " + nacional.getAula());
                        txtRegistroBungalow.setText("  Nº Bungalow: " + nacional.getN_bungalow()+"     / Responsable de Bungalow :  "+nacional.getResp_bungalow());
                        Calendar calendario = Calendar.getInstance();
                        int yy = calendario.get(Calendar.YEAR);
                        int mm = calendario.get(Calendar.MONTH)+1;
                        int dd = calendario.get(Calendar.DAY_OF_MONTH);
                        int hora = calendario.get(Calendar.HOUR_OF_DAY);
                        int minuto = calendario.get(Calendar.MINUTE);
                        String estado1 = "1";
                        String estado2 = "0";
                        Registrado registrado1 = new Registrado(dni,nacional.getNivel(),
                                nacional.getCod_sede_reg(),nacional.getCod_sede_prov(),nacional.getCod_sede_distrital(),
                                nacional.getSede_region(),nacional.getSede_provincia(),nacional.getSede_distrital(),
                                nacional.getCod_local(),nacional.getNom_local(),nacional.getAula(),nacional.getCodigo(),nacional.getNombres(),
                                nacional.getId_cargo(),nacional.getCargo(),nacional.getTipo_candidato(),nacional.getN_bungalow(),nacional.getResp_bungalow(),
                                checkDigito(dd),checkDigito(mm),checkDigito(yy),checkDigito(hora),checkDigito(minuto),"","","","","",estado1,estado2,0,0);
//                        Registrado registrado1 = new Registrado(dni,dni,nacional.getSede(),nacional.getId_local(),nacional.getNom_local(),nacional.getAula(),nacional.getNombres(),
//                                checkDigito(dd),checkDigito(mm),checkDigito(yy),checkDigito(hora),checkDigito(minuto),"","","","","",estado1,estado2,0,0);
                        data.insertarFechaRegistro(registrado1);
                        //Toast.makeText(context, "Se Registro Entrada al Local", Toast.LENGTH_SHORT).show();
                    }
                    data.close();
                }else{
                    cvError.setVisibility(View.VISIBLE);
                    cvNoregistrado.setVisibility(View.GONE);
                    cvRegistro.setVisibility(View.GONE);
                    cvYaregistrado.setVisibility(View.GONE);
                    txtErrorSede.setText(nacional.getSede_region());
                    txtErrorLocal.setText(nacional.getNom_local());
                    txtErrorCargo.setText(nacional.getCargo());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encontrado;
    }

    public String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}
