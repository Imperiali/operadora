package com.company.DAO;

import com.company.Operadora.Cliente;

import java.util.ArrayList;

public interface DAOInferface {
    void adicionar(Cliente cliente);
    boolean remover(int num);
    void alterar(int indice, Cliente cliente);
    String mostrar();
    ArrayList<Object> listarTodos();
}
