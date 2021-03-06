package com.ticketsystem.dao;

import com.ticketsystem.model.AccountInfo;
import com.ticketsystem.model.AccountInfoExample.Criteria;
import com.ticketsystem.model.AccountInfoExample.Criterion;
import com.ticketsystem.model.AccountInfoExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class AccountInfoSqlProvider {

    public String countByExample(AccountInfoExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("account_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(AccountInfoExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("account_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(AccountInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("account_info");
        
        if (record.getAccountNo() != null) {
            sql.VALUES("account_no", "#{accountNo,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getContactMobile() != null) {
            sql.VALUES("contact_mobile", "#{contactMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getUseTime() != null) {
            sql.VALUES("use_time", "#{useTime,jdbcType=VARCHAR}");
        }
        
        if (record.getEncryptstr() != null) {
            sql.VALUES("encryptStr", "#{encryptstr,jdbcType=VARCHAR}");
        }
        
        if (record.getSession() != null) {
            sql.VALUES("session", "#{session,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(AccountInfoExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("account_no");
        } else {
            sql.SELECT("account_no");
        }
        sql.SELECT("name");
        sql.SELECT("password");
        sql.SELECT("contact_mobile");
        sql.SELECT("use_time");
        sql.SELECT("encryptStr");
        sql.SELECT("session");
        sql.FROM("account_info");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        AccountInfo record = (AccountInfo) parameter.get("record");
        AccountInfoExample example = (AccountInfoExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("account_info");
        
        if (record.getAccountNo() != null) {
            sql.SET("account_no = #{record.accountNo,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{record.password,jdbcType=VARCHAR}");
        }
        
        if (record.getContactMobile() != null) {
            sql.SET("contact_mobile = #{record.contactMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getUseTime() != null) {
            sql.SET("use_time = #{record.useTime,jdbcType=VARCHAR}");
        }
        
        if (record.getEncryptstr() != null) {
            sql.SET("encryptStr = #{record.encryptstr,jdbcType=VARCHAR}");
        }
        
        if (record.getSession() != null) {
            sql.SET("session = #{record.session,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("account_info");
        
        sql.SET("account_no = #{record.accountNo,jdbcType=VARCHAR}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("password = #{record.password,jdbcType=VARCHAR}");
        sql.SET("contact_mobile = #{record.contactMobile,jdbcType=VARCHAR}");
        sql.SET("use_time = #{record.useTime,jdbcType=VARCHAR}");
        sql.SET("encryptStr = #{record.encryptstr,jdbcType=VARCHAR}");
        sql.SET("session = #{record.session,jdbcType=VARCHAR}");
        
        AccountInfoExample example = (AccountInfoExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AccountInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("account_info");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getContactMobile() != null) {
            sql.SET("contact_mobile = #{contactMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getUseTime() != null) {
            sql.SET("use_time = #{useTime,jdbcType=VARCHAR}");
        }
        
        if (record.getEncryptstr() != null) {
            sql.SET("encryptStr = #{encryptstr,jdbcType=VARCHAR}");
        }
        
        if (record.getSession() != null) {
            sql.SET("session = #{session,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("account_no = #{accountNo,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, AccountInfoExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}