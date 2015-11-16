package com.example.saravia.proyectogeolocalizacion;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Richard on 16/11/2015.
 */
public class Test {
    public void main(){
        String test="richard";
        try{
            Utilitarios.SHA1(test);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
