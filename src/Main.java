import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/javabanco";
        String user ="root";
        String password  = "";

        try{

            Connection conexao = DriverManager.getConnection(url,user,password);

            Statement stmt = conexao.createStatement(); // siginifica separação, deixa pronto a declaração

            System.out.println("Digite o nome: ");
            String nome = sc.nextLine();
            System.out.println("Digite o email: ");
            String email = sc.nextLine();

            //Inserir dados
            String insert = "insert into usuario (nome,email) values (?,?)";
            PreparedStatement psInsert = conexao.prepareStatement(insert);
            psInsert.setString(1,nome);
            psInsert.setString(2,email);
            psInsert.executeUpdate();

            System.out.println("Usuário inserido com sucesso");

            //Listar dados
            ResultSet resultado = stmt.executeQuery("Select * from usuario");

            System.out.println("Lista de usuário");

            while (resultado.next()){
                System.out.printf(" %d - %s - %s \n",resultado.getInt("id"),resultado.getString("nome"),resultado.getString("email"));
            }


            conexao.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        sc.close();
    }
}
