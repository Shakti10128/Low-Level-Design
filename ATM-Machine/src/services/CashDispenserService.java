package services;

import models.ATM;

public interface CashDispenserService {
    void dispenseCash(ATM atm, int amount);
}
