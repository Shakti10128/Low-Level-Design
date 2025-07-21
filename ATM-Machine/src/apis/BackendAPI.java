package apis;

import DTO.CreateTransactionDTO;

public interface BackendAPI {
    public int createTransaction(CreateTransactionDTO createTransactionDTO);
}
