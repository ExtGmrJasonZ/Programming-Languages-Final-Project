package sample;
import java.sql.*;

public class DBConnect {

    public Connection con;
    public Statement st;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/products_db","root","");

        return connection;
    }

    void insertData( String id , String name , String price , String date , String image){
        String sql = String.format("insert into products(id ,name , price , date , image) VALUES( '%s', '%s' ,'%s','%s','%s')"  , id, name ,price,date , image);
        //System.out.println(sql);
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Data Inserted");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    DBConnect(){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            // Create Connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products_db","root","");
            //"jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12294102","sql12294102","YrBYNJeaNR"
            st = con.createStatement();
        }
        catch (Exception e){
            System.out.println("Error : " + e);
        }
    }

    void deleteRow(String id){

        String sql = String.format("delete from products where id = '%s' " , id);

        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Row Deleted");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    void inserIntoSellTable(String id , String name ,String price , String soldPrice , String revenue){
        String sql = String.format("insert into revenue_table(id ,name , price , sold_price , revenue) VALUES('%s', '%s' ,'%s','%s','%s')"  , id, name ,price,soldPrice , revenue);
        //System.out.println(sql);
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Data Inserted");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


}