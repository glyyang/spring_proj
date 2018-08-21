package com.g128.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Header", namespace="com.g128.model.MWSEnv")
@XmlAccessorType(XmlAccessType.FIELD)
public class Header {
	@XmlElement(name="DocumentVersion")
	private String docVers;

	public String getDocVers() {
		return docVers;
	}

	public void setDocVers(String docVers) {
		this.docVers = docVers;
	}
	
	
}
