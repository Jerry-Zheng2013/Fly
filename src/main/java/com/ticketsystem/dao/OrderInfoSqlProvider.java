package com.ticketsystem.dao;

import com.ticketsystem.model.OrderInfo;
import com.ticketsystem.model.OrderInfoExample.Criteria;
import com.ticketsystem.model.OrderInfoExample.Criterion;
import com.ticketsystem.model.OrderInfoExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class OrderInfoSqlProvider {

    public String countByExample(OrderInfoExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("order_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(OrderInfoExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("order_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(OrderInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("order_info");
        
        if (record.getOiId() != null) {
            sql.VALUES("oi_id", "#{oiId,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountNo() != null) {
            sql.VALUES("account_no", "#{accountNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderNo() != null) {
            sql.VALUES("order_no", "#{orderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getTripCode() != null) {
            sql.VALUES("trip_code", "#{tripCode,jdbcType=VARCHAR}");
        }
        
        if (record.getFlightNo() != null) {
            sql.VALUES("flight_no", "#{flightNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCabinCode() != null) {
            sql.VALUES("cabin_code", "#{cabinCode,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.VALUES("price", "#{price,jdbcType=VARCHAR}");
        }
        
        if (record.getStandbyCount() != null) {
            sql.VALUES("standby_count", "#{standbyCount,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderStatus() != null) {
            sql.VALUES("order_status", "#{orderStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCountTime() != null) {
            sql.VALUES("count_time", "#{countTime,jdbcType=VARCHAR}");
        }
        
        if (record.getRound() != null) {
            sql.VALUES("round", "#{round,jdbcType=VARCHAR}");
        }
        
        if (record.getInputTime() != null) {
            sql.VALUES("input_time", "#{inputTime,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=VARCHAR}");
        }
        
        if (record.getInputUser() != null) {
            sql.VALUES("input_user", "#{inputUser,jdbcType=VARCHAR}");
        }
        
        if (record.getAttribute0() != null) {
            sql.VALUES("attribute0", "#{attribute0,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(OrderInfoExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("oi_id");
        } else {
            sql.SELECT("oi_id");
        }
        sql.SELECT("account_no");
        sql.SELECT("order_no");
        sql.SELECT("trip_code");
        sql.SELECT("flight_no");
        sql.SELECT("cabin_code");
        sql.SELECT("price");
        sql.SELECT("standby_count");
        sql.SELECT("order_status");
        sql.SELECT("count_time");
        sql.SELECT("round");
        sql.SELECT("input_time");
        sql.SELECT("update_time");
        sql.SELECT("input_user");
        sql.SELECT("attribute0");
        sql.SELECT("remark");
        sql.FROM("order_info");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        OrderInfo record = (OrderInfo) parameter.get("record");
        OrderInfoExample example = (OrderInfoExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("order_info");
        
        if (record.getOiId() != null) {
            sql.SET("oi_id = #{record.oiId,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountNo() != null) {
            sql.SET("account_no = #{record.accountNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderNo() != null) {
            sql.SET("order_no = #{record.orderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getTripCode() != null) {
            sql.SET("trip_code = #{record.tripCode,jdbcType=VARCHAR}");
        }
        
        if (record.getFlightNo() != null) {
            sql.SET("flight_no = #{record.flightNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCabinCode() != null) {
            sql.SET("cabin_code = #{record.cabinCode,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{record.price,jdbcType=VARCHAR}");
        }
        
        if (record.getStandbyCount() != null) {
            sql.SET("standby_count = #{record.standbyCount,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderStatus() != null) {
            sql.SET("order_status = #{record.orderStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCountTime() != null) {
            sql.SET("count_time = #{record.countTime,jdbcType=VARCHAR}");
        }
        
        if (record.getRound() != null) {
            sql.SET("round = #{record.round,jdbcType=VARCHAR}");
        }
        
        if (record.getInputTime() != null) {
            sql.SET("input_time = #{record.inputTime,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=VARCHAR}");
        }
        
        if (record.getInputUser() != null) {
            sql.SET("input_user = #{record.inputUser,jdbcType=VARCHAR}");
        }
        
        if (record.getAttribute0() != null) {
            sql.SET("attribute0 = #{record.attribute0,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("order_info");
        
        sql.SET("oi_id = #{record.oiId,jdbcType=VARCHAR}");
        sql.SET("account_no = #{record.accountNo,jdbcType=VARCHAR}");
        sql.SET("order_no = #{record.orderNo,jdbcType=VARCHAR}");
        sql.SET("trip_code = #{record.tripCode,jdbcType=VARCHAR}");
        sql.SET("flight_no = #{record.flightNo,jdbcType=VARCHAR}");
        sql.SET("cabin_code = #{record.cabinCode,jdbcType=VARCHAR}");
        sql.SET("price = #{record.price,jdbcType=VARCHAR}");
        sql.SET("standby_count = #{record.standbyCount,jdbcType=VARCHAR}");
        sql.SET("order_status = #{record.orderStatus,jdbcType=VARCHAR}");
        sql.SET("count_time = #{record.countTime,jdbcType=VARCHAR}");
        sql.SET("round = #{record.round,jdbcType=VARCHAR}");
        sql.SET("input_time = #{record.inputTime,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=VARCHAR}");
        sql.SET("input_user = #{record.inputUser,jdbcType=VARCHAR}");
        sql.SET("attribute0 = #{record.attribute0,jdbcType=VARCHAR}");
        sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        
        OrderInfoExample example = (OrderInfoExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    protected void applyWhere(SQL sql, OrderInfoExample example, boolean includeExamplePhrase) {
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