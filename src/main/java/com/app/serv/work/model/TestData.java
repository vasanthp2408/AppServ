package com.app.serv.work.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TEST_DATA")
public class TestData {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @Column(name = "TEST_DESC")
    private String testDesc;
    
    @Column(name = "INPUT_URI")
    private String inputUri;
    
    @Column(name = "INPUT_JSON")
    private String inputJson;
    
    @Column(name = "EXP_RESP")
    private String expResp;
    
    @Column(name = "EXPECTED_JSON")
    private String expJson;
    
    @Column(name = "RESULT_JSON")
    private String resultJson;
    
    @Column(name = "RES_RESP")
    private String resultresp;
    
    @Transient
    private String environment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTestDesc() {
		return testDesc;
	}

	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}

	public String getInputUri() {
		return inputUri;
	}

	public void setInputUri(String inputUri) {
		this.inputUri = inputUri;
	}

	public String getInputJson() {
		return inputJson;
	}

	public void setInputJson(String inputJson) {
		this.inputJson = inputJson;
	}

	public String getExpResp() {
		return expResp;
	}

	public String getExpJson() {
		return expJson;
	}

	public void setExpJson(String expJson) {
		this.expJson = expJson;
	}

	public void setExpResp(String expResp) {
		this.expResp = expResp;
	}

	public String getResultJson() {
		return resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}

	public String getResultresp() {
		return resultresp;
	}

	public void setResultresp(String resultresp) {
		this.resultresp = resultresp;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "TestData [id=" + id + ", testDesc=" + testDesc + ", inputUri=" + inputUri + ", inputJson=" + inputJson
				+ ", expResp=" + expResp + ", expJson=" + expJson + ", resultJson=" + resultJson + ", resultresp="
				+ resultresp + ", environment=" + environment + "]";
	}
    
}
