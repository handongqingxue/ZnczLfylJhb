package com.znczLfylJhb.entity;

public class DingDan {
	
	/**
	 * ���ϰ�
	 */
	public static final Integer DAI_SHANG_BANG=1;
	/**
	 * �ϰ���
	 */
	public static final Integer SHANG_BANG_ZHONG=2;
	/**
	 * ������
	 */
	public static final Integer DAI_CHENG_ZHONG=3;
	/**
	 * ������
	 */
	public static final Integer CHENG_ZHONG_ZHONG=4;
	/**
	 * ���°�
	 */
	public static final Integer DAI_XIA_BANG=5;
	/**
	 * �°���
	 */
	public static final Integer XIA_BANG_ZHONG=6;
	/**
	 * �����
	 */
	public static final Integer YI_WAN_CHENG=7;

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDdh() {
		return ddh;
	}
	public void setDdh(String ddh) {
		this.ddh = ddh;
	}
	public Integer getDdztId() {
		return ddztId;
	}
	public void setDdztId(Integer ddztId) {
		this.ddztId = ddztId;
	}
	public String getDdztMc() {
		return ddztMc;
	}
	public void setDdztMc(String ddztMc) {
		this.ddztMc = ddztMc;
	}
	public Integer getXddztId() {
		return xddztId;
	}
	public void setXddztId(Integer xddztId) {
		this.xddztId = xddztId;
	}
	public String getXddztMc() {
		return xddztMc;
	}
	public void setXddztMc(String xddztMc) {
		this.xddztMc = xddztMc;
	}
	public Integer getYjzt() {
		return yjzt;
	}
	public void setYjzt(Integer yjzt) {
		this.yjzt = yjzt;
	}
	public Integer getXyjzt() {
		return xyjzt;
	}
	public void setXyjzt(Integer xyjzt) {
		this.xyjzt = xyjzt;
	}
	public Integer getEjzt() {
		return ejzt;
	}
	public void setEjzt(Integer ejzt) {
		this.ejzt = ejzt;
	}
	public Integer getXejzt() {
		return xejzt;
	}
	public void setXejzt(Integer xejzt) {
		this.xejzt = xejzt;
	}
	public Integer getYjbfh() {
		return yjbfh;
	}
	public void setYjbfh(Integer yjbfh) {
		this.yjbfh = yjbfh;
	}
	public Integer getEjbfh() {
		return ejbfh;
	}
	public void setEjbfh(Integer ejbfh) {
		this.ejbfh = ejbfh;
	}
	public Integer getLxlx() {
		return lxlx;
	}
	public void setLxlx(Integer lxlx) {
		this.lxlx = lxlx;
	}
	public Integer getClId() {
		return clId;
	}
	public void setClId(Integer clId) {
		this.clId = clId;
	}
	public String getBjsj() {
		return bjsj;
	}
	public void setBjsj(String bjsj) {
		this.bjsj = bjsj;
	}
	private String ddh;//������
	private Integer ddztId;
	private String ddztMc;
	private Integer xddztId;//�¶���״̬id
	private String xddztMc;//�¶���״̬����
	private Integer yjzt;//һ��״̬(1.���ϰ� 2.�ϰ��� 3.�����)
	private Integer xyjzt;//��һ��״̬(1.���ϰ� 2.�ϰ��� 3.�����)
	private Integer ejzt;//����״̬(1.���ϰ� 2.�ϰ��� 3.�����)
	private Integer xejzt;//�¶���״̬(1.���ϰ� 2.�ϰ��� 3.�����)
	private Integer yjbfh=0;//һ�������
	private Integer ejbfh=0;//���������
	private Integer lxlx;//��������
	private Integer clId;
	private String bjsj;//�༭ʱ��
}