package br.com.alura.ecommerce.database;

import java.io.IOException;
import java.sql.*;

public class LocalDatabase {

    private final Connection connection;
    public LocalDatabase(String name) throws SQLException {
        String url = "jdbc:sqlite:service-users/target/" +name+ ".db";
        this.connection = DriverManager.getConnection(url);
    }


    // yes this is way too generic
    // according to your database tool avoid injection
    public void createIfNotExists(String sql) {
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(String statement, String ... params) throws SQLException {
       prepare(statement, params).execute();

    }

    public ResultSet query(String query, String... params) throws SQLException {
        return prepare(query, params).executeQuery();
    }

    private PreparedStatement prepare(String statement, String[] params) throws SQLException {
        var prepareStatement = connection.prepareStatement(statement);
        for(int i = 0; i< params.length; i++) {
            prepareStatement.setString(i+1, params[i]);
        }
        return prepareStatement;
    }

    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
}
