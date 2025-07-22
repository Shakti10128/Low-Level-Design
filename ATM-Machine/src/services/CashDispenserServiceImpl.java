package services;

import DTO.GetATMAmountReqDTO;
import apis.BackendAPI;
import apis.NodeBackendApi;
import models.ATM;

public class CashDispenserServiceImpl implements CashDispenserService {
    private final BackendAPI backendAPI;

    public CashDispenserServiceImpl() {
        this.backendAPI = new NodeBackendApi();
    }

    @Override
    public void dispenseCash(ATM atm, int amount) {
        int atmAmount = backendAPI.getATMAmount(new GetATMAmountReqDTO(atm.getAtmId()));
        if(atmAmount < amount) {
            throw new RuntimeException("ATM does not have enough money");
        }
        System.out.println("Dispensing cash: " + amount);
    }
}
