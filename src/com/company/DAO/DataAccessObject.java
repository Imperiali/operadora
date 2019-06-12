package com.company.DAO;

import com.company.Operadora.Cliente;

import java.util.*;

public class DataAccessObject extends Arquivo implements DAOInferface{
    private ArrayList<Cliente> clientes;

    public DataAccessObject(String nomeArq) {
        super(nomeArq);
        clientes = new ArrayList<>();
        Scanner entrada = abreArquivo();
        if (entrada != null){
            leArquivo(entrada, clientes);
            fechaArquivo(entrada);
        }
    }

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
    public String listar() {
        String msg = "";
        msg += "------------------------------\n";

        if (msg.isEmpty()) {
            msg = "Sem Clientes";
        }
        for(Cliente cliente:this.clientes){
            msg += cliente + "\n";
        }

        msg += "------------------------------";

        return msg;
    }

    private String listar(List clientes) {
        String msg = "";
        msg += "------------------------------\n";

        if (msg.isEmpty()) {
            msg = "Sem Clientes";
        }
        for(Object cliente:clientes){
            msg += cliente + "\n";
        }

        msg += "------------------------------";

        return msg;
    }

    public String listarPorCredito(){
        List<Cliente> clientesOrdenados = new ArrayList<>();
        this.clientes.sort((o1, o2) -> {
            if (o2.getCreditos() <= o1.getCreditos()) return -1;
            else return 1;
        });
        clientesOrdenados = this.clientes;
        return listar(clientesOrdenados);
    }

    private void salvar(){
        Formatter saida = abreArquivoGravar();
        if (saida != null){
            gravaArquivo(saida, clientes);
            fechaArquivo(saida);
        }
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

    //    region Getters and Setters

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    //    endregion
}
