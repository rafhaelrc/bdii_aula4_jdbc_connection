// src/main/java/com/example/Main.java
package application;

import dao.*;
import model.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Instancia todos os DAOs
        ClienteDAO clienteDAO = new ClienteDAO();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        VendaDAO vendaDAO = new VendaDAO();

        // --- 1. PREPARAÇÃO: Criando dados base ---
        System.out.println("--- Criando dados base ---");
        Fornecedor fornecedor = fornecedorDAO.salvar(new Fornecedor("F01", "Tech Distribuidora", "Tech LTDA", "5199998888"));
        Produto produtoA = produtoDAO.salvar(new Produto("P01", "Mouse Gamer RGB", new BigDecimal("150.00"), "A", fornecedor.getId()));
        Produto produtoB = produtoDAO.salvar(new Produto("P02", "Teclado Mecânico", new BigDecimal("350.00"), "A", fornecedor.getId()));

        // Reutilizando o cliente do exemplo anterior ou criando um novo
        Cliente cliente = new Cliente();
        cliente.setId(1); // Supondo que o cliente com ID 1 já exista. Busque-o ou crie-o.

        // --- 2. MONTAGEM DA VENDA ---
        System.out.println("\n--- Montando a Venda ---");
        Venda novaVenda = new Venda();
        novaVenda.setCliente(cliente);
        novaVenda.setDataVenda(LocalDate.now());

        // Adicionando itens
        novaVenda.adicionarItem(new ItemVenda(produtoA, 2)); // 2 mouses
        novaVenda.adicionarItem(new ItemVenda(produtoB, 1)); // 1 teclado

        // Calculando o total
        BigDecimal total = novaVenda.getItens().stream()
                .map(item -> item.getValorUnitario().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        novaVenda.setTotalVenda(total);

        System.out.println("Total da venda calculado: " + total);

        // --- 3. PERSISTÊNCIA TRANSACIONAL DA VENDA ---
        System.out.println("\n--- Salvando a Venda (operação transacional) ---");
        vendaDAO.salvar(novaVenda);
    }
}