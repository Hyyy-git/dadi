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
public class EnumHandlerLock implements TypeHandler<Lock> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Lock lock, JdbcType jdbcType) throws SQLException {
        ps.setString(i, lock.getValue());
    }

    @Override
    public Lock getResult(ResultSet rs, String columnName) throws SQLException {
        String lock = rs.getString(columnName);
        return Lock.newValueOf(lock);
    }

    @Override
    public Lock getResult(ResultSet rs, int columnIndex) throws SQLException {
        String lock = rs.getString(columnIndex);
        return Lock.newValueOf(lock);
    }

    @Override
    public Lock getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String lock = cs.getString(columnIndex);
        return Lock.newValueOf(lock);
    }

}
