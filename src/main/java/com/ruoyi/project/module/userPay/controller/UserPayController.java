package com.ruoyi.project.module.userPay.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.project.module.common.*;
import com.ruoyi.project.module.util.*;
import com.ruoyi.project.module.wechat.service.IWeChatAppLoginService;
import com.ruoyi.project.module.wxpay.sdk.WXMyConfigUtil;
import com.ruoyi.project.system.user.domain.User;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.module.userPay.domain.UserPay;
import com.ruoyi.project.module.userPay.service.IUserPayService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商户充值 信息操作处理
 * 
 * @author snailever
 * @date 2018-10-11
 */
@Controller
@RequestMapping("/module/userPay")
public class UserPayController extends BaseController
{
    private String prefix = "module/userPay";
	private static final Logger lg = LoggerFactory.getLogger(HttpUtil.class);
	@Autowired
	private IUserPayService userPayService;
	@Autowired
	IWeChatAppLoginService weChatAppLoginService;

	@RequiresPermissions("module:userPay:view")
	@GetMapping()
	public String userPay( ModelMap mmap)
	{
		User user =getUser();
		List<UserPay> userPays= userPayService.selectUserPayByUserId(user.getUserId().intValue());
		UserPay userPay = new UserPay();
		if(userPays.size()>0){
			userPay = userPays.get(0);
		}
		mmap.put("userPay", userPay);
		mmap.put("user", user);
		return prefix + "/add";
	}
	
	/**
	 * 查询商户充值列表
	 */
//	@RequiresPermissions("module:userPay:list")
//	@PostMapping("/list")
//	@ResponseBody
//	public TableDataInfo list(UserPay userPay)
//	{
//		startPage();
//        List<UserPay> list = userPayService.selectUserPayList(userPay);
//		return getDataTable(list);
//	}
	
	/**
	 * 新增商户充值
	 */
	@GetMapping("/list")
	public String add()
	{
	    return prefix + "/add";
	}
	/**
	 * pc端微信支付之后的回调方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="wechat_notify_url_pc",method=RequestMethod.POST)
	public void wechat_notify_url_pc(HttpServletRequest request,HttpServletResponse response) throws Exception{

		//读取参数
		InputStream inputStream ;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s ;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((s = in.readLine()) != null){
			sb.append(s);
		}
		in.close();
		inputStream.close();

		//解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());

		//过滤空 设置 TreeMap
		SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
		Iterator<String> it = m.keySet().iterator();
		while (it.hasNext()) {
			String parameter = it.next();
			String parameterValue = m.get(parameter);

			String v = "";
			if(null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}
		// 微信支付的API密钥
		String key = WeChatConfig.APIKEY; // key

		lg.info("微信支付返回回来的参数："+packageParams);
		//判断签名是否正确
		if(PayForUtil.isTenpaySign("UTF-8", packageParams,key)) {
			//------------------------------
			//处理业务开始
			//------------------------------
			String resXml = "";
			if("SUCCESS".equals((String)packageParams.get("result_code"))){
				// 这里是支付成功
				//执行自己的业务逻辑开始
				String app_id = (String)packageParams.get("appid");
				String mch_id = (String)packageParams.get("mch_id");
				String openid = (String)packageParams.get("openid");
				String is_subscribe = (String)packageParams.get("is_subscribe");//是否关注公众号

				//附加参数【商标申请_0bda32824db44d6f9611f1047829fa3b_15460】--【业务类型_会员ID_订单号】
				String attach = (String)packageParams.get("attach");
				//商户订单号
				String out_trade_no = (String)packageParams.get("out_trade_no");
				//付款金额【以分为单位】
				String total_fee = (String)packageParams.get("total_fee");
				//微信生成的交易订单号
				String transaction_id = (String)packageParams.get("transaction_id");//微信支付订单号
				//支付完成时间
				String time_end=(String)packageParams.get("time_end");

				lg.info("app_id:"+app_id);
				lg.info("mch_id:"+mch_id);
				lg.info("openid:"+openid);
				lg.info("is_subscribe:"+is_subscribe);
				lg.info("out_trade_no:"+out_trade_no);
				lg.info("total_fee:"+total_fee);
				lg.info("额外参数_attach:"+attach);
				lg.info("time_end:"+time_end);

				//执行自己的业务逻辑结束
				lg.info("支付成功");
				//通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

			} else {
				lg.info("支付失败,错误信息：" + packageParams.get("err_code"));
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			//------------------------------
			//处理业务完毕
			//------------------------------
			BufferedOutputStream out = new BufferedOutputStream(
					response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} else{
			lg.info("通知签名验证失败");
		}

	}

//	/**
//	 * 新增保存商户充值
//	 */
//	@RequiresPermissions("module:userPay:add")
//	@Log(title = "商户充值", businessType = BusinessType.INSERT)
//	@PostMapping("/add")
//	@ResponseBody
//	public AjaxResult addSave(UserPay userPay)
//	{
//		System.out.print("wwwww");
//		return toAjax(userPayService.insertUserPay(userPay));
//	}

	//微信支付接口
	@RequestMapping("/wxPay")
	public String wxPay(WeChatParams ps) throws Exception {
		ps.setBody("测试商品3");
		ps.setTotal_fee("1");
		ps.setOut_trade_no("hw5409550792199899");
		ps.setAttach("xiner");
		ps.setMemberid("888");
		String urlCode = WeixinPay.getCodeUrl(ps);
		System.out.println(urlCode);
		return "";

	}
	/**
	 * 新增保存商户充值
	 */
	@RequiresPermissions("module:userPay:add")
	@Log(title = "商户充值", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	public String orderPay(@RequestParam(required = true,value = "userId")String userId,

						   @RequestParam(required = true,value = "totalFee")Integer totalFee,
						   HttpServletRequest req, HttpServletResponse response, ModelMap mmap) throws Exception {
		System.err.println("进入微信支付申请");
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		String out_trade_no=hehe+"wxpay";
		Integer fee =totalFee.intValue()*100;
		String attach=totalFee*100+","+userId;
		WeChatParams ps = new WeChatParams();
		ps.setBody("微信支付"+totalFee);
		ps.setTotal_fee(fee.toString());
		ps.setOut_trade_no(out_trade_no);
		ps.setAttach(attach);
		ps.setMemberid("888");
		String urlCode = WeixinPay.getCodeUrl(ps);
		mmap.put("urlCode",urlCode);
		User user =getUser();
		List<UserPay> userPays= userPayService.selectUserPayByUserId(user.getUserId().intValue());
		UserPay userPay = new UserPay();
		if(userPays.size()>0){
			userPay = userPays.get(0);
		}
		mmap.put("userPay", userPay);
		mmap.put("user", user);
		return prefix + "/add";

	}

	/**
	 * 订单支付异步通知
	 */
	@ApiOperation(value = "手机订单支付完成后回调")
	@RequestMapping(value = "/notify",method = {RequestMethod.GET, RequestMethod.POST})
	public String WXPayBack(HttpServletRequest request,HttpServletResponse response,ModelMap mmap) {
		String resXml = "";
		System.err.println("进入异步通知");
		try {
			//
			InputStream is = request.getInputStream();
			//将InputStream转换成String
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			resXml = sb.toString();
			System.err.println(resXml);
			String result = weChatAppLoginService.payBack(resXml);
//            return "<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg></xml>";
			return result;
		} catch (Exception e) {
			//logger.error("手机支付回调通知失败",e);
			String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			return result;
		}
	}

	/**
	 * 此函数会被执行多次，如果支付状态已经修改为已支付，则下次再调的时候判断是否已经支付，如果已经支付了，则什么也执行
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 */
	@RequestMapping(value = "bizook", produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestDescription("支付回调地址")
	@ResponseBody
	public String notifyWeiXinPay(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
		System.out.println("微信支付回调");
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		String resultxml = new String(outSteam.toByteArray(), "utf-8");
		Map<String, String> params = PayCommonUtil.doXMLParse(resultxml);
		outSteam.close();
		inStream.close();


		Map<String,String> return_data = new HashMap<String,String>();
		if (!PayCommonUtil.isTenpaySign(params)) {
			// 支付失败
			return_data.put("return_code", "FAIL");
			return_data.put("return_msg", "return_code不正确");
			return StringUtil.GetMapToXML(return_data);
		} else {
			System.out.println("===============付款成功==============");
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			// 此处处理订单状态，结合自己的订单数据完成订单状态的更新
			// ------------------------------

			String total_fee = params.get("total_fee");
			double v = Double.valueOf(total_fee) / 100;
			String out_trade_no = String.valueOf(Long.parseLong(params.get("out_trade_no").split("O")[0]));
			Date accountTime = DateUtil.stringtoDate(params.get("time_end"), "yyyyMMddHHmmss");
			String ordertime = DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
			String totalAmount = String.valueOf(v);
			String appId = params.get("appid");
			String tradeNo = params.get("transaction_id");

			return_data.put("return_code", "SUCCESS");
			return_data.put("return_msg", "OK");
			return StringUtil.GetMapToXML(return_data);
		} }



	/**
	 * 修改商户充值
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		UserPay userPay = userPayService.selectUserPayById(id);
		mmap.put("userPay", userPay);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商户充值
	 */
	@RequiresPermissions("module:userPay:edit")
	@Log(title = "商户充值", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(UserPay userPay)
	{		
		return toAjax(userPayService.updateUserPay(userPay));
	}
	
	/**
	 * 删除商户充值
	 */
	@RequiresPermissions("module:userPay:remove")
	@Log(title = "商户充值", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(userPayService.deleteUserPayByIds(ids));
	}
	
}
