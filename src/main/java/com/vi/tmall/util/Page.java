package com.vi.tmall.util;

/**
 * ��ҳ������
 * 
 * @author vi
 *
 */
public class Page {
	private int start; 	// ��ʼ���ݵ�����
	private int count;	// ��ҳ�ļ�¼��
	private int total;	// �ܹ���������
	private String param;	//�Զ������
	/**
	 * �ṩһ�����췽��
	 * @param start
	 * @param count
	 */
	public Page(int start,int count) {
		super();
		this.start = start;
		this.count = count;
	}
	
	public Page() {
		this.start = 0;
		this.count = 5;
	}
	
	/**
	 * �ж��Ƿ�����һҳ
	 * @return
	 */
	public boolean isHasPrevious() {
		return !(start == 0);
	}
	
	/**
	 * �ж��Ƿ�����һҳ
	 * @return
	 */
	public boolean isHasNext() {
		return !(start >= getLast());
	}
	
	/**
	 * ����õ�βҳ������
	 * @return
	 */
	public int getLast() {
		int last;
		if(total%count == 0)  
			last = total - count;
		else {
			last = total - total%count;
		}
		return last<0?0:last;	
	}
	
	/**
	 * ������ҳ��
	 */
	public int getTotalPage() {
		int totalPage;
		if(total%count == 0)
			totalPage =  total/count;
		else 
			totalPage =  total/count+1;
		if(totalPage == 0)
			totalPage = 1;
		return totalPage;
	}
	
	//getter,setter
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	} 
	
	public int getCurrent() {
		int current;
		if(start%count == 0)
			current = start/count+1;
		else
			current = start/count + 2;
		return current==0?1:current;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	
}
