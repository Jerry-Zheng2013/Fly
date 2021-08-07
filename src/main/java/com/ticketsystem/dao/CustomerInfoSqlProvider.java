package com.ticketsystem.dao;

import com.ticketsystem.model.CustomerInfo;
import com.ticketsystem.model.CustomerInfoExample.Criteria;
import com.ticketsystem.model.CustomerInfoExample.Criterion;
import com.ticketsystem.model.CustomerInfoExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class CustomerInfoSqlProvider {

    public String countByExample(CustomerInfoExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("customer_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(CustomerInfoExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("customer_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(CustomerInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("customer_info");
        
        if (record.getCustomerId() != null) {
            sql.VALUES("customer_id", "#{customerId,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCardType() != null) {
            sql.VALUES("card_type", "#{cardType,jdbcType=VARCHAR}");
        }
        
        if (record.getCardNo() != null) {
            sql.VALUES("card_no", "#{cardNo,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.VALUES("mobile", "#{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getBirthday() != null) {
            sql.VALUES("birthday", "#{birthday,jdbcType=VARCHAR}");
        }
        
        if (record.getTicketType() != null) {
            sql.VALUES("ticket_type", "#{ticketType,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomerStatus() != null) {
            sql.VALUES("customer_status", "#{customerStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountNo() != null) {
            sql.VALUES("account_no", "#{accountNo,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(CustomerInfoExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("customer_id");
        } else {
            sql.SELECT("customer_id");
        }
        sql.SELECT("name");
        sql.SELECT("card_type");
        sql.SELECT("card_no");
        sql.SELECT("mobile");
        sql.SELECT("birthday");
        sql.SELECT("ticket_type");
        sql.SELECT("customer_status");
        sql.SELECT("account_no");
        sql.FROM("customer_info");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CustomerInfo record = (CustomerInfo) parameter.get("record");
        CustomerInfoExample example = (CustomerInfoExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("customer_info");
        
        if (record.getCustomerId() != null) {
            sql.SET("customer_id = #{record.customerId,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getCardType() != null) {
            sql.SET("card_type = #{record.cardType,jdbcType=VARCHAR}");
        }
        
        if (record.getCardNo() != null) {
            sql.SET("card_no = #{record.cardNo,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.SET("mobile = #{record.mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getBirthday() != null) {
            sql.SET("birthday = #{record.birthday,jdbcType=VARCHAR}");
        }
        
        if (record.getTicketType() != null) {
            sql.SET("ticket_type = #{record.ticketType,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomerStatus() != null) {
            sql.SET("customer_status = #{record.customerStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountNo() != null) {
            sql.SET("account_no = #{record.accountNo,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("customer_info");
        
        sql.SET("customer_id = #{record.customerId,jdbcType=VARCHAR}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("card_type = #{record.cardType,jdbcType=VARCHAR}");
        sql.SET("card_no = #{record.cardNo,jdbcType=VARCHAR}");
        sql.SET("mobile = #{record.mobile,jdbcType=VARCHAR}");
        sql.SET("birthday = #{record.birthday,jdbcType=VARCHAR}");
        sql.SET("ticket_type = #{record.ticketType,jdbcType=VARCHAR}");
        sql.SET("customer_status = #{record.customerStatus,jdbcType=VARCHAR}");
        sql.SET("account_no = #{record.accountNo,jdbcType=VARCHAR}");
        
        CustomerInfoExample example = (CustomerInfoExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CustomerInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("customer_info");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCardType() != null) {
            sql.SET("card_type = #{cardType,jdbcType=VARCHAR}");
        }
        
        if (record.getCardNo() != null) {
            sql.SET("card_no = #{cardNo,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.SET("mobile = #{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getBirthday() != null) {
            sql.SET("birthday = #{birthday,jdbcType=VARCHAR}");
        }
        
        if (record.getTicketType() != null) {
            sql.SET("ticket_type = #{ticketType,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomerStatus() != null) {
            sql.SET("customer_status = #{customerStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountNo() != null) {
            sql.SET("account_no = #{accountNo,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("customer_id = #{customerId,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, CustomerInfoExample example, boolean includeExamplePhrase) {
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