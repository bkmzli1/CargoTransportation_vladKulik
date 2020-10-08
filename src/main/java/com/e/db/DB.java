package com.e.db;

import com.e.Main;
import com.e.util.Decoder;
import com.e.util.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class DB {
    private Connection conn;
    public static final Logger logger = LogManager.getLogger();


    public DB(String fileConn) throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");

        logger.info(Decoder.UTFtoWin1251(fileConn));
        conn = DriverManager.getConnection("jdbc:sqlite:" + fileConn);


    }

    public void start() throws SQLException {
        Statement statmt = conn.createStatement();
        try {
            statmt.execute("create table orders (id TEXT, name varchar(28),dateIn date , dateOut date,description text);");
            statmt.execute("create table name (id TEXT, name varchar(28));");
            statmt.execute("create table date ( id TEXT constraint date_name_id_fk references name (id), dateIn date, dateOut date ); insert into date_dg_tmp(id, dateIn, dateOut) select id, dateIn, dateOut from date; drop table date; alter table date_dg_tmp rename to date;");
            statmt.execute("create table description ( id TEXT constraint description_name_id_fk references name (id), description text ); insert into description_dg_tmp(id, description) select id, description from description; drop table description; alter table description_dg_tmp rename to description;");

        } catch (SQLException e) {
        }
        statmt.close();
    }

    public Connection getConn() {
        return conn;
    }


    public void add(Table t) {
        try {
            String id = t.getId();
            String name = t.getName();
            String dateIn = t.getDateIn();
            String dateOut = t.getDateOut();
            String description = t.getDescription();
            Statement statmt = conn.createStatement();
            statmt.execute("INSERT INTO orders (id, name, dateIn, dateOut, description) VALUES ('" + id + "', '" + name + "', '" + dateIn + "', '" + dateOut + "', '" + description + "')");
            statmt.execute("INSERT INTO description (id , description ) VALUES ('" + id + "','" + description + "' )");
            statmt.execute("INSERT INTO date (id , dateIn  , dateOut ) VALUES('" + id + "','" + dateIn + "','" + dateOut + "' )");
            statmt.execute("INSERT INTO name (id , name) VALUES('" + id + "','" + name + "' )");
        } catch (SQLException sqlException) {

        }
    }

    public ArrayList<Table> getBD() {
        ArrayList<Table> tables = new ArrayList<Table>();
        try {
            Statement statmt = conn.createStatement();
            ResultSet resultSet = statmt.executeQuery("SELECT * FROM orders");
            while (resultSet.next()) {
                tables.add(new Table(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("dateIn"),resultSet.getString("dateOut"),resultSet.getString("description")));
            }
        } catch (Exception e) {

        }


        return tables;
    }
}
