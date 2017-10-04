package br.ufc.dc.tpii.bank.account;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ufc.dc.tpii.bank.account.exception.NegativeAmountException;
import br.ufc.dc.tpii.bank.control.BankController;
import br.ufc.dc.tpii.bank.control.exception.BankTransactionException;
import br.ufc.dc.tpii.bank.persistence.AccountVector;

public class BankControllerTest {
	
	AccountVector vector;
	BankController controller;
	AbstractAccount simple;
	AbstractAccount savings;
	
	@Before
	public void setup() {
		vector = new AccountVector();
		controller = new BankController(vector);
		simple = new OrdinaryAccount("123A");
		savings = new SavingsAccount("123C");
	}

	@Test
	public void scenario() {
		try {
			controller.addAccount(simple);
			controller.addAccount(savings);
			simple.credit(20);
			savings.credit(20);
			controller.doTransfer("123A", "123C", 10);
			assertEquals("Erro na transferencia conta Simples", 10, simple.getBalance(), 0.0);
			assertEquals("Erro na transferencia conta Poupanca", 30, savings.getBalance(), 0.0);
		} catch (NegativeAmountException | BankTransactionException e) {
			e.printStackTrace();
		}
	}

}
