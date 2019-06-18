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
                case 2: alterarCliente(dao);
                    break;
                case 3: deletarCliente(dao);
                    break;
                case 4: relatorio(dao);
                    break;
            }
            opcao = menu();
        }
    }

    public static void relatorio(DataAccessObject dao){
        DataAccessObject daoLigacoes = new DataAccessObject("clientes.txt", "ligacoes.txt");
        final int FIM = 0;
        int opcao;

        opcao = relatorioMenu();
        while (opcao != FIM) {
            switch (opcao) {
                case 1: listarClientes(dao);
                    break;
                case 2: listarClientesSemCredito(dao);
                    break;
                case 3: listarClientesComMaisDe(dao, leCredito());
                    break;
                case 4: listarClienteTop(dao);
                    break;
                case 5: gerarBoleto(daoLigacoes, dao);
                    break;
            }
            opcao = relatorioMenu();
        }
    }

    public static void gerarBoleto(DataAccessObject daoLigacoes, DataAccessObject daoClientes){
        listarClientes(daoClientes);
        Cliente cliente = daoClientes.retornaCliente(daoLigacoes.pesquisaCliente(leNumero("Digite o numero")));
        int minuto = daoLigacoes.gerarCobranca(cliente.getNumero());

        String cobranca = "Minutos: " + minuto + "\n";

        switch (cliente.getPlano()){
            case "Pré-pago":
                cliente.setCreditos(cliente.getCreditos() - minuto);
                cobranca += cliente.getNome() + " | R$" + minuto + ",00 | Creditos restantes: " +  cliente.getCreditos();
                break;
            case "Pós-pago":
                cobranca += cliente.getNome() + " deverá pagar o valor de R$" + minuto + ",00";
                break;
        }
        System.out.println(cobranca);
    }

    public static int relatorioMenu(){
        int opcao;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("[1] - Listar todos os clientes");
            System.out.println("[2] - Listar todos os clientes sem crédito");
            System.out.println("[3] - Listar todos os clientes com créditos acima de...");
            System.out.println("[4] - Listar conta com mais créditos");
            System.out.println("[5] - Gerar boletos");
            System.out.println("[0] - Sair");
            opcao = leNumero("Entre com uma opção: ");
            if ((opcao < 0) || (opcao > 5)) {
                System.out.println("Erro: Opcao inválida");
            }
        } while ((opcao < 0) || (opcao > 5));

        return opcao;
    }

    public static void listarClienteTop(DataAccessObject dao){
        System.out.println(dao.mostrarClietneTop());
    }
    public static void listarClientesComMaisDe(DataAccessObject dao, float credito){
        System.out.println(dao.listarPorCreditoAcimaDe(credito));
    }

    public static void listarClientesSemCredito(DataAccessObject dao){
        System.out.println(dao.listarSemCredito());
    }

    public static void listarClientes(DataAccessObject dao){
        System.out.println(dao.mostrar());
    }

    public static void adicionarCliente(DataAccessObject dao){
        Cliente cliente = lerCliente();
        if(dao.pesquisaCliente(cliente.getNumero()) != -1){
            System.out.println("Cliente já existe!");
            return;
        }
        dao.adicionar(cliente);
    }

    public static void alterarCliente(DataAccessObject dao){
        listarClientes(dao);

        int numero;
        numero = leNumero("Entre com o numero do cliente");
        int indice = dao.pesquisaCliente(numero);
        if (indice >= 0){
            Cliente cliente = lerClienteSemNumero(numero);
            dao.alterar(indice, cliente);
            System.out.println("Cliente alterado com sucesso");
        } else {
            System.out.println("Cliente não encontrado");
        }
    }

    public static void deletarCliente(DataAccessObject dao){
        listarClientes(dao);

        int numero;
        numero = leNumero("Entre com o numero do cliente");
        int indice = dao.pesquisaCliente(numero);
        if (indice >= 0){
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

    public static Cliente lerClienteSemNumero(int numero){
        String nome = leTexto("Entre com o nome do cliente: ");
        String plano = lePlano();
        float credito;
        if(plano.equals("Pós-pago")){
            credito = 0;
        }else {
            credito = leCredito();
        }
        return new Cliente(nome, numero, plano, credito);
    }

    public static Cliente lerCliente(){
        int numero = leTelefone();
        return lerClienteSemNumero(numero);
    }

    public static String lePlano(){
        int escolha = leNumero("Entre com o plano[1 - Pre/ 2 - Pos]: ");
        String plano = "";

        while (plano.equals("")){
            switch (escolha) {
                case 1:
                    plano = "Pré-pago";
                    break;
                case 2:
                    plano = "Pós-pago";
                    break;
                default:
                    System.out.println("Escolha invalida!");
                    escolha = leNumero("Entre com o plano[1 - Pre/ 2 - Pos]: ");
            }
        }

        return plano;
    }

    public static float leCredito(){
        float credito = (float) leNumero("Entre com os creditos: ");

        while(credito < 0){
            System.out.println("Os creditos não podem ser negativos!");
            credito = (float) leNumero("Entre com os creditos: ");
        }


        return credito;
    }

    public static int leTelefone(){
        int numero = leNumero("Entre com o numero do cliente: ");

        while(String.valueOf(numero).length() != 8){
            System.out.println("Numero precisa ter 8 digitos!");
            numero = leNumero("Entre com o numero do cliente: ");
        }


        return numero;
    }

    public static int menu() {
        int opcao;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("[1] - Incluir");
            System.out.println("[2] - Alterar");
            System.out.println("[3] - Excluir");
            System.out.println("[4] - Relatorios Gerais");
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
