package com.company.Operadora;

import java.util.ArrayList;
import java.util.Collections;

public class Operadora {
  ArrayList<Cliente> clientes;
  ArrayList<Ligacao> ligacoes;

  public Operadora() {
    this.clientes = new ArrayList<>();
    this.ligacoes = new ArrayList<>();
  }

  //region Manipula Cliente

  public void addCliente(Cliente cliente){
    this.clientes.add(cliente);
  }

  public void removeCliente(int indice){
    this.clientes.remove(indice);
  }

  public void updateCliente(int indice, Cliente cliente){
    this.clientes.set(indice, cliente);
  }

  //endregion

  public String fazCobranca(Cliente cliente){
    int custo = analisarCusto(cliente);
    String msg = "";

    msg += "------------------------------\n";

    switch (cliente.getPlano()){
      case "Pré-pago":
          cliente.setCreditos(cliente.getCreditos() - custo);
          msg += cliente.getNome() + " | R$" + custo + ",00 | Creditos restantes: " +  cliente.getCreditos();
        break;
      case "Pós-pago":
          cliente.setCreditos(cliente.getCreditos() + custo);
          msg += cliente.getNome() + " deverá pagar o valor de R$" + custo + ",00";
        break;
    }

    msg += "\n------------------------------\n";

    updateCliente(pesquisaCliente(cliente.getNumero()), cliente);
    return msg;
  }

  public int analisarCusto(Cliente cliente){
    int hora = 0;
    int minuto = 0;

    for(Ligacao ligacao:ligacoes){
      if(cliente.getNumero() == ligacao.getNumero()){
        hora += ligacao.getInicio().getHour() - ligacao.getFim().getHour();
        minuto += ligacao.getInicio().getMinute() - ligacao.getFim().getMinute();
      }
    }

    minuto += (hora * 60);
    minuto *= -1;

    return minuto;
  }

  public String mostrarClienteTop() {
    ArrayList<Cliente> clientes = ordenarPorCredito();
    String msg = "";

    msg += "------------------------------\n";

    if (clientes.isEmpty()) {
      msg = "Sem Clientes\n";
    }else {
      msg += clientes.get(clientes.size() -1) + "\n";
    }

    msg += "------------------------------";

    return msg;
  }

  public String listarPorCreditoAcimaDe(float credito) {
    ArrayList<Cliente> clientes = ordenarPorCredito();
    String msg = "";

    msg += "------------------------------\n";

    if (clientes.isEmpty()) {
      msg = "Sem Clientes\n";
    }else {
      for (Cliente cliente : clientes) {
        if (cliente.getCreditos() >= credito) {
          msg += cliente + "\n";
        }
      }
    }

    msg += "------------------------------";

    return msg;
  }

  public String listarSemCredito() {
    ArrayList<Cliente> clientes = ordenarPorCredito();
    float abaixoDe = 0;
    String msg = "";

    msg += "------------------------------\n";

    if (clientes.isEmpty()) {
      msg = "Sem Clientes\n";
    }else {
      for (Cliente cliente : clientes) {
        if (cliente.getCreditos() <= abaixoDe) {
          msg += cliente + "\n";
        }
      }
    }

    msg += "------------------------------";

    return msg;
  }

  private ArrayList<Cliente> ordenarPorCredito(){
    ArrayList<Cliente> clientesOrdenados = clientes;

    Collections.sort(clientesOrdenados);

    return clientesOrdenados;
  }

  public int pesquisaCliente(int num){
    int pos = -1;
    for (int i = 0; i < clientes.size(); i++){
      if(clientes.get(i).getNumero() == num){
        pos = i;
        break;
      }
    }
    return pos;
  }

  //region Getters and Setters

  public ArrayList<Cliente> getClientes() {
    return clientes;
  }

  public void setClientes(ArrayList<Cliente> clientes) {
    this.clientes = clientes;
  }

  public ArrayList<Ligacao> getLigacoes() {
    return ligacoes;
  }

  public void setLigacoes(ArrayList<Ligacao> ligacoes) {
    this.ligacoes = ligacoes;
  }

  //endregion

}
