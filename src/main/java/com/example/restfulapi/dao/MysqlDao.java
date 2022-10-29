package com.example.restfulapi.dao;

import com.example.restfulapi.model.DatabaseConfig;
import com.example.restfulapi.model.Sushi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlDao {

    public static Connection getConnection(DatabaseConfig config) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://"+config.getHost()+":"+config.getPort()+"/"+config.getDatabase();
            System.out.println("url : "+ url);
            conn = DriverManager.getConnection(url, config.getUserName(), config.getPassword());

        }catch (Exception exception){
            System.out.println(exception);
        }

        return conn;
    }

    public static List<Sushi> getSushiList(Connection connection) throws SQLException {
        List<Sushi> sushis = new ArrayList<>();
        try{
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM  sushi order by id;");
            while (res.next()) {
                int id = res.getInt("id");
                String img_url = res.getString("img_url");
                String name = res.getString("name");
                float price = res.getFloat("price");
                String created_at = res.getString("created_at");

                Sushi sushi = new Sushi();
                sushi.setId(id);
                sushi.setName(name);
                sushi.setCreated_at(created_at);
                sushi.setPrice(price);
                sushi.setImg_url(img_url);

                sushis.add(sushi);
            }
        }
        catch (SQLException s){
            System.out.println("SQL code does not execute.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return sushis;
    }

    public static String addSushiList(Connection connection, List<Sushi> sushis) throws SQLException {
        String msg = "insertion is successful.";
        System.out.println("len of sushi "+ sushis.size());
        try{
            Statement st = connection.createStatement();
            connection.setAutoCommit(false);
            String query= "";
            for (int i=0; i<sushis.size(); i++){
                query = "insert into sushi(id, name, img_url, created_at, price) value(";
                query += sushis.get(i).getId()+",'"+sushis.get(i).getName()+"','"+sushis.get(i).getImg_url()+"','"+sushis.get(i).getCreated_at()+"',"+sushis.get(i).getPrice()+"); ";
                st.addBatch(query);
                if((i+1)%50 == 0 || i+1 == sushis.size()){
                    st.executeBatch();
                    connection.commit();
                }
            }
        }
        catch (SQLException s){
            msg = "insertion is fail.";
            System.out.println("SQL code does not execute."+s);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return msg;
    }
}
