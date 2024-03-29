package com.znczLfylJhb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znczLfylJhb.entity.*;
import com.znczLfylJhb.service.*;
import com.znczLfylJhb.socket.*;

//机器码
@Controller
@RequestMapping("/gkj")
public class GKJController {
	
	@Autowired
	private DingDanService dingDanService;
	@Autowired
	private CheLiangService cheLiangService;
	@Autowired
	private BangDanJiLuService bangDanJiLuService;
	@Autowired
	private GuoBangJiLuService guoBangJiLuService; 
	@Autowired
	private RglrCphJiLuService rglrCphJiLuService;

	@RequestMapping(value="/addDingDan")
	@ResponseBody
	public Map<String, Object> addDingDan(String cph) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			boolean exist=cheLiangService.checkIfExistByCph(cph);
			if(!exist) {
				CheLiang cl=new CheLiang();
				cl.setCph(cph);
				cl.setLx(CheLiang.MO_SHENG_CHE_LIANG);
				cheLiangService.add(cl);
			}
			
			DingDan dd=new DingDan();
			String ddh=dingDanService.createDdhByDateYMD();
			dd.setDdh(ddh);
			int clId=cheLiangService.getIdByCph(cph);
			dd.setClId(clId);
			int count=dingDanService.add(dd);
			if(count>0) {
				jsonMap.put("status", "ok");
				jsonMap.put("message", "创建订单成功！");
			}
			else {
				jsonMap.put("status", "no");
				jsonMap.put("message", "创建订单失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonMap;
	}

	@RequestMapping(value="/getDingDan")
	@ResponseBody
	public Map<String, Object> getDingDan(String cph,String ddztMc) {
		
		System.out.println("cph==="+cph);
		System.out.println("ddztMc==="+ddztMc);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		DingDan dd = dingDanService.getDingDan(cph, ddztMc);
		
		if(dd==null) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "没找到相关订单");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("dingDan", dd);
		}
		
		return jsonMap;
	}

	@RequestMapping(value="/getDingDanByZt")
	@ResponseBody
	public Map<String, Object> getDingDanByZt(Integer yjbfh,Integer ejbfh,String ddztMc,Integer yjzt,Integer ejzt) {
		
		System.out.println("yjbfh==="+yjbfh);
		System.out.println("ejbfh==="+ejbfh);
		System.out.println("ddztMc==="+ddztMc);
		System.out.println("yjzt==="+yjzt);
		System.out.println("ejzt==="+ejzt);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		DingDan dd = dingDanService.getByZt(yjbfh,ejbfh,ddztMc,yjzt,ejzt);
		
		if(dd==null) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "没找到相关订单");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("dingDan", dd);
		}
		
		return jsonMap;
	}

	@RequestMapping(value="/getPDZDingDanByCph")
	@ResponseBody
	public Map<String, Object> getPDZDingDanByCph(String cph) {

		System.out.println("cph==="+cph);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		DingDan dd = dingDanService.getPDZByCph(cph);
		
		if(dd==null) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "没找到相关订单");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("dingDan", dd);
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/editDingDan")
	@ResponseBody
	public Map<String, Object> editDingDan(DingDan dd,
			HttpServletRequest request) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			int count=dingDanService.edit(dd);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "编辑订单成功！");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "编辑订单失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/editDingDanByZt")
	@ResponseBody
	public Map<String, Object> editDingDanByZt(DingDan dd,
			HttpServletRequest request) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			int count=dingDanService.editByZt(dd);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "编辑订单成功！");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "编辑订单失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonMap;
	}

	@RequestMapping(value="/newBangDanJiLu")
	@ResponseBody
	public Map<String, Object> newBangDanJiLu(BangDanJiLu bdjl) {

		System.out.println("ddId==="+bdjl.getDdId());
		System.out.println("yjzl==="+bdjl.getYjzl());
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = bangDanJiLuService.add(bdjl);
		
		if(count==0) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "创建磅单信息失败！");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("message", "创建磅单信息成功！");
		}
		
		return jsonMap;
	}

	@RequestMapping(value="/editBangDanJiLu")
	@ResponseBody
	public Map<String, Object> editBangDanJiLu(BangDanJiLu bdjl) {

		System.out.println("ddId==="+bdjl.getDdId());
		System.out.println("ejzl==="+bdjl.getEjzl());
		System.out.println("mz==="+bdjl.getMz());
		System.out.println("pz==="+bdjl.getPz());
		System.out.println("jz==="+bdjl.getJz());
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		int count=bangDanJiLuService.edit(bdjl);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "编辑磅单信息成功！");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "编辑磅单信息失败！");
		}
		return jsonMap;
	}

	@RequestMapping(value="/selectBangDanJiLuByDdId")
	@ResponseBody
	public Map<String, Object> selectBangDanJiLuByDdId(Integer ddId) {

		System.out.println("ddId==="+ddId);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			BangDanJiLu bdjl = bangDanJiLuService.selectByDdId(ddId);
			if(bdjl==null) {
				jsonMap.put("status", "no");
				jsonMap.put("message", "找不到相关磅单！");
			}
			else {
				jsonMap.put("status", "ok");
				jsonMap.put("bdjl", bdjl);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}

	@RequestMapping(value="/newGuoBangJiLu")
	@ResponseBody
	public Map<String, Object> newGuoBangJiLu(GuoBangJiLu gbjl) {

		Map<String, Object> jsonMap=new HashMap<String, Object>();
		try {
			int count=guoBangJiLuService.add(gbjl);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "创建过磅信息成功！");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "创建过磅信息失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}

	@RequestMapping(value="/selectGuoBangJiLuByDdId")
	@ResponseBody
	public Map<String, Object> selectGuoBangJiLuByDdId(Integer ddId, Integer gblx) {

		System.out.println("ddId==="+ddId);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		GuoBangJiLu gbjl = guoBangJiLuService.selectPrintInfo(ddId,gblx);
		if(gbjl==null) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "找不到相关过磅记录！");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("gbjl", gbjl);
		}
		return jsonMap;
	}

	@RequestMapping(value="/checkDingDanIfExistByZt")
	@ResponseBody
	public Map<String, Object> checkDingDanIfExistByZt(Integer checkBfh,String checkDdztMc) {
		
		System.out.println("checkBfh==="+checkBfh);
		System.out.println("checkDdztMc==="+checkDdztMc);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		boolean bool=dingDanService.checkIfExistByZt(checkBfh,checkDdztMc);
		if(bool) {
			jsonMap.put("status", "ok");
			jsonMap.put("message", "订单已存在！");
		}
		else {
			jsonMap.put("status", "no");
		}
		return jsonMap;
	}


	/**
	 * 根据车牌号验证车辆是否可以上磅
	 * @param cph
	 * @return
	 */
	@RequestMapping(value="/checkIfCPSB")
	@ResponseBody
	public Map<String, Object> checkIfCPSB(String cph) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		boolean bool=dingDanService.checkIfCPSB(cph);
		if(bool) {
			jsonMap.put("status", "ok");
		}
		else {
			jsonMap.put("status", "no");
		}
		return jsonMap;
	}

	@RequestMapping(value="/getCphByBfhDdzt")
	@ResponseBody
	public Map<String, Object> getCphByBfhDdzt(Integer bfh, String ddztMc) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		String cph=dingDanService.getCphByBfhDdzt(bfh, ddztMc);
		if(StringUtils.isEmpty(cph)) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "车牌号不存在！");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("cph", cph);
		}
		return jsonMap;
	}

	@RequestMapping(value="/updateCheLiangWcddcsByCph")
	@ResponseBody
	public Map<String, Object> updateCheLiangWcddcsByCph(String cph) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=cheLiangService.updateWcddcsByCph(cph);
		if(count>0) {
			jsonMap.put("status", "ok");
			jsonMap.put("message", "修改完成订单次数成功！");
		}
		else {
			jsonMap.put("status", "no");
		}
		return jsonMap;
	}

	@RequestMapping(value="/sendCphToClient")
	@ResponseBody
	public Map<String, Object> sendCphToClient(String cph,Integer bfNoFlag) {
		
		System.out.println("sendCphToClient.bfNoFlag==="+bfNoFlag);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		//ProxySet.sayToClient("鲁A9031", SocketProxy.YI_JIAN);
		String mesJO="{\"action\":\"pushCph\",\"cph\":\" "+cph+"\"}";
		ProxySet.sayToClient(mesJO, bfNoFlag==1?SocketProxy.YI_HAO_BANG_FANG:SocketProxy.ER_HAO_BANG_FANG);
		
		jsonMap.put("status", "ok");
		
		return jsonMap;
	}

	@RequestMapping(value="/addRglrCphJiLu")
	@ResponseBody
	public Map<String, Object> addRglrCphJiLu(String cph,Integer ddId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=0;
		boolean exist=rglrCphJiLuService.checkIfExistByDdIdCph(ddId,cph);//验证同一个订单是否存在该车牌号，存在则说明之前录入过了，不需要再生成车牌号记录了，反之则需要生成
		if(!exist) {
			RglrCphJiLu rglrCphJiLu=new RglrCphJiLu();
			rglrCphJiLu.setCph(cph);
			rglrCphJiLu.setDdId(ddId);
			count=rglrCphJiLuService.add(rglrCphJiLu);
		}

		if(count>0)
			jsonMap.put("status", "ok");
		else
			jsonMap.put("status", "no");
		
		return jsonMap;
	}
	
	public static void main(String[] args) {
		
	}
}
