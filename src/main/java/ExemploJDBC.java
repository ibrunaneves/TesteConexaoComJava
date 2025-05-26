import java.sql.*;

public class ExemploJDBC {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/conexaocomjava";
        String usuario = "root";
        String senha = "12345";

        try (
                Connection conn = DriverManager.getConnection(URL, usuario, senha);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTES")
        ) {
            System.out.println("Conexão com o banco de dados estabelecida!");

            ResultSetMetaData meta = rs.getMetaData();
            int numColunas = meta.getColumnCount();

            // Imprimindo dinamicamente o nome de cada coluna
            for (int i = 1; i <= numColunas; i++) {
                System.out.print(meta.getColumnLabel(i) + "\t"); // Nome real da coluna
            }
            System.out.println();

            while (rs.next()) {

                for (int i = 1; i <= numColunas; i++) {
                    Object valor = rs.getObject(i); // Pega o valor da coluna 
                    System.out.print(valor + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println("Erro de SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro geral: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
