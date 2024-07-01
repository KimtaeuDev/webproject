package com.community.vo;

import lombok.Getter;

@Getter
public class PagingVO{

	private int firstPage, lastPage, curPage, startRow, endRow, endPage, total, amount;
	private boolean prev, next;
	
	public PagingVO(int total) {
		
		this(total, 10, 1);
		
	}
	
	public PagingVO(int total, int amount) {
		
		this(total, amount, 1);
		
	}
	
	public PagingVO(int total, int amount, int curPage) {
		
		this.total = total;
		this.amount = amount;
		this.curPage = curPage;
		
        this.startRow = (this.curPage * this.amount) - this.amount + 1;
        
        this.endRow = this.startRow + this.amount - 1;
		
		this.lastPage = (int) Math.ceil(curPage / 10.0) * 10;
        
		this.firstPage = this.lastPage - 9;
		
		this.endPage = (int) Math.ceil((double)this.total / this.amount);	
		
        if(this.endRow > this.total) {
        	this.endRow = this.total;
        }
		
		if(this.endPage < this.lastPage) {
			this.lastPage = this.endPage;
		}
		
		this.prev = this.firstPage > 1;
		this.next = this.lastPage < endPage;
	}
	
	
}