package com.company.DAO;

import com.company.Operadora.Cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public abstract class Arquivo {
  private String nomeArq;

  public Arquivo(String nomeArq) {
    this.nomeArq = nomeArq;
  }

  public Formatter abreArquivoGravar(){
    Formatter saida = null;

    try {
      saida = new Formatter(new File(nomeArq));
    } catch (FileNotFoundException e) {
      System.out.println("Erro :" + e);
      e.printStackTrace();
    }
    return saida;
  }

  public void gravaArquivo( Formatter saida, ArrayList<Cliente> clientes){

    for (Cliente cliente: clientes){
      try {
          saida.format("" + cliente.getNome() + ";" + cliente.getNumero() + ";" + cliente.getPlano() + ";" + cliente.getCreditos() + "\n");
      }catch (Exception e){
        System.out.println("Erro :" + e);
      }
    }
  }

  public void leArquivo(Scanner entrada, ArrayList<Cliente> clientes){
    String linha;
    String[] campos;

    try {
      while (entrada.hasNext()){
        linha = entrada.nextLine();
        campos = linha.split(";");
        Cliente aluno = new Cliente(campos[0],
                Integer.parseInt(campos[1]),
                campos[2],
                Float.parseFloat(campos[3]));
        clientes.add(aluno);
      }
    }catch (Exception e){
      System.out.println("Erro: " + e);
    }
  }

  public void fechaArquivo(Scanner entrada){
    if (entrada != null){
      entrada.close();
    }
  }

  public void fechaArquivo(Formatter saida){
    if (saida != null){
      saida.close();
    }
  }

  public Scanner abreArquivo() {
    Scanner entrada = null;

    try {
      entrada = new Scanner(new File(nomeArq));
    } catch (FileNotFoundException e) {
      System.out.println("Erro: Abertura de arquivo");
      e.printStackTrace();
    }

    return entrada;
  }
}