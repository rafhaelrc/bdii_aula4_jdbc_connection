package dao;

import model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    /**
     * INSERT: Insere um novo cliente no banco de dados.
     * O ID é gerado automaticamente pelo banco (auto_increment).
     */
    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO comclien (c_codiclien, c_nomeclien, c_razaclien, d_dataclien, c_cnpjclien, " +
                "c_foneclien, c_cidaclien, c_estaclien) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getCodigo());
            pstmt.setString(2, cliente.getNome());
            pstmt.setString(3, cliente.getRazaoSocial());
            pstmt.setDate(4, Date.valueOf(cliente.getDataCadastro())); // Conversão de LocalDate para java.sql.Date
            pstmt.setString(5, cliente.getCnpj());
            pstmt.setString(6, cliente.getTelefone());
            pstmt.setString(7, cliente.getCidade());
            pstmt.setString(8, cliente.getEstado());

            pstmt.executeUpdate();
            System.out.println("Cliente '" + cliente.getNome() + "' inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    /**
     * UPDATE: Atualiza os dados de um cliente existente com base no seu ID.
     */
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE comclien SET c_nomeclien = ?, c_foneclien = ?, c_cidaclien = ? WHERE n_numeclien = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getTelefone());
            pstmt.setString(3, cliente.getCidade());
            pstmt.setInt(4, cliente.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Cliente ID " + cliente.getId() + " atualizado com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o ID " + cliente.getId());
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    /**
     * DELETE: Remove um cliente do banco de dados com base no seu ID.
     */
    public void deletar(int id) {
        String sql = "DELETE FROM comclien WHERE n_numeclien = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Cliente ID " + id + " deletado com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
        }
    }

    /**
     * SELECT (Helper): Lista todos os clientes para podermos ver os resultados.
     */
    public List<Cliente> listarTodos() {
        String sql = "SELECT * FROM comclien";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("n_numeclien"));
                cliente.setCodigo(rs.getString("c_codiclien"));
                cliente.setNome(rs.getString("c_nomeclien"));
                cliente.setRazaoSocial(rs.getString("c_razaclien"));
                cliente.setDataCadastro(rs.getDate("d_dataclien").toLocalDate()); // Conversão de java.sql.Date para LocalDate
                cliente.setCnpj(rs.getString("c_cnpjclien"));
                cliente.setTelefone(rs.getString("c_foneclien"));
                cliente.setCidade(rs.getString("c_cidaclien"));
                cliente.setEstado(rs.getString("c_estaclien"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }
}