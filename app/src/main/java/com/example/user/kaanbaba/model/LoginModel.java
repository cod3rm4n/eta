package com.example.user.kaanbaba.model;

public class LoginModel {
    private String e_posta;
    private String pass;

    public LoginModel(String e_posta, String pass) {
        this.e_posta = e_posta;
        this.pass = pass;
    }

    public String getE_posta() {
        return e_posta;
    }

    public void setE_posta(String e_posta) {
        this.e_posta = e_posta;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
