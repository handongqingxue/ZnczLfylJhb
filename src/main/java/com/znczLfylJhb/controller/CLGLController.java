package com.znczLfylJhb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znczLfylJhb.entity.*;
import com.znczLfylJhb.service.*;
import com.znczLfylJhb.util.*;

@Controller
@RequestMapping("/"+CLGLController.MODULE_NAME)
public class CLGLController {

	@Autowired
	private CheLiangService cheLiangService;
	public static final String MODULE_NAME="clgl";
	
	/**
	 * ��ת����������-İ������-�б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/mscl/list")
	public String goMsclList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		request.setAttribute("lx", CheLiang.MO_SHENG_CHE_LIANG);
		
		return MODULE_NAME+"/mscl/list";
	}
	
	/**
	 * ��ת����������-��ʶ����-�༭ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/dsbcl/edit")
	public String goDsbclEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		CheLiang cl=cheLiangService.selectById(id);
		request.setAttribute("cl", cl);
		
		return MODULE_NAME+"/dsbcl/edit";
	}
	
	/**
	 * ��ת����������-��ʶ����-�б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/dsbcl/list")
	public String goDsbclList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		request.setAttribute("lx", CheLiang.DAI_SHI_BIE_CHE_LIANG);
		
		return MODULE_NAME+"/dsbcl/list";
	}

	@RequestMapping(value="/ysbcl/new")
	public String goYsbclNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		request.setAttribute("lx", CheLiang.YI_SHI_BIE_CHE_LIANG);
		
		return MODULE_NAME+"/ysbcl/new";
	}
	
	/**
	 * ��ת����������-��ʶ����-�༭ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ysbcl/edit")
	public String goYsbclEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		CheLiang cl=cheLiangService.selectById(id);
		request.setAttribute("cl", cl);
		
		return MODULE_NAME+"/ysbcl/edit";
	}
	
	/**
	 * ��ת����������-��ʶ����-�б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ysbcl/list")
	public String goYsbclList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		request.setAttribute("lx", CheLiang.YI_SHI_BIE_CHE_LIANG);
		
		return MODULE_NAME+"/ysbcl/list";
	}
	
	/**
	 * ��ת����������-�ۺϲ�ѯ-�б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/zhcx/list")
	public String goZhcxList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/zhcx/list";
	}
	
	@RequestMapping(value="/newCheLiang")
	@ResponseBody
	public Map<String, Object> newCheLiang(CheLiang cl) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=cheLiangService.add(cl);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "���������ɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "��������ʧ�ܣ�");
		}
		return jsonMap;
	}

	/**
	 * �༭������Ϣ
	 * @param cl
	 * @return
	 */
	@RequestMapping(value="/editCheLiang")
	@ResponseBody
	public Map<String, Object> editCheLiang(CheLiang cl) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=cheLiangService.edit(cl);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "�༭�����ɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "�༭����ʧ�ܣ�");
		}
		return jsonMap;
	}

	/**
	 * �����ۺϲ�ѯ
	 * @param cph
	 * @param lx
	 * @param yssMc
	 * @param fhdwMc
	 * @param shbmMc
	 * @param wzlxMc
	 * @param wzMc
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/queryZHCXList")
	@ResponseBody
	public Map<String, Object> queryZHCXList(String cph,Integer lx,String yssMc, String fhdwMc,
			String shbmMc, String wzlxMc, String wzMc, int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = cheLiangService.queryForInt(cph, lx, yssMc, fhdwMc, shbmMc, wzlxMc, wzMc);
		List<CheLiang> clList=cheLiangService.queryList(cph, lx, yssMc, fhdwMc, shbmMc, wzlxMc, wzMc, page, rows, sort, order);
		
		jsonMap.put("total", count);
		jsonMap.put("rows", clList);
		
		return jsonMap;
	}

	@RequestMapping(value="/checkCphIfExist",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String checkCphIfExist(String cph) {
		boolean exist=cheLiangService.checkCphIfExist(cph);
		PlanResult plan=new PlanResult();
		String json;
		if(exist) {
			plan.setStatus(0);
			plan.setMsg("���ƺ��Ѵ���");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
}
