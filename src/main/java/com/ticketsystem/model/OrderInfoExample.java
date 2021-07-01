package com.ticketsystem.model;

import java.util.ArrayList;
import java.util.List;

public class OrderInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOiIdIsNull() {
            addCriterion("oi_id is null");
            return (Criteria) this;
        }

        public Criteria andOiIdIsNotNull() {
            addCriterion("oi_id is not null");
            return (Criteria) this;
        }

        public Criteria andOiIdEqualTo(String value) {
            addCriterion("oi_id =", value, "oiId");
            return (Criteria) this;
        }

        public Criteria andOiIdNotEqualTo(String value) {
            addCriterion("oi_id <>", value, "oiId");
            return (Criteria) this;
        }

        public Criteria andOiIdGreaterThan(String value) {
            addCriterion("oi_id >", value, "oiId");
            return (Criteria) this;
        }

        public Criteria andOiIdGreaterThanOrEqualTo(String value) {
            addCriterion("oi_id >=", value, "oiId");
            return (Criteria) this;
        }

        public Criteria andOiIdLessThan(String value) {
            addCriterion("oi_id <", value, "oiId");
            return (Criteria) this;
        }

        public Criteria andOiIdLessThanOrEqualTo(String value) {
            addCriterion("oi_id <=", value, "oiId");
            return (Criteria) this;
        }

        public Criteria andOiIdLike(String value) {
            addCriterion("oi_id like", value, "oiId");
            return (Criteria) this;
        }

        public Criteria andOiIdNotLike(String value) {
            addCriterion("oi_id not like", value, "oiId");
            return (Criteria) this;
        }

        public Criteria andOiIdIn(List<String> values) {
            addCriterion("oi_id in", values, "oiId");
            return (Criteria) this;
        }

        public Criteria andOiIdNotIn(List<String> values) {
            addCriterion("oi_id not in", values, "oiId");
            return (Criteria) this;
        }

        public Criteria andOiIdBetween(String value1, String value2) {
            addCriterion("oi_id between", value1, value2, "oiId");
            return (Criteria) this;
        }

        public Criteria andOiIdNotBetween(String value1, String value2) {
            addCriterion("oi_id not between", value1, value2, "oiId");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNull() {
            addCriterion("account_no is null");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNotNull() {
            addCriterion("account_no is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNoEqualTo(String value) {
            addCriterion("account_no =", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotEqualTo(String value) {
            addCriterion("account_no <>", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThan(String value) {
            addCriterion("account_no >", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("account_no >=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThan(String value) {
            addCriterion("account_no <", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThanOrEqualTo(String value) {
            addCriterion("account_no <=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLike(String value) {
            addCriterion("account_no like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotLike(String value) {
            addCriterion("account_no not like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoIn(List<String> values) {
            addCriterion("account_no in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotIn(List<String> values) {
            addCriterion("account_no not in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoBetween(String value1, String value2) {
            addCriterion("account_no between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotBetween(String value1, String value2) {
            addCriterion("account_no not between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andTripCodeIsNull() {
            addCriterion("trip_code is null");
            return (Criteria) this;
        }

        public Criteria andTripCodeIsNotNull() {
            addCriterion("trip_code is not null");
            return (Criteria) this;
        }

        public Criteria andTripCodeEqualTo(String value) {
            addCriterion("trip_code =", value, "tripCode");
            return (Criteria) this;
        }

        public Criteria andTripCodeNotEqualTo(String value) {
            addCriterion("trip_code <>", value, "tripCode");
            return (Criteria) this;
        }

        public Criteria andTripCodeGreaterThan(String value) {
            addCriterion("trip_code >", value, "tripCode");
            return (Criteria) this;
        }

        public Criteria andTripCodeGreaterThanOrEqualTo(String value) {
            addCriterion("trip_code >=", value, "tripCode");
            return (Criteria) this;
        }

        public Criteria andTripCodeLessThan(String value) {
            addCriterion("trip_code <", value, "tripCode");
            return (Criteria) this;
        }

        public Criteria andTripCodeLessThanOrEqualTo(String value) {
            addCriterion("trip_code <=", value, "tripCode");
            return (Criteria) this;
        }

        public Criteria andTripCodeLike(String value) {
            addCriterion("trip_code like", value, "tripCode");
            return (Criteria) this;
        }

        public Criteria andTripCodeNotLike(String value) {
            addCriterion("trip_code not like", value, "tripCode");
            return (Criteria) this;
        }

        public Criteria andTripCodeIn(List<String> values) {
            addCriterion("trip_code in", values, "tripCode");
            return (Criteria) this;
        }

        public Criteria andTripCodeNotIn(List<String> values) {
            addCriterion("trip_code not in", values, "tripCode");
            return (Criteria) this;
        }

        public Criteria andTripCodeBetween(String value1, String value2) {
            addCriterion("trip_code between", value1, value2, "tripCode");
            return (Criteria) this;
        }

        public Criteria andTripCodeNotBetween(String value1, String value2) {
            addCriterion("trip_code not between", value1, value2, "tripCode");
            return (Criteria) this;
        }

        public Criteria andFlightNoIsNull() {
            addCriterion("flight_no is null");
            return (Criteria) this;
        }

        public Criteria andFlightNoIsNotNull() {
            addCriterion("flight_no is not null");
            return (Criteria) this;
        }

        public Criteria andFlightNoEqualTo(String value) {
            addCriterion("flight_no =", value, "flightNo");
            return (Criteria) this;
        }

        public Criteria andFlightNoNotEqualTo(String value) {
            addCriterion("flight_no <>", value, "flightNo");
            return (Criteria) this;
        }

        public Criteria andFlightNoGreaterThan(String value) {
            addCriterion("flight_no >", value, "flightNo");
            return (Criteria) this;
        }

        public Criteria andFlightNoGreaterThanOrEqualTo(String value) {
            addCriterion("flight_no >=", value, "flightNo");
            return (Criteria) this;
        }

        public Criteria andFlightNoLessThan(String value) {
            addCriterion("flight_no <", value, "flightNo");
            return (Criteria) this;
        }

        public Criteria andFlightNoLessThanOrEqualTo(String value) {
            addCriterion("flight_no <=", value, "flightNo");
            return (Criteria) this;
        }

        public Criteria andFlightNoLike(String value) {
            addCriterion("flight_no like", value, "flightNo");
            return (Criteria) this;
        }

        public Criteria andFlightNoNotLike(String value) {
            addCriterion("flight_no not like", value, "flightNo");
            return (Criteria) this;
        }

        public Criteria andFlightNoIn(List<String> values) {
            addCriterion("flight_no in", values, "flightNo");
            return (Criteria) this;
        }

        public Criteria andFlightNoNotIn(List<String> values) {
            addCriterion("flight_no not in", values, "flightNo");
            return (Criteria) this;
        }

        public Criteria andFlightNoBetween(String value1, String value2) {
            addCriterion("flight_no between", value1, value2, "flightNo");
            return (Criteria) this;
        }

        public Criteria andFlightNoNotBetween(String value1, String value2) {
            addCriterion("flight_no not between", value1, value2, "flightNo");
            return (Criteria) this;
        }

        public Criteria andCabinCodeIsNull() {
            addCriterion("cabin_code is null");
            return (Criteria) this;
        }

        public Criteria andCabinCodeIsNotNull() {
            addCriterion("cabin_code is not null");
            return (Criteria) this;
        }

        public Criteria andCabinCodeEqualTo(String value) {
            addCriterion("cabin_code =", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeNotEqualTo(String value) {
            addCriterion("cabin_code <>", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeGreaterThan(String value) {
            addCriterion("cabin_code >", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cabin_code >=", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeLessThan(String value) {
            addCriterion("cabin_code <", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeLessThanOrEqualTo(String value) {
            addCriterion("cabin_code <=", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeLike(String value) {
            addCriterion("cabin_code like", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeNotLike(String value) {
            addCriterion("cabin_code not like", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeIn(List<String> values) {
            addCriterion("cabin_code in", values, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeNotIn(List<String> values) {
            addCriterion("cabin_code not in", values, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeBetween(String value1, String value2) {
            addCriterion("cabin_code between", value1, value2, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeNotBetween(String value1, String value2) {
            addCriterion("cabin_code not between", value1, value2, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(String value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(String value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(String value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(String value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(String value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(String value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLike(String value) {
            addCriterion("price like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotLike(String value) {
            addCriterion("price not like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<String> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<String> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(String value1, String value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(String value1, String value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andStandbyCountIsNull() {
            addCriterion("standby_count is null");
            return (Criteria) this;
        }

        public Criteria andStandbyCountIsNotNull() {
            addCriterion("standby_count is not null");
            return (Criteria) this;
        }

        public Criteria andStandbyCountEqualTo(String value) {
            addCriterion("standby_count =", value, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andStandbyCountNotEqualTo(String value) {
            addCriterion("standby_count <>", value, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andStandbyCountGreaterThan(String value) {
            addCriterion("standby_count >", value, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andStandbyCountGreaterThanOrEqualTo(String value) {
            addCriterion("standby_count >=", value, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andStandbyCountLessThan(String value) {
            addCriterion("standby_count <", value, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andStandbyCountLessThanOrEqualTo(String value) {
            addCriterion("standby_count <=", value, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andStandbyCountLike(String value) {
            addCriterion("standby_count like", value, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andStandbyCountNotLike(String value) {
            addCriterion("standby_count not like", value, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andStandbyCountIn(List<String> values) {
            addCriterion("standby_count in", values, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andStandbyCountNotIn(List<String> values) {
            addCriterion("standby_count not in", values, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andStandbyCountBetween(String value1, String value2) {
            addCriterion("standby_count between", value1, value2, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andStandbyCountNotBetween(String value1, String value2) {
            addCriterion("standby_count not between", value1, value2, "standbyCount");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(String value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(String value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(String value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(String value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(String value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(String value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLike(String value) {
            addCriterion("order_status like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotLike(String value) {
            addCriterion("order_status not like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<String> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<String> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(String value1, String value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(String value1, String value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andCountTimeIsNull() {
            addCriterion("count_time is null");
            return (Criteria) this;
        }

        public Criteria andCountTimeIsNotNull() {
            addCriterion("count_time is not null");
            return (Criteria) this;
        }

        public Criteria andCountTimeEqualTo(String value) {
            addCriterion("count_time =", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeNotEqualTo(String value) {
            addCriterion("count_time <>", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeGreaterThan(String value) {
            addCriterion("count_time >", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeGreaterThanOrEqualTo(String value) {
            addCriterion("count_time >=", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeLessThan(String value) {
            addCriterion("count_time <", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeLessThanOrEqualTo(String value) {
            addCriterion("count_time <=", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeLike(String value) {
            addCriterion("count_time like", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeNotLike(String value) {
            addCriterion("count_time not like", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeIn(List<String> values) {
            addCriterion("count_time in", values, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeNotIn(List<String> values) {
            addCriterion("count_time not in", values, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeBetween(String value1, String value2) {
            addCriterion("count_time between", value1, value2, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeNotBetween(String value1, String value2) {
            addCriterion("count_time not between", value1, value2, "countTime");
            return (Criteria) this;
        }

        public Criteria andRoundIsNull() {
            addCriterion("round is null");
            return (Criteria) this;
        }

        public Criteria andRoundIsNotNull() {
            addCriterion("round is not null");
            return (Criteria) this;
        }

        public Criteria andRoundEqualTo(String value) {
            addCriterion("round =", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundNotEqualTo(String value) {
            addCriterion("round <>", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundGreaterThan(String value) {
            addCriterion("round >", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundGreaterThanOrEqualTo(String value) {
            addCriterion("round >=", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundLessThan(String value) {
            addCriterion("round <", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundLessThanOrEqualTo(String value) {
            addCriterion("round <=", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundLike(String value) {
            addCriterion("round like", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundNotLike(String value) {
            addCriterion("round not like", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundIn(List<String> values) {
            addCriterion("round in", values, "round");
            return (Criteria) this;
        }

        public Criteria andRoundNotIn(List<String> values) {
            addCriterion("round not in", values, "round");
            return (Criteria) this;
        }

        public Criteria andRoundBetween(String value1, String value2) {
            addCriterion("round between", value1, value2, "round");
            return (Criteria) this;
        }

        public Criteria andRoundNotBetween(String value1, String value2) {
            addCriterion("round not between", value1, value2, "round");
            return (Criteria) this;
        }

        public Criteria andInputTimeIsNull() {
            addCriterion("input_time is null");
            return (Criteria) this;
        }

        public Criteria andInputTimeIsNotNull() {
            addCriterion("input_time is not null");
            return (Criteria) this;
        }

        public Criteria andInputTimeEqualTo(String value) {
            addCriterion("input_time =", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeNotEqualTo(String value) {
            addCriterion("input_time <>", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeGreaterThan(String value) {
            addCriterion("input_time >", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeGreaterThanOrEqualTo(String value) {
            addCriterion("input_time >=", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeLessThan(String value) {
            addCriterion("input_time <", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeLessThanOrEqualTo(String value) {
            addCriterion("input_time <=", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeLike(String value) {
            addCriterion("input_time like", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeNotLike(String value) {
            addCriterion("input_time not like", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeIn(List<String> values) {
            addCriterion("input_time in", values, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeNotIn(List<String> values) {
            addCriterion("input_time not in", values, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeBetween(String value1, String value2) {
            addCriterion("input_time between", value1, value2, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeNotBetween(String value1, String value2) {
            addCriterion("input_time not between", value1, value2, "inputTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(String value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(String value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(String value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(String value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(String value) {
            addCriterion("update_time like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(String value) {
            addCriterion("update_time not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<String> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<String> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(String value1, String value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andInputUserIsNull() {
            addCriterion("input_user is null");
            return (Criteria) this;
        }

        public Criteria andInputUserIsNotNull() {
            addCriterion("input_user is not null");
            return (Criteria) this;
        }

        public Criteria andInputUserEqualTo(String value) {
            addCriterion("input_user =", value, "inputUser");
            return (Criteria) this;
        }

        public Criteria andInputUserNotEqualTo(String value) {
            addCriterion("input_user <>", value, "inputUser");
            return (Criteria) this;
        }

        public Criteria andInputUserGreaterThan(String value) {
            addCriterion("input_user >", value, "inputUser");
            return (Criteria) this;
        }

        public Criteria andInputUserGreaterThanOrEqualTo(String value) {
            addCriterion("input_user >=", value, "inputUser");
            return (Criteria) this;
        }

        public Criteria andInputUserLessThan(String value) {
            addCriterion("input_user <", value, "inputUser");
            return (Criteria) this;
        }

        public Criteria andInputUserLessThanOrEqualTo(String value) {
            addCriterion("input_user <=", value, "inputUser");
            return (Criteria) this;
        }

        public Criteria andInputUserLike(String value) {
            addCriterion("input_user like", value, "inputUser");
            return (Criteria) this;
        }

        public Criteria andInputUserNotLike(String value) {
            addCriterion("input_user not like", value, "inputUser");
            return (Criteria) this;
        }

        public Criteria andInputUserIn(List<String> values) {
            addCriterion("input_user in", values, "inputUser");
            return (Criteria) this;
        }

        public Criteria andInputUserNotIn(List<String> values) {
            addCriterion("input_user not in", values, "inputUser");
            return (Criteria) this;
        }

        public Criteria andInputUserBetween(String value1, String value2) {
            addCriterion("input_user between", value1, value2, "inputUser");
            return (Criteria) this;
        }

        public Criteria andInputUserNotBetween(String value1, String value2) {
            addCriterion("input_user not between", value1, value2, "inputUser");
            return (Criteria) this;
        }

        public Criteria andAttribute0IsNull() {
            addCriterion("attribute0 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute0IsNotNull() {
            addCriterion("attribute0 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute0EqualTo(String value) {
            addCriterion("attribute0 =", value, "attribute0");
            return (Criteria) this;
        }

        public Criteria andAttribute0NotEqualTo(String value) {
            addCriterion("attribute0 <>", value, "attribute0");
            return (Criteria) this;
        }

        public Criteria andAttribute0GreaterThan(String value) {
            addCriterion("attribute0 >", value, "attribute0");
            return (Criteria) this;
        }

        public Criteria andAttribute0GreaterThanOrEqualTo(String value) {
            addCriterion("attribute0 >=", value, "attribute0");
            return (Criteria) this;
        }

        public Criteria andAttribute0LessThan(String value) {
            addCriterion("attribute0 <", value, "attribute0");
            return (Criteria) this;
        }

        public Criteria andAttribute0LessThanOrEqualTo(String value) {
            addCriterion("attribute0 <=", value, "attribute0");
            return (Criteria) this;
        }

        public Criteria andAttribute0Like(String value) {
            addCriterion("attribute0 like", value, "attribute0");
            return (Criteria) this;
        }

        public Criteria andAttribute0NotLike(String value) {
            addCriterion("attribute0 not like", value, "attribute0");
            return (Criteria) this;
        }

        public Criteria andAttribute0In(List<String> values) {
            addCriterion("attribute0 in", values, "attribute0");
            return (Criteria) this;
        }

        public Criteria andAttribute0NotIn(List<String> values) {
            addCriterion("attribute0 not in", values, "attribute0");
            return (Criteria) this;
        }

        public Criteria andAttribute0Between(String value1, String value2) {
            addCriterion("attribute0 between", value1, value2, "attribute0");
            return (Criteria) this;
        }

        public Criteria andAttribute0NotBetween(String value1, String value2) {
            addCriterion("attribute0 not between", value1, value2, "attribute0");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}