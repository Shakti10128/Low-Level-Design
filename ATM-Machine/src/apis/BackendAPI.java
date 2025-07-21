package apis;

import DTO.CreateTransactionDTO;
import DTO.UpdateATMStateDTO;

public interface BackendAPI {
    public int createTransaction(CreateTransactionDTO createTransactionDTO);

    public boolean updateState(UpdateATMStateDTO updateATMStateDTO);
}
