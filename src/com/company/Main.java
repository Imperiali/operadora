package com.company;

import com.company.DAO.DataAccessObject;
import com.company.Operadora.Cliente;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final int FIM = 0;
        DataAccessObject dao = new DataAccessObject("clientes.txt");
        int opcao;

        opcao = menu();
        while (opcao != FIM) {
            switch (opcao) {
                case 1: adicionarCliente(dao);
                    break;
                case 2: deletarCliente(dao);
                    break;
                case 3: alterarCliente(dao);
                    break;
                case 4: listarClientes(dao);
                    break;
            }
            opcao = menu();
        }
    }

    public static void listarClientes(DataAccessObject dao){
        System.out.println(dao.listar());
    }

    public static void adicionarCliente(DataAccessObject dao){
        Cliente cliente = lerCliente();
        dao.adicionar(cliente);
    }

    public static void alterarCliente(DataAccessObject dao){
        dao.listar();

        int numero;
        numero = leNumero("Entre com o numero do cliente");
        int indice = dao.pesquisaCliente(numero);
        if (indice > 0){
            Cliente cliente = lerCliente();
            dao.alterar(indice, cliente);
            System.out.println("Cliente alterado com sucesso");
        } else {
            System.out.println("Cliente não encontrado");
        }
    }

    public static void deletarCliente(DataAccessObject dao){
        dao.listar();

        int numero;
        numero = leNumero("Entre com o numero do cliente");
        int indice = dao.pesquisaCliente(numero);
        if (indice > 0){
            boolean removeu = dao.remover(indice);
            // todo Fazer persistencia
            if(removeu){
                System.out.println("Cliente removido com sucesso");
            } else {
                System.out.println("Cliente não encontrado");
            }
        } else {
            System.out.println("Cliente não encontrado");
        }
    }

    public static Cliente lerCliente(){
        String nome = leTexto("Entre com o nome do cliente: ");
        int numero = leNumero("Entre com o numero do cliente: ");
        String plano = leTexto("Entre com o plano[Pre/Pos]: ");
        float credito = (float) leNumero("Entre com os creditos: ");
        return new Cliente(nome, numero, plano, credito);
    }

    public static int menu() {
        int opcao;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("[1] - Incluir");
            System.out.println("[2] - Alterar");
            System.out.println("[3] - Excluir");
            System.out.println("[4] - Listar");
            System.out.println("[0] - Sair");
            opcao = leNumero("Entre com uma opção: ");
            if ((opcao < 0) || (opcao > 4)) {
                System.out.println("Erro: Opcao inválida");
            }
        } while ((opcao < 0) || (opcao > 4));

        return opcao;
    }

    public static String leTexto(String msg) {
        String nome;
        Scanner input = new Scanner(System.in);

        System.out.println(msg);
        nome = input.nextLine();
        return nome;
    }

    public static int leNumero(String msg) {
        int num = 0;
        boolean ok = false;
        Scanner input = new Scanner(System.in);
        do {
            try {

                System.out.println(msg);
                num = input.nextInt();
                ok = true;
            } catch (Exception e) {
                System.out.println("Erro: Opcao inválida");
                input.nextLine();
            }
        } while (!ok);

        return num;
    }
}
