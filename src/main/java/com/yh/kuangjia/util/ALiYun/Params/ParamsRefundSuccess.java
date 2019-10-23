package com.yh.kuangjia.util.ALiYun.Params;

public class ParamsRefundSuccess extends ParamsBase {

    public ParamsRefundSuccess() {
    }

    public ParamsRefundSuccess(String phone,Integer order_id,double money) {
        this.order_id = order_id;
        this.money = money;
        this.setPhone(phone);
    }

    /**
     * 订单ID
     */
    private Integer order_id;
    /**
     * 退款金额
     */
    private double money;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
