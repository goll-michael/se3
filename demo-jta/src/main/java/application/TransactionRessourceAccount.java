package application;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

public class TransactionRessourceAccount implements XAResource
{
	private double balance = 0.0;
	private double credit = 0.0;
	private double debit = 0.0;
	private TYPE type = TYPE.NONE;
	
	private enum TYPE { NONE, CREDIT, DEBIT }
	
	public TransactionRessourceAccount(double balance) {
		this.balance = balance;
	}
	
	/*
	 * Gutschrift des Werts
	 */
	public void credit(double value) {
		this.credit = value;
		this.type = TYPE.CREDIT;
	}
	
	/*
	 * Abbuchung des Werts
	 */
	public void debit(double value) {
		this.debit = value;
		this.type = TYPE.DEBIT;
	}
	
	public String print() {
		return String.format("Aktueller Kontostand ist %.2f", balance);
	}
	
	/*
	 * Mit dieser Methode wird dem Ressourcen-Manager mitgeteilt, sich auf ein commit
	 * vorzubereiten. In dieser Methode bereitet sich der Ressourcen-Manager auch auf
	 * ein eventuelles rollback vor, d.h. ein undo-log wird geschrieben oder es werden
	 * Sicherungskopien angelegt. Es ist der erste Schritt im Zwei-Phasen-Commit-Protokoll.
	 * Der Transaktionsmanager geht erst in die zweite (commit-)Phase über, wenn kein
	 * Ressourcen-Manager beim prepare()-Aufruf eine XAException geworfen hat.
	 */
    @Override
	public int prepare(Xid arg0) throws XAException
	{
    	if(this.type == TYPE.NONE) {
    		throw new XAException("TYPE is NONE");
    	}
    	if(this.type == TYPE.CREDIT) {
    		this.balance += this.credit;
    	} else if(this.type == TYPE.DEBIT) {
    		if(this.balance < this.debit) {
    			throw new XAException("Balance too low");	
    		}
    		this.balance -= this.debit;
    	}
		return XA_OK;
	}
    
	/*
	 * Mit dieser Methode wird der Ressourcen-Manager aufgefordert, die Transaktion
	 * abzuschließen und ggfs. gesperrte Ressourcen freizugeben.
	 * An dieser Stelle wird lediglich die Sicherungskopie geloescht.
	 */
	@Override
	public void commit(Xid arg0, boolean arg1) throws XAException
	{
		this.credit = 0.0;
		this.debit = 0.0;
		this.type = TYPE.NONE;
	}
	
	/*
	 * Beim Rollback soll der Ressourcen-Manager alle waehrend der Transaktion
	 * durchgefuehrten Aenderungen wieder rueckgaengig machen. Hierfuer werden die
	 * Sicherungen genutzt, die waehrend der prepare()-Phase angelegt worden sind.
	 */
	@Override
	public void rollback(Xid arg0) throws XAException
	{
		if(this.type == TYPE.CREDIT) {
    		this.balance -= this.credit;
    	} else if(this.type == TYPE.DEBIT) {
    		this.balance += this.debit;
    	}
	}

	@Override
	public void end(Xid arg0, int arg1) throws XAException
	{
	}

	@Override
	public void forget(Xid arg0) throws XAException
	{
	}

	@Override
	public int getTransactionTimeout() throws XAException
	{
		return 0;
	}

	@Override
	public boolean isSameRM(XAResource xares) throws XAException
	{
		return (xares == this);
	}

	@Override
	public Xid[] recover(int arg0) throws XAException
	{
		return null;
	}

	@Override
	public boolean setTransactionTimeout(int arg0) throws XAException
	{
		return false;
	}

	@Override
	public void start(Xid arg0, int arg1) throws XAException
	{		
	}
}
