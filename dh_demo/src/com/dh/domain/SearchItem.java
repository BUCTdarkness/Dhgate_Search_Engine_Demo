package com.dh.domain;

public class SearchItem {
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public Double getMincost() {
		return mincost;
	}
	public void setMincost(Double mincost) {
		this.mincost = mincost;
	}
	public Double getMaxcost() {
		return maxcost;
	}
	public void setMaxcost(Double maxcost) {
		this.maxcost = maxcost;
	}
	private String key;
	private String supplierid;
	private String imgurl;
	private Double mincost;
	private Double maxcost;
	private String itemname;
	
}
