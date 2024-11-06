package mtnl;

import java.io.Serializable;

public class Bill implements Serializable {
    private int consumerId;
    private String consumerName;
    private String billDueDate;
    private double billAmount;

    public Bill(int consumerId, String consumerName, String billDueDate, double billAmount) {
        this.consumerId = consumerId;
        this.consumerName = consumerName;
        this.billDueDate = billDueDate;
        this.billAmount = billAmount;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public String getBillDueDate() {
        return billDueDate;
    }

    public double getBillAmount() {
        return billAmount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "consumerId=" + consumerId +
                ", consumerName='" + consumerName + '\'' +
                ", billDueDate='" + billDueDate + '\'' +
                ", billAmount=" + billAmount +
                '}';
    }
}
