package com.ccic.config.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description mybatis enum type handler for status
 */
public class EnumHandlerStatus implements TypeHandler<Status> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Status status, JdbcType jdbcType) throws SQLException {
        ps.setString(i, status.getValue());
    }

    @Override
    public Status getResult(ResultSet rs, String columnName) throws SQLException {
        String status = rs.getString(columnName);
        return Status.newValueOf(status);
    }

    @Override
    public Status getResult(ResultSet rs, int columnIndex) throws SQLException {
        String status = rs.getString(columnIndex);
        return Status.newValueOf(status);
    }

    @Override
    public Status getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String status = cs.getString(columnIndex);
        return Status.newValueOf(status);
    }

}
