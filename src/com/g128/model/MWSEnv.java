package com.g128.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="AmazonEnvelope", namespace="")
@XmlAccessorType(XmlAccessType.FIELD)
public class MWSEnv {
	@XmlElement(name="Message")
	private List<OrderMessage> lmsg;
	
	@XmlElement(name="Header")
	private Header head;
	
	@XmlElement(name="MessageType")
	private String msgType;

//	@XmlElement(name="Message")
	public List<OrderMessage> getLmsg() {
		return lmsg;
	}

	public void setLmsg(List<OrderMessage> lmsg) {
		this.lmsg = lmsg;
	}

	public Header getHead() {
		return head;
	}

	public void setHead(Header head) {
		this.head = head;
	}

	public String getMsgType() {
		return msgType;
	}
	
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	
}
