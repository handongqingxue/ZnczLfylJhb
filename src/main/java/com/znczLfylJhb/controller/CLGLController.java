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

@Controller
@RequestMapping("/"+CLGLController.MODULE_NAME)
public class CLGLController {

	@Autowired
	private CheLiangService cheLiangService;
	public static final String MODULE_NAME="clgl";
	
	/**
	 * 跳转到车辆管理-陌生车辆-列表页面
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
	 * 跳转到车辆管理-待识别车辆-编辑页面
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
	 * 跳转到车辆管理-待识别车辆-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/dsbcl/list")
	public String goDsbclList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		request.setAttribute("lx", CheLiang.DAI_SHI_BIE_CHE_LIANG);
		
		return MODULE_NAME+"/dsbcl/list";
	}
	
	/**
	 * 跳转到车辆管理-已识别车辆-编辑页面
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
	 * 跳转到车辆管理-已识别车辆-列表页面
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
	 * 跳转到车辆管理-综合查询-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/zhcx/list")
	public String goZhcxList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/zhcx/list";
	}

	@RequestMapping(value="/editCheLiang")
	@ResponseBody
	public Map<String, Object> editCheLiang(CheLiang cl) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=cheLiangService.edit(cl);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑车辆成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑车辆失败！");
		}
		return jsonMap;
	}

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
}
