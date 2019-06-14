package com.company.Operadora;

public class Cliente {
    private String nome;
    private int numero;
    private String plano;
    private float creditos;

    public Cliente() {
    }

    public Cliente(String nome, int numero, String plano, float creditos) {
        this.nome = nome;
        this.numero = numero;
        this.plano = plano;
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", numero=" + numero +
                ", plano='" + plano + '\'' +
                ", creditos=" + creditos +
                '}';
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

    public float getCreditos() {
        return creditos;
    }

    public void setCreditos(float creditos) {
        this.creditos = creditos;
    }

    //endregion
}
