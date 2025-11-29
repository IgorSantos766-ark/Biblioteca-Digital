package database;

import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {

        Connection conn = ConexaoBanco.getConnection();

        if (conn != null) {
            System.out.println("Conexão ao MySQL bem-sucedida!");
        } else {
            System.out.println("Falha na conexão.");
        }

        ConexaoBanco.fechar();
    }
}
