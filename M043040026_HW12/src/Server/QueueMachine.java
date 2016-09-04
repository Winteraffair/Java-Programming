package Server;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QueueMachine {
	private LinkedList<Transaction> queue = new LinkedList<Transaction>();
	private Lock lock = new ReentrantLock();
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	public Transaction get() {
		lock.lock();
		try{
		if (!isEmpty()) {
			//Transaction get_Tran = this.queue.poll();
			return this.queue.poll();
		}else{
			return null;
		}}finally{
			lock.unlock();
		}
		
	}

	public void add(Transaction newTran) {
		lock.lock();
		try{
		this.queue.add(newTran);
		System.out.println(newTran.get_information());
		}finally{
			lock.unlock();
		}
	}

}
