package com.znczLfylJhb.entity;

public class CheLiang {

	/**
	 * İ������
	 */
	public static final Integer MO_SHENG_CHE_LIANG=1;
	/**
	 * ��ʶ����
	 */
	public static final Integer DAI_SHI_BIE_CHE_LIANG=2;
	/**
	 * ��ʶ����
	 */
	public static final Integer YI_SHI_BIE_CHE_LIANG=3;
	
	/**
	 * ��ʶ������׼ֵ
	 */
	public static final Integer DSBCLBZZ=3;
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCph() {
		return cph;
	}
	public void setCph(String cph) {
		this.cph = cph;
	}
	public Integer getLx() {
		return lx;
	}
	public void setLx(Integer lx) {
		this.lx = lx;
	}
	public Integer getWcddcs() {
		return wcddcs;
	}
	public void setWcddcs(Integer wcddcs) {
		this.wcddcs = wcddcs;
	}
	public Integer getYssId() {
		return yssId;
	}
	public void setYssId(Integer yssId) {
		this.yssId = yssId;
	}
	public String getYssMc() {
		return yssMc;
	}
	public void setYssMc(String yssMc) {
		this.yssMc = yssMc;
	}
	public Integer getFhdwId() {
		return fhdwId;
	}
	public void setFhdwId(Integer fhdwId) {
		this.fhdwId = fhdwId;
	}
	public String getFhdwMc() {
		return fhdwMc;
	}
	public void setFhdwMc(String fhdwMc) {
		this.fhdwMc = fhdwMc;
	}
	public Integer getShbmId() {
		return shbmId;
	}
	public void setShbmId(Integer shbmId) {
		this.shbmId = shbmId;
	}
	public String getShbmMc() {
		return shbmMc;
	}
	public void setShbmMc(String shbmMc) {
		this.shbmMc = shbmMc;
	}
	public Integer getWzlxId() {
		return wzlxId;
	}
	public void setWzlxId(Integer wzlxId) {
		this.wzlxId = wzlxId;
	}
	public String getWzlxMc() {
		return wzlxMc;
	}
	public void setWzlxMc(String wzlxMc) {
		this.wzlxMc = wzlxMc;
	}
	public Integer getWzId() {
		return wzId;
	}
	public void setWzId(Integer wzId) {
		this.wzId = wzId;
	}
	public String getWzMc() {
		return wzMc;
	}
	public void setWzMc(String wzMc) {
		this.wzMc = wzMc;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	private String cph;
	private Integer lx;//�������� 1.İ������ 2.��ʶ���� 3.��ʶ����
	private Integer wcddcs;
	private Integer yssId;
	private String yssMc;
	private Integer fhdwId;
	private String fhdwMc;
	private Integer shbmId;
	private String shbmMc;
	private Integer wzlxId;
	private String wzlxMc;
	private Integer wzId;
	private String wzMc;
	private String cjsj;
}
