package br.ufc.dc.tpii.bank.persistence;

import br.ufc.dc.tpii.bank.account.AbstractAccount;
import br.ufc.dc.tpii.bank.persistence.exception.AccountCreationException;
import br.ufc.dc.tpii.bank.persistence.exception.AccountDeletionException;
import br.ufc.dc.tpii.bank.persistence.exception.AccountNotFoundException;
import br.ufc.dc.tpii.bank.persistence.exception.FlushException;

public interface IAccountRepository {

	public void create(AbstractAccount account) throws AccountCreationException;

	public void delete(String number) throws AccountDeletionException;

	public AbstractAccount retrieve(String number) throws AccountNotFoundException;

	public AbstractAccount[] list();

	public int mumberOfAccounts();

	public void flush() throws FlushException;
}
