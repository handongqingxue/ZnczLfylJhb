package com.znczLfylJhb.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.znczLfylJhb.service.*;
import com.znczLfylJhb.util.*;

import net.sf.json.JSONObject;

import com.znczLfylJhb.entity.*;

/**
 * ����״̬��һ���Ŷ���-һ���ϰ�-�����Ŷ���-�����ϰ�-�����
 * */
@Controller
@RequestMapping("/"+DDGLController.MODULE_NAME)
public class DDGLController {

	@Autowired
	private DingDanZhuangTaiService dingDanZhuangTaiService;
	public static final String MODULE_NAME="ddgl";
	
	@RequestMapping(value="/ddzt/new")
	public String goDdztNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/ddzt/new";
	}
	
	@RequestMapping(value="/ddzt/edit")
	public String goDdztEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		DingDanZhuangTai ddzt=dingDanZhuangTaiService.selectById(id);
		request.setAttribute("ddzt", ddzt);
		
		return MODULE_NAME+"/ddzt/edit";
	}

	/**
	 * ��ת����������-����״̬-�б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ddzt/list")
	public String goDdztList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/ddzt/list";
	}
	
	@RequestMapping(value="/ddzt/detail")
	public String goDdztDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		DingDanZhuangTai ddzt=dingDanZhuangTaiService.selectById(id);
		request.setAttribute("ddzt", ddzt);
		
		return MODULE_NAME+"/ddzt/detail";
	}
	
	@RequestMapping(value="/newDingDanZhuangTai")
	@ResponseBody
	public Map<String, Object> newDingDanZhuangTai(DingDanZhuangTai ddzt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=dingDanZhuangTaiService.add(ddzt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "��������״̬�ɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "��������״̬ʧ�ܣ�");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/editDingDanZhuangTai")
	@ResponseBody
	public Map<String, Object> editDingDanZhuangTai(DingDanZhuangTai ddzt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=dingDanZhuangTaiService.edit(ddzt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "�༭����״̬�ɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "�༭����״̬ʧ�ܣ�");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryDDZTList")
	@ResponseBody
	public Map<String, Object> queryDDZTList(String mc,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = dingDanZhuangTaiService.queryForInt(mc);
			List<DingDanZhuangTai> ddztList=dingDanZhuangTaiService.queryList(mc, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", ddztList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
}
