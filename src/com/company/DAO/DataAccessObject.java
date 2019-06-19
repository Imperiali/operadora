package com.company.DAO;

import com.company.Operadora.Cliente;
import com.company.Operadora.Ligacao;
import com.company.Operadora.Operadora;

import java.util.*;

public class DataAccessObject implements DAOInferface{

    private Arquivo cliente;
    private Operadora operadora;

    public DataAccessObject(String nomeArq) {
        cliente = new Arquivo(nomeArq);
        operadora = new Operadora();
        Scanner entrada = cliente.abreArquivo();
        if (entrada != null){
            cliente.leArquivoCliente(entrada, operadora.getClientes());
            cliente.fechaArquivo(entrada);
        }
    }

    public DataAccessObject(String nomeArq, String nomeArq2) {
        cliente = new Arquivo(nomeArq);
        operadora = new Operadora();
        Arquivo ligacoes = new Arquivo(nomeArq2);
        Scanner entradaCliente = cliente.abreArquivo();

        if (entradaCliente != null){
            cliente.leArquivoCliente(entradaCliente, operadora.getClientes());
            cliente.fechaArquivo(entradaCliente);
        }

        Scanner entradaLigacao = ligacoes.abreArquivo();
        if (entradaLigacao != null){
            ligacoes.leArquivoLigacao(entradaLigacao, operadora.getLigacoes());
            ligacoes.fechaArquivo(entradaLigacao);
        }
    }

    public String gerarCobranca(int num){
        Cliente cliente = operadora.getClientes().get(pesquisaCliente(num));
        String msg = operadora.fazCobranca(cliente);
        salvar();

        return msg;
    }

    //region Metodos Interface

    @Override
    public void adicionar(Cliente cliente) {
        operadora.addCliente(cliente);
        salvar();
    }

    @Override
    public boolean remover(int indice) {
        if (operadora.getClientes().isEmpty() || indice < 0) {
            return false;
        } else {
            operadora.removeCliente(indice);
            salvar();
            return true;
        }
    }

    @Override
    public void alterar(int indice, Cliente cliente) {
        operadora.updateCliente(indice, cliente);
        salvar();
    }

    @Override
    public String mostrar() {
        String msg = "";
        msg += "------------------------------\n";

        if(operadora.getClientes().isEmpty()){
            msg += "Sem Clientes";
        }else {
            for(Cliente cliente:operadora.getClientes()){
                msg += cliente + "\n";
            }
        }

        msg += "------------------------------";

        return msg;
    }

    public Cliente retornaCliente(int indice){
        return operadora.getClientes().get(indice);
    }

    // endregion

    public String mostrarClienteTop() {
        return operadora.mostrarClienteTop();
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
            cliente.gravaArquivo(saida, operadora.getClientes());
            cliente.fechaArquivo(saida);
        }
    }

}
