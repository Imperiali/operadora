package com.company.DAO;

public class DataAccessObject implements DAOInferface{
    private String nome;

    public DataAccessObject(String nome) {
        this.nome = nome;
    }

    //    region Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    endregion
}
