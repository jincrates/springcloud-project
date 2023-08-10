package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;

@Getter
@NoArgsConstructor
public class TossPayApproveResponse extends PaymentApproveResponse {

    private int code;
    private String msg;
    private String errorCode;
    private String mode;
    private String approvalTime;
    private String stateMsg;
    private int amount;
    private int discountedAmount;
    private int paidAmount;
    private String payMethod;
    private String orderNo;
    private String payToken;
    private String transactionId;
    private String cardCompanyCode;
    private String cardCompanyName;
    private String salesCheckLinkUrl;
    private String accountBankCode;
    private String accountBankName;

    @Builder
    private TossPayApproveResponse(int code, String msg, String errorCode, String mode,
        String approvalTime, String stateMsg, int amount, int discountedAmount, int paidAmount,
        String payMethod, String orderNo, String payToken, String transactionId,
        String cardCompanyCode,
        String cardCompanyName, String salesCheckLinkUrl, String accountBankCode,
        String accountBankName) {
        this.code = code;
        this.msg = msg;
        this.errorCode = errorCode;
        this.mode = mode;
        this.approvalTime = approvalTime;
        this.stateMsg = stateMsg;
        this.amount = amount;
        this.discountedAmount = discountedAmount;
        this.paidAmount = paidAmount;
        this.payMethod = payMethod;
        this.orderNo = orderNo;
        this.payToken = payToken;
        this.transactionId = transactionId;
        this.cardCompanyCode = cardCompanyCode;
        this.cardCompanyName = cardCompanyName;
        this.salesCheckLinkUrl = salesCheckLinkUrl;
        this.accountBankCode = accountBankCode;
        this.accountBankName = accountBankName;
    }

    @Override
    public String toString() {
        return "TossPayApproveResponse{" +
            "code=" + code +
            ", msg='" + msg + '\'' +
            ", errorCode='" + errorCode + '\'' +
            ", mode='" + mode + '\'' +
            ", approvalTime='" + approvalTime + '\'' +
            ", stateMsg='" + stateMsg + '\'' +
            ", amount=" + amount +
            ", discountedAmount=" + discountedAmount +
            ", paidAmount=" + paidAmount +
            ", payMethod='" + payMethod + '\'' +
            ", orderNo='" + orderNo + '\'' +
            ", payToken='" + payToken + '\'' +
            ", transactionId='" + transactionId + '\'' +
            ", cardCompanyCode='" + cardCompanyCode + '\'' +
            ", cardCompanyName='" + cardCompanyName + '\'' +
            ", salesCheckLinkUrl='" + salesCheckLinkUrl + '\'' +
            ", accountBankCode='" + accountBankCode + '\'' +
            ", accountBankName='" + accountBankName + '\'' +
            '}';
    }

    public boolean isSuccess() {
        return code == 0 && msg == null;
    }
}
