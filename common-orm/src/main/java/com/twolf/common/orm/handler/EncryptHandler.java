package com.twolf.common.orm.handler;

import com.twolf.common.core.util.AESUtil;
import com.twolf.common.core.util.SpringContextUtil;
import com.twolf.common.core.util.Tools;
import com.twolf.common.orm.config.AesConfig;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 配置加解密
 * @Author twolf
 * @Date 2024/11/13
 */
public class EncryptHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (Tools.isEmpty(parameter)) {
            ps.setString(i, null);
            return;
        }
        //加密
        AesConfig aesConfig = SpringContextUtil.getBean(AesConfig.class);
        String encrypted = AESUtil.encryptHex(parameter, aesConfig.getSecret());
        ps.setString(i, encrypted);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return decrypt(rs.getString(columnName));
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return decrypt(rs.getString(columnIndex));
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return decrypt(cs.getString(columnIndex));
    }

    /**
     * 数据解密
     * @param encryptedText 需要解密的文本
     * @author twolf
     * @date 2024/11/19 09:35
     **/
    private String decrypt(String encryptedText) {
        if (Tools.isEmpty(encryptedText)) {
            return encryptedText;
        }
        //加密
        AesConfig aesConfig = SpringContextUtil.getBean(AesConfig.class);
        return AESUtil.decryptHex(encryptedText, aesConfig.getSecret());
    }
}
