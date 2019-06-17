package com.company.DAO;

import com.company.Operadora.Cliente;
import com.company.Operadora.Ligacao;
import com.company.Operadora.Operadora;

import java.util.*;

public class DataAccessObject implements DAOInferface{

    private ArrayList<Cliente> clientes;
    private ArrayList<Ligacao> ligacoes;
    private Arquivo cliente;
    private Operadora operadora;

    public DataAccessObject(String nomeArq) {
        cliente = new Arquivo(nomeArq);
        clientes = new ArrayList<>();
        operadora = new Operadora(clientes);
        Scanner entrada = cliente.abreArquivo();
        if (entrada != null){
            cliente.leArquivoCliente(entrada, clientes);
            cliente.fechaArquivo(entrada);
        }
    }

    public DataAccessObject(String nomeArq, String nomeArq2) {
        cliente = new Arquivo(nomeArq);
        this.clientes = new ArrayList<Cliente>();
        Arquivo ligacoes = new Arquivo(nomeArq2);
        this.ligacoes = new ArrayList<Ligacao>();

        Scanner entradaCliente = cliente.abreArquivo();
        if (entradaCliente != null){
            cliente.leArquivoCliente(entradaCliente, this.clientes);
            cliente.fechaArquivo(entradaCliente);
        }

        Scanner entradaLigacao = ligacoes.abreArquivo();
        if (entradaLigacao != null){
            ligacoes.leArquivoLigacao(entradaLigacao, this.ligacoes);
            ligacoes.fechaArquivo(entradaLigacao);
        }
        operadora = new Operadora(this.clientes, this.ligacoes);
    }

    public String geraBoleto(int num){
        return operadora.analisarCusto(clientes.get(num));
    }

    //region Metodos Interface

    @Override
    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
        salvar();
    }

    @Override
    public boolean remover(int indice) {
        if (clientes.isEmpty() || indice < 0) {
            return false;
        } else {
            clientes.remove(indice);
            salvar();
            return true;
        }
    }

    @Override
    public void alterar(int indice, Cliente cliente) {
        clientes.set(indice, cliente);
        salvar();
    }

    @Override
    public String mostrar() {
        String msg = "";
        msg += "------------------------------\n";

        for(Cliente cliente:this.clientes){
            msg += cliente + "\n";
        }

        if (msg.isEmpty()) {
            msg = "Sem Clientes";
        }

        msg += "------------------------------";

        return msg;
    }

    @Override
    public ArrayList<Object> listarTodos() {
        return null;
    }

    // endregion

    public String mostrarClietneTop() {
        return operadora.mostrarClietneTop();
    }

    public String listarPorCreditoAcimaDe(float credito) {
        return operadora.listarPorCreditoAcimaDe(credito);
    }

    public String listarSemCredito() {
        return operadora.listarSemCredito();
    }

    public int pesquisaCliente(int num){
        return operadora.pesquisaCliente(num);
    }

    private void salvar(){
        Formatter saida = cliente.abreArquivoGravar();
        if (saida != null){
            cliente.gravaArquivo(saida, clientes);
            cliente.fechaArquivo(saida);
        }
    }

    //    region Getters and Setters

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    //    endregion
}
