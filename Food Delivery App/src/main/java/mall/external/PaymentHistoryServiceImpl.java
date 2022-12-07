package mall.external;

import org.springframework.stereotype.Service;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    /**
     * Fallback
     */
    public PaymentHistory getPaymentHistory(Long id) {
        PaymentHistory paymentHistory = new PaymentHistory();
        return paymentHistory;
    }
}
