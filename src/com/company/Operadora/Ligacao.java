package com.company.Operadora;

import java.sql.Time;
import java.time.LocalTime;

public class Ligacao {
  private int numero;
  private LocalTime inicio;
  private LocalTime fim;

  public Ligacao(int numero, LocalTime inicio, LocalTime fim) {
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

  public LocalTime getInicio() {
    return inicio;
  }

  public void setInicio(LocalTime inicio) {
    this.inicio = inicio;
  }

  public LocalTime getFim() {
    return fim;
  }

  public void setFim(LocalTime fim) {
    this.fim = fim;
  }

  //endregion

}
