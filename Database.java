package com.example.OnlineShop;

import java.sql.*;
import java.util.ArrayList;

public final class Database {

    public static void addPersons(Person person) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        String sql=null;
        if (person instanceof Customer) {
             sql = "INSERT INTO customers (firstName, lastName, phoneNumber, role, userName, password, email, money) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else if (person instanceof Seller) {
             sql = "INSERT INTO sellers (firstName, lastName, phoneNumber, role, userName, password, email, money) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        }

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, person.getFirstName());
        statement.setString(2, person.getLastName());
        statement.setString(3, person.getPhoneNumber());
        statement.setString(4, person.getRole());
        statement.setString(5, person.getUserName());
        statement.setString(6, person.getPassword());
        statement.setString(7, person.getEmail());
        statement.setDouble(8, person.getMoney());
        statement.executeUpdate();

        connection.close();

    }

    public static void readPerson(ArrayList<Person>persons) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        Statement statement = connection.createStatement();

            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM customers");
            while (resultSet1.next()){
                String firstName1=resultSet1.getString("firstName");
                String lastName1=resultSet1.getString("lastName");
                String phoneNumber1=resultSet1.getString("phoneNumber");
                String userName1=resultSet1.getString("userName");
                String password1=resultSet1.getString("password");
                String email1=resultSet1.getString("email");
                Double money1=resultSet1.getDouble("money");

                    Person person1=new Customer(firstName1,lastName1,phoneNumber1,userName1,password1,email1);
                    person1.setMoney(money1);
                    persons.add(person1);
            }
        ResultSet resultSet2 = statement.executeQuery("SELECT * FROM sellers");
        while (resultSet2.next()){
            String firstName2=resultSet2.getString("firstName");
            String lastName2=resultSet2.getString("lastName");
            String phoneNumber2=resultSet2.getString("phoneNumber");
            String userName2=resultSet2.getString("userName");
            String password2=resultSet2.getString("password");
            String email2=resultSet2.getString("email");
            Double money2=resultSet2.getDouble("money");

            Person person2=new Seller(firstName2,lastName2,phoneNumber2,userName2,password2,email2);
            person2.setMoney(money2);
            persons.add(person2);
        }

    }





    public static void updatePerson(Person person){

        String url = "jdbc:sqlite:myDataBase.db";
        String sql = null;
        if (person instanceof Customer) {
            sql = "UPDATE costomers SET firstName = ?,lastName = ?,phoneNumber = ?,role = ?,userName = ? ," +
                    "password = ?,email = ?,money = ? WHERE userName = ?";
        }else if (person instanceof Seller){
            sql = "UPDATE sellers SET firstName = ?,lastName = ?,phoneNumber = ?,role = ?,userName = ? ," +
                    "password = ?,email = ?,money = ? WHERE userName = ?";
        }
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the new password value and the username of the row to update
            pstmt.setString(1, person.getFirstName());
            pstmt.setString(2,person.getLastName());
            pstmt.setString(3,person.getPhoneNumber());
            pstmt.setString(4, person.getRole());
            pstmt.setString(5,person.getUserName());
            pstmt.setString(6, person.getPassword());
            pstmt.setString(7,person.getEmail());
            pstmt.setDouble(7,person.getMoney());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    public static void updateProduct(Product product){

                String url = "jdbc:sqlite:myDataBase.db";
                String sql = "UPDATE products SET name = ?,quantity = ?,price = ?,score = ?,addressImage = ? ," +
                        "category = ?,description = ?,numberInCart = ?,userNameSeller = ?,wareHouse = ?,countScore = ? WHERE codeOfProduct = ?";
                try (Connection conn = DriverManager.getConnection(url);
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    // Set the new password value and the username of the row to update
                    pstmt.setString(1, product.getName());
                    pstmt.setInt(2,product.getQuantity());
                    pstmt.setDouble(3,product.getPrice());
                    pstmt.setDouble(4, product.getScore());
                    pstmt.setString(5,product.getAddressImage());
                    pstmt.setString(6,product.getCategory());
                    pstmt.setString(7, product.getDescription());
                    pstmt.setInt(8,product.getNumberInCart());
                    pstmt.setString(9,product.getUserNameSeller());
                    pstmt.setString(10, product.getWareHouse());
                    pstmt.setInt(11,product.getCountScore());
                    pstmt.setString(12,product.getCodeOfProduct());

                    pstmt.executeUpdate();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
    }

    public static void addProduct(Product product) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        String sql=null;

            sql = "INSERT INTO products (name, quantity, price, score, addressImage, category, " +
                    "description, numberInCart,userNameSeller,wareHouse,codeOfProduct,countScore,timeAdding) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, product.getName());
        statement.setInt(2, product.getQuantity());
        statement.setDouble(3, product.getPrice());
        statement.setDouble(4, product.getScore());
        statement.setString(5, product.getAddressImage());
        statement.setString(6, product.getCategory());
        statement.setString(7, product.getDescription());
        statement.setInt(8, product.getNumberInCart());
        statement.setString(9, product.getUserNameSeller());
        statement.setString(10, product.getWareHouse());
        statement.setString(11, product.getCodeOfProduct());
        statement.setInt(12,product.getCountScore());
        statement.setLong(13,product.getTimeAdding());
        statement.executeUpdate();

        connection.close();

    }


    public static void readProduct(ArrayList<Product>products) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
        while (resultSet.next()){
            String name=resultSet.getString("name");
            int quantity=resultSet.getInt("quantity");
            double price=resultSet.getDouble("price");
            double score=resultSet.getDouble("score");
            String addressImage=resultSet.getString("addressImage");
            String category=resultSet.getString("category");
            String description=resultSet.getString("description");
            int numberInCart=resultSet.getInt("numberInCart");
            String userNameSeller=resultSet.getString("userNameSeller");
            String wareHouse=resultSet.getString("wareHouse");
            String codeOfProduct=resultSet.getString("codeOfProduct");

           Product product=new Product(name,quantity,price,addressImage,category,description,userNameSeller);
           product.setWareHouse(wareHouse);
           product.setNumberInCart(numberInCart);
           product.setCodeOfProduct(codeOfProduct);
           product.setScore(score);
           products.add(product);
        }
    }


    public static void addTransaction(transaction transaction) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        String sql=null;

        sql = "INSERT INTO transactions (customer, seller, codeOfProduct, date, code, address, " +
                "numberProduct, phoneNumber,price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, transaction.getCustomer());
        statement.setString(2, transaction.getSeller());
        statement.setString(3, transaction.getCodeOfProduct());
        statement.setString(4, transaction.getDate());
        statement.setString(5, transaction.getCode());
        statement.setString(6, transaction.getAddress());
        statement.setInt(7, transaction.getNumberProduct());
        statement.setString(8, transaction.getPhoneNumber());
        statement.setDouble(9, transaction.getPrice());
        statement.executeUpdate();

        connection.close();

    }


    public static void readTransaction(ArrayList<transaction>transactions) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions");
        while (resultSet.next()){
            String customer=resultSet.getString("customer");
            String seller=resultSet.getString("seller");
            String codeOfProduct=resultSet.getString("codeOfProduct");
            String date=resultSet.getString("date");
            String code=resultSet.getString("code");
            int numberProduct=resultSet.getInt("numberProduct");
            String phoneNumber=resultSet.getString("phoneNumber");
            String address=resultSet.getString("address");
            double price=resultSet.getDouble("price");

          transaction transaction=new transaction(customer,seller,codeOfProduct,code,address,date,numberProduct,phoneNumber,price);
          transactions.add(transaction);

        }
    }



    public static void addMessage(message message) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        String sql=null;

        sql = "INSERT INTO messages (userSender, roleSender, text, read, time) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, message.getUserSender());
        statement.setInt(2, message.getRoleSender());
        statement.setString(3, message.getText());
        statement.setInt(4, message.getRead());
        statement.setInt(5, message.getTime());
        statement.executeUpdate();

        connection.close();

    }


    public static void readMessage(ArrayList<message>messages) throws SQLException {

        long timeNow=System.currentTimeMillis();
        int x = (int) (timeNow/1000000-605);

        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        Statement statement = connection.createStatement();

        String sql="DELETE FROM messages WHERE  time < "+x+";";

        statement.executeUpdate(sql);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM messages");
        while (resultSet.next()){
            String userSender=resultSet.getString("userSender");
            int roleSender=resultSet.getInt("roleSender");
            String text=resultSet.getString("text");
            int read=resultSet.getInt("read");
            int time=resultSet.getInt("time");

            message message=new message(userSender,roleSender,text);
            message.setTime(time);
            message.setRead(read);
            messages.add(message);

        }

    }



    public static void updateMessage(message message){
        String url = "jdbc:sqlite:myDataBase.db";

        String sql = "UPDATE messages SET read = ?  WHERE userSender = ? AND roleSender = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the new password value and the username of the row to update
            pstmt.setInt(1,message.getRead());
            pstmt.setString(2,message.getUserSender());
            pstmt.setInt(3,message.getRoleSender());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }





    public static void addComment(Comment comment) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        String sql=null;

        sql = "INSERT INTO comments (userName, codeOfProduct, textComment, bought) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, comment.getUserName());
        statement.setString(2, comment.getCodeOfProduct());
        statement.setString(3, comment.getTextComment());
        statement.setInt(4, comment.getBought());
        statement.executeUpdate();

        connection.close();


    }



    public static void readComment(ArrayList<Comment>comments) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM comments");
        while (resultSet.next()){
            String userName=resultSet.getString("userName");
            String codeOfProduct=resultSet.getString("codeOfProduct");
            String textComment=resultSet.getString("textComment");
            int bought=resultSet.getInt("bought");

            Comment comment=new Comment(userName,textComment,codeOfProduct,bought);
            comments.add(comment);


        }

    }






















    public static void addAuction(auction auction) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        String sql=null;

        sql = "INSERT INTO auctions (codeOfProduct, currentCustomer, basicPrice, maxPrice, count,time,timeStart) VALUES (?, ?, ?, ?, ?,?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, auction.getCodeOfProduct());
        statement.setString(2, auction.getCurrentCustomer());
        statement.setInt(3, auction.getBasicPrice());
        statement.setInt(4, auction.getMaxPrice());
        statement.setInt(5, auction.getCount());
        statement.setInt(6, auction.getTime());
        statement.setInt(7, auction.getTimeStart());
        statement.executeUpdate();

        connection.close();

    }


    public static void readAuctions(ArrayList<auction>auctions) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM auctions");
        while (resultSet.next()){

            String codeOfProduct=resultSet.getString("codeOfProduct");
            String currentCustomer=resultSet.getString("currentCustomer");
            int basicPrice=resultSet.getInt("basicPrice");
            int maxPrice=resultSet.getInt("maxPrice");
            int count=resultSet.getInt("count");
            int time=resultSet.getInt("time");
            int timeStart=resultSet.getInt("timeStart");

            auction auction=new auction(codeOfProduct,basicPrice,count,time);
            auction.setMaxPrice(maxPrice);
            auction.setCurrentCustomer(currentCustomer);
            auction.setTimeStart(timeStart);
            auctions.add(auction);

        }

    }

    public static void deleteAuction(auction auction) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");

        PreparedStatement stmt = connection.prepareStatement("DELETE FROM auctions WHERE codeOfProduct = ?");
        stmt.setString(1, auction.getCodeOfProduct());
        stmt.executeUpdate();

    }



    public static void updateAuction(auction auction){
        String url = "jdbc:sqlite:myDataBase.db";

        String sql = "UPDATE auctions SET currentCustomer = ?,maxPrice = ?  WHERE codeOfProduct = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the new password value and the username of the row to update
            pstmt.setString(1,auction.getCurrentCustomer());
            pstmt.setInt(2,auction.getMaxPrice());
            pstmt.setString(3,auction.getCodeOfProduct());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }











    public static void addPoint(point point) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        String sql=null;

        sql = "INSERT INTO points (userName, codeOfProduct) VALUES ( ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, point.getUserName());
        statement.setString(2, point.getCodeOfProduct());
        statement.executeUpdate();

        connection.close();


    }



    public static void readPoints(ArrayList<point>points) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM points");
        while (resultSet.next()){
            String userName=resultSet.getString("userName");
            String codeOfProduct=resultSet.getString("codeOfProduct");

            point point=new point(userName,codeOfProduct);
            points.add(point);

        }

    }



























//    public static void main(String[] args) throws SQLException {
//
//        Connection connection = DriverManager.getConnection("jdbc:sqlite:myDataBase.db");
//
//        Statement statement = connection.createStatement();
//
//        statement.execute("CREATE TABLE myTable(name,pass)");
//
//        statement.execute("INSERT INTO myTable(name,pass) VALUES('sina','1234')");
//
//        statement.executeUpdate("DELETE FROM customers WHERE userName='sina'");
//
//
//        statement.executeUpdate("DELETE FROM customers WHERE userName IS NOT NULL ");
//
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM myTable");
//
//        while (resultSet.next()){
//            System.out.println(resultSet.getString("name"));
//        }
//    }

}
