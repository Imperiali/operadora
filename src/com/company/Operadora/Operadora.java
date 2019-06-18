package com.company.Operadora;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;

public class Operadora {
  ArrayList<Cliente> clientes;
  ArrayList<Ligacao> ligacoes;

  public Operadora(ArrayList<Cliente> clientes) {
    this.clientes = clientes;
  }

  public Operadora(ArrayList<Cliente> clientes, ArrayList<Ligacao> ligacoes) {
    this.clientes = clientes;
    this.ligacoes = ligacoes;
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

  public String listarLigacoes() {
    String msg = "";

    msg += "------------------------------\n";

    for(Ligacao ligacao:ligacoes){
        msg += ligacao + "\n";
    }

    if (msg.isEmpty()) {
      msg = "Sem Clientes";
    }

    msg += "------------------------------";

    return msg;
  }

  public String mostrarClietneTop() {
    ArrayList<Cliente> clientes = ordenarPorCredito();
    String msg = "";

    msg += "------------------------------\n";

    if (msg.isEmpty()) {
      msg = "Sem Clientes";
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

    for(Cliente cliente:clientes){
      if (cliente.getCreditos() >= credito){
        msg += cliente + "\n";
      }
    }

    if (msg.isEmpty()) {
      msg = "Sem Clientes";
    }

    msg += "------------------------------";

    return msg;
  }

  public String listarSemCredito() {
    ArrayList<Cliente> clientes = ordenarPorCredito();
    float abaixoDe = 0;
    String msg = "";

    msg += "------------------------------\n";

    for(Cliente cliente:clientes){
      if (cliente.getCreditos() <= abaixoDe){
        msg += cliente + "\n";
      }
    }

    if (msg.isEmpty()) {
      msg = "Sem Clientes";
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
