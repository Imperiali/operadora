package com.company.Operadora;

public class Cliente {
    private String nome;
    private int numero;
    private String plano;
    private int credito;

    public Cliente(String nome, int numero, String plano, int credito) {
        this.nome = nome;
        this.numero = numero;
        this.plano = plano;
        this.credito = credito;
    }

    //region Getters and Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    //endregion
}
