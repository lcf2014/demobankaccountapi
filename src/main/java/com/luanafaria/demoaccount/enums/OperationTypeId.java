package com.luanafaria.demoaccount.enums;

public enum OperationTypeId{
    DEBIT(1),
    CREDIT(2),
    WITHDRAW(3),
    PAYMENT(4);

    private final int value;

    OperationTypeId(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
