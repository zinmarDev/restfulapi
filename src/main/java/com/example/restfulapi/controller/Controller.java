package com.example.restfulapi.controller;


import com.example.restfulapi.dao.MysqlDao;
import com.example.restfulapi.model.DatabaseConfig;
import com.example.restfulapi.model.Sushi;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin
//@org.springframework.stereotype.Controller  // to view html
@RestController //to catch api
@RequestMapping("/")

public class Controller {

    @GetMapping("sushis")
    public List<Sushi> getSushiList() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        List<Sushi> sushi = new ArrayList<>();
        if(conn != null){
            sushi = MysqlDao.getSushiList(conn);
        }
        return sushi;
    }

    @GetMapping("get/connection")
    public Connection getConnection() throws SQLException, ClassNotFoundException {
//        String message = "";
        DatabaseConfig config = new DatabaseConfig();
        config.setHost("localhost");
        config.setPort("3306");
        config.setUserName("root");
        config.setPassword("");
        config.setDatabase("sushi_db");
        config.setTableName("sushi");

        Connection conn = MysqlDao.getConnection(config);
//        if(conn != null){
//            message = "connection is successful.";
//        }else{
//            message = "cannot connect to the mysql server";
//        }
        return conn;
    }

    @PostMapping("add/sushis")
    public String addSushiList(@RequestBody List<Sushi> sushi_list) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        String msg = "connection is fail";
        if(conn != null){
            msg = MysqlDao.addSushiList(conn, sushi_list);
        }
        return msg;
    }

    @GetMapping("sushi/list")
    public String test(Model model){
        return "sushi_list";
    }

}
