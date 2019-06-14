package com.company.Operadora;

import java.sql.Time;

public class Ligacao {
  private int numero;
  private Time inicio;
  private Time fim;

  public Ligacao(int numero, Time inicio, Time fim) {
    this.numero = numero;
    this.inicio = inicio;
    this.fim = fim;
  }

  @Override
  public String toString() {
    return "Ligacao{" +
            "numero=" + numero +
            ", inicio=" + inicio +
            ", fim=" + fim +
            '}';
  }

  //region Getters and Setters

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public Time getInicio() {
    return inicio;
  }

  public void setInicio(Time inicio) {
    this.inicio = inicio;
  }

  public Time getFim() {
    return fim;
  }

  public void setFim(Time fim) {
    this.fim = fim;
  }

  //endregion

}
