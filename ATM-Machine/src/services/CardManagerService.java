package services;

import models.Card;

public interface CardManagerService {
    boolean validateCard(Card card,String pin);

    boolean validateWithdrawal(Card card,double amount);

    boolean doTransaction(Card card,double amount,String transactionId);
}
