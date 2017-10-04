package br.ufc.dc.tpii.bank.persistence;

import java.util.Vector;

import br.ufc.dc.tpii.bank.account.AbstractAccount;
import br.ufc.dc.tpii.bank.persistence.exception.AccountCreationException;
import br.ufc.dc.tpii.bank.persistence.exception.AccountDeletionException;
import br.ufc.dc.tpii.bank.persistence.exception.AccountNotFoundException;
import br.ufc.dc.tpii.bank.persistence.exception.FlushException;

public class AccountVector implements IAccountRepository {

	private Vector<AbstractAccount> accounts = null;

	public AccountVector() {
		this.accounts = new Vector<AbstractAccount>();
	}

	@Override
	public void delete(String number) throws AccountDeletionException {
		AbstractAccount account = this.findAccount(number);
		if (account != null) {
			this.accounts.remove(account);
		} else {
			throw new AccountDeletionException("Account doesn't exist!", number);
		}
	}

	@Override
	public void create(AbstractAccount account) throws AccountCreationException {
		if (this.findAccount(account.getNumber()) == null) {
			this.accounts.addElement(account);
		} else {
			throw new AccountCreationException("Account alredy exist!", account.getNumber());
		}
	}

	@Override
	public AbstractAccount retrieve(String number) throws AccountNotFoundException {
		AbstractAccount account = findAccount(number);
		if (account != null) {
			return account;
		} else {
			throw new AccountNotFoundException("Account not found!", number);
		}
	}

	@Override
	public int mumberOfAccounts() {
		return this.accounts.size();
	}

	@Override
	public AbstractAccount[] list() {
		AbstractAccount[] list = null;
		if (this.accounts.size() > 0) {
			list = new AbstractAccount[this.accounts.size()];
			for (int i = 0; i < this.accounts.size(); i++) {
				list[i] = (AbstractAccount) this.accounts.elementAt(i);
			}
		}
		return list;
	}

	@Override
	public void flush() throws FlushException {
	}

	private AbstractAccount findAccount(String number) {
		if (this.accounts.size() > 0) {
			for (int i = 0; i < this.accounts.size(); i++) {
				AbstractAccount account = (AbstractAccount) this.accounts.elementAt(i);
				if (account.getNumber().equals(number)) {
					return account;
				}
			}
		}
		return null;
	}
}