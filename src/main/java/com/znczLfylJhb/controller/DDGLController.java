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
 * 订单状态：一检排队中-一检上磅-二检排队中-二检上磅-已完成
 * */
@Controller
@RequestMapping("/"+DDGLController.MODULE_NAME)
public class DDGLController {

	@Autowired
	private DingDanService dingDanService;
	@Autowired
	private DingDanZhuangTaiService dingDanZhuangTaiService;
	@Autowired
	private RglrCphJiLuService rglrCphJiLuService;
	@Autowired
	private BangDanJiLuService bangDanJiLuService;
	@Autowired
	private GuoBangJiLuService guoBangJiLuService;
	public static final String MODULE_NAME="ddgl";
	
	/**
	 * 跳转到订单管理-订单状态-添加页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ddzt/new")
	public String goDdztNew(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/ddzt/new";
	}
	
	/**
	 * 跳转到订单管理-订单状态-编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ddzt/edit")
	public String goDdztEdit(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		DingDanZhuangTai ddzt=dingDanZhuangTaiService.selectById(id);
		request.setAttribute("ddzt", ddzt);
		
		return MODULE_NAME+"/ddzt/edit";
	}

	/**
	 * 跳转到订单管理-订单状态-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ddzt/list")
	public String goDdztList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/ddzt/list";
	}
	
	/**
	 * 跳转到订单管理-订单状态-详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ddzt/detail")
	public String goDdztDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		DingDanZhuangTai ddzt=dingDanZhuangTaiService.selectById(id);
		request.setAttribute("ddzt", ddzt);
		
		return MODULE_NAME+"/ddzt/detail";
	}

	/**
	 * 跳转到订单管理-综合查询-列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/zhcx/list")
	public String goZhcxList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		request.setAttribute("yjpdzDdztMc", DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
		request.setAttribute("yjsbDdztMc", DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
		request.setAttribute("ejpdzDdztMc", DingDanZhuangTai.ER_JIAN_PAI_DUI_ZHONG_TEXT);
		request.setAttribute("ejsbDdztMc", DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
		
		return MODULE_NAME+"/zhcx/list";
	}
	
	/**
	 * 跳转到订单管理-综合查询-详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/zhcx/detail")
	public String goZhcxDetail(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		String id = request.getParameter("id");
		DingDan dd=dingDanService.selectById(id);
		request.setAttribute("dd", dd);
		
		return MODULE_NAME+"/zhcx/detail";
	}
	
	/**
	 * 添加订单状态
	 * @param ddzt
	 * @return
	 */
	@RequestMapping(value="/newDingDanZhuangTai")
	@ResponseBody
	public Map<String, Object> newDingDanZhuangTai(DingDanZhuangTai ddzt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=dingDanZhuangTaiService.add(ddzt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "创建订单状态成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "创建订单状态失败！");
		}
		return jsonMap;
	}
	
	/**
	 * 编辑订单状态
	 * @param ddzt
	 * @return
	 */
	@RequestMapping(value="/editDingDanZhuangTai")
	@ResponseBody
	public Map<String, Object> editDingDanZhuangTai(DingDanZhuangTai ddzt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=dingDanZhuangTaiService.edit(ddzt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑订单状态成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑订单状态失败！");
		}
		return jsonMap;
	}
	
	/**
	 * 查询订单状态列表
	 * @param mc
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
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

	/**
	 * 删除订单
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteDingDan",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteDingDan(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=dingDanService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除订单失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除订单成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	/**
	 * 订单综合查询
	 * @param ddh
	 * @param ddztId
	 * @param ddztMc
	 * @param cph
	 * @param yssMc
	 * @param wzMc
	 * @param fhdwMc
	 * @param shbmMc
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/queryZHCXList")
	@ResponseBody
	public Map<String, Object> queryZHCXList(String ddh,Integer ddztId,String ddztMc,String cph,String yssMc,String wzMc,
			String fhdwMc,String shbmMc,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = dingDanService.queryZHCXForInt(ddh,ddztId,ddztMc,cph,yssMc,wzMc,fhdwMc,shbmMc);
			List<DingDan> zhglList=dingDanService.queryZHCXList(ddh,ddztId,ddztMc,cph,yssMc,wzMc,fhdwMc,shbmMc, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", zhglList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	

	/**
	 * 根据订单id复位订单
	 * @param dd
	 * @param jyFlag
	 * @return
	 */
	@RequestMapping(value="/fwddById",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String fwddById(DingDan dd,int jyFlag) {

		PlanResult plan=new PlanResult();
		String json;
		boolean bool=false;
		int count=dingDanService.edit(dd);
		if(jyFlag==GuoBangJiLu.RU_CHANG_GUO_BANG) {//若是入厂过磅（一检过磅），需要检查有无磅单记录。有的话删除磅单记录，等再次过磅后再生成新的磅单记录。此操作仅在入厂过磅时才执行
			bool=bangDanJiLuService.checkIfExistByDdId(dd.getId());
			if(bool)
				bangDanJiLuService.deleteByDdId(dd.getId());
		}
		bool=guoBangJiLuService.checkIfExistByDdId(jyFlag,dd.getId());
		if(bool)
			guoBangJiLuService.deleteByDdId(jyFlag,dd.getId());
			
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("订单复位失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("订单复位成功，请车辆重新排队上磅");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	/**
	 * 查询订单状态下拉列表信息
	 * @return
	 */
	@RequestMapping(value="/queryDingDanZhuangTaiCBBList")
	@ResponseBody
	public Map<String, Object> queryDingDanZhuangTaiCBBList() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<DingDanZhuangTai> ddztList=dingDanZhuangTaiService.queryCBBList();
		
		jsonMap.put("rows", ddztList);
		
		return jsonMap;
	}
	
	/**
	 * 查询选择车牌号下拉框信息
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/queryXzCphCBBList")
	@ResponseBody
	public Map<String, Object> queryXzCphCBBList(int page,int rows,String sort,String order) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<String> cphList=rglrCphJiLuService.queryXzCphCBBList(page, rows, sort, order);
		
		jsonMap.put("rows", cphList);
		
		return jsonMap;
	}
	
	/**
	 * 查询录入省简称下拉列表信息
	 * @param q
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/queryLrSjcCBBList")
	@ResponseBody
	public List<RglrCphJiLu> queryLrSjcCBBList(String q,int page,int rows,String sort,String order) {

		List<RglrCphJiLu> sjcList=rglrCphJiLuService.queryLrSjcCBBList(q, page, rows, sort, order);
		
		return sjcList;
	}
	
	/**
	 * 查询录入完成车牌号下拉列表信息
	 * @param sjc
	 * @param q
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/queryLrWscphCBBList")
	@ResponseBody
	public List<RglrCphJiLu> queryLrWscphCBBList(String sjc,String q,int page,int rows,String sort,String order) {

		List<RglrCphJiLu> cphList=rglrCphJiLuService.queryLrWscphCBBList(sjc, q, page, rows, sort, order);
		
		return cphList;
	}
}
