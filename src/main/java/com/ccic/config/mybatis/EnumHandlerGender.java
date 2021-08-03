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
public class EnumHandlerGender implements TypeHandler<Gender> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Gender gender, JdbcType jdbcType) throws SQLException {
        ps.setString(i, gender.getValue());
    }

    @Override
    public Gender getResult(ResultSet rs, String columnName) throws SQLException {
        String gender = rs.getString(columnName);
        return Gender.newValueOf(gender);
    }

    @Override
    public Gender getResult(ResultSet rs, int columnIndex) throws SQLException {
        String gender = rs.getString(columnIndex);
        return Gender.newValueOf(gender);
    }

    @Override
    public Gender getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String gender = cs.getString(columnIndex);
        return Gender.newValueOf(gender);
    }

}
