package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";      // coloque seu usuário
    private static final String PASSWORD = "123456"; // coloque sua senha

    private static Connection connection = null;

    // ------------------------------------------------
    // OBTÉM A CONEXÃO
    // ------------------------------------------------
    public static Connection getConexao() {

        try {
            if (connection == null || connection.isClosed()) {

                // Carrega driver do MySQL (às vezes opcional, mas seguro)
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco MySQL!", e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL não encontrado! Adicione o mysql-connector-j ao projeto.", e);
        }

        return connection;
    }

    // ------------------------------------------------
    // FECHA A CONEXÃO
    // ------------------------------------------------
    public static void fechar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
