// src/main/java/com/example/Main.java
package application;

import dao.ClienteDAO;
import model.Cliente;
import java.time.LocalDate;
import java.util.List;

public class MainClient {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();

        // 1. EXEMPLO DE INSERT
        System.out.println("--- Inserindo um novo cliente ---");
        Cliente novoCliente = new Cliente();
        novoCliente.setCodigo("CLI001");
        novoCliente.setNome("Empresa Exemplo LTDA");
        novoCliente.setRazaoSocial("Empresa Exemplo de Software LTDA");
        novoCliente.setDataCadastro(LocalDate.now());
        novoCliente.setCnpj("12.345.678/0001-99");
        novoCliente.setTelefone("(11) 98765-4321");
        novoCliente.setCidade("São Paulo");
        novoCliente.setEstado("SP");

        clienteDAO.inserir(novoCliente);

        System.out.println("\n--- Lista de clientes após a inserção ---");
        List<Cliente> clientes = clienteDAO.listarTodos();
        clientes.forEach(System.out::println);

        // Pega o primeiro cliente da lista para os próximos exemplos
        // Em uma aplicação real, você teria um método de busca por ID ou código
        if (!clientes.isEmpty()) {
            // Para garantir que estamos pegando o cliente correto, vamos buscá-lo pelo ID que o banco gerou
            int idDoPrimeiroCliente = clientes.get(0).getId();

            // Usando o método buscarPorId que criamos no ClienteDAO (se ele já existir)
            // Se não, podemos pegar o objeto da lista como antes.
            Cliente clienteParaModificar = clienteDAO.buscarPorId(idDoPrimeiroCliente).orElse(null);

            if (clienteParaModificar != null) {
                System.out.println("\nCliente a ser modificado: " + clienteParaModificar);

                // 2. EXEMPLO DE UPDATE
                System.out.println("\n--- Atualizando o telefone e a cidade do cliente ---");
                clienteParaModificar.setTelefone("(21) 99999-8888");
                clienteParaModificar.setCidade("Rio de Janeiro");
                clienteDAO.atualizar(clienteParaModificar);

                System.out.println("\n--- Lista de clientes após a atualização ---");
                clienteDAO.listarTodos().forEach(System.out::println);

                // 3. EXEMPLO DE DELETE
                System.out.println("\n--- Deletando o cliente ID " + clienteParaModificar.getId() + " ---");
                clienteDAO.deletar(clienteParaModificar.getId());

                System.out.println("\n--- Lista final de clientes ---");
                clienteDAO.listarTodos().forEach(System.out::println);
            }
        } else {
            System.out.println("Nenhum cliente encontrado para testar UPDATE e DELETE.");
        }
    }
}