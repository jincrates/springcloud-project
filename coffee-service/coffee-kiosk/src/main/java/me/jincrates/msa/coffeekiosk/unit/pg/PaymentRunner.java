package me.jincrates.msa.coffeekiosk.unit.pg;

import me.jincrates.msa.coffeekiosk.spring.temp.domain.payment.PayMethod;

import java.util.HashMap;
import java.util.Map;

public class PaymentRunner {

    public static void main(String[] args) {

        final Map<PayMethod, PayPrepare> prepareMap = new HashMap<>();
        prepareMap.put(PayMethod.SETTLE_BANK, new SettleBank());
        prepareMap.put(PayMethod.TOSS_PAY, new TossPay());

        final Map<PayMethod, PayApprove> approveMap = new HashMap<>();
        approveMap.put(PayMethod.SETTLE_BANK, new SettleBank());

        final PayPrepare client = prepareMap.get(PayMethod.TOSS_PAY);
        client.prepare();

        final PayApprove client2 = approveMap.get(PayMethod.SETTLE_BANK);
        client2.approve();
    }
}
