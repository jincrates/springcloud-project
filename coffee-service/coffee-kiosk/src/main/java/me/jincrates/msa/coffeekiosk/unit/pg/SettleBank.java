package me.jincrates.msa.coffeekiosk.unit.pg;

public class SettleBank implements PayPrepare, PayApprove {


    @Override
    public void prepare() {
        System.out.println("내통장결제 결제준비");
    }

    @Override
    public void approve() {
        System.out.println("내통장결제 결제요청");
    }
}
