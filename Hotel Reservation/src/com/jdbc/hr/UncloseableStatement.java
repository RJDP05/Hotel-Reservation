package com.jdbc.hr;


import java.sql.Statement;

public class UncloseableStatement implements AutoCloseable {

    private final Statement statement;

    public UncloseableStatement(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }

    @Override
    public void close() {
        // Do nothing to prevent closing
    }

}
