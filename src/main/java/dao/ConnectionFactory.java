package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // --- ATENÇÃO: Configure com suas credenciais e nome do banco ---
    // O seu script não cria o banco, apenas as tabelas. 
    // Certifique-se de que o banco 'ifrs_bancoii' exista.
    private static final String URL = "jdbc:mariadb://localhost:3306/ifrs_bancoii";
    private static final String USER = "ifrs"; // ou seu usuário
    private static final String PASSWORD = "ifrs"; // sua senha

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}

