package me.jincrates.msa.coffeekiosk.unit.pg;

public class TossPay implements PayPrepare {

    @Override
    public void prepare() {
        System.out.println("토스페이 결제준비");
    }
}
