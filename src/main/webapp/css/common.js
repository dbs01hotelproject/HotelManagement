        /**************************COOKIE操作相关START**************************/
/*
 添加COOKIE
 name：名称
 value：值
 expiresHours：过期时间（0：代表不设定过期时间，即当浏览器关闭时cookie自动消失）
 * */

function addCookie(name, value, expiresHours) {
	var cookieString = name + "=" + escape(value);
	//判断是否设置过期时间
	if(expiresHours > 0) {
		var date = new Date();
		date.setTime(date.getTime + expiresHours * 3600 * 1000);
		cookieString = cookieString + "; expires=" + date.toGMTString();
	}
	document.cookie = cookieString;
};

/*
 获取对应名称的COOKIE
 * */
function getCookie(name) {
	var strCookie = document.cookie;
	var arrCookie = strCookie.split("; ");
	for(var i = 0; i < arrCookie.length; i++) {
		var arr = arrCookie[i].split("=");
		if(arr[0] == name)
			return unescape(arr[1]);
	}
	return "";
};
/**************************COOKIE操作相关END**************************/

/**************************页面操作相关START**************************/
/*
 *弹出页面
 * tittle：标题
 * content：内容页url
 * id:数据id
 * width:宽度
 * height：高度
 */
function pageOpen(tittle, content, id, width, height) {
	if(id == null || id == undefined) {
		id = 0;
	}
	layer.open({
		type: 2,
		title: tittle,
		area: [width, height],
		shift: 0,
		maxmin: true,
		skin: 'layui-layer-rim', //加上边框
		content: content + "?timestamp=" + new Date().getTime() + "&id=" + id
	});
}

/*
 *弹出页面（父页面）
 * tittle：标题
 * content：内容页url
 * id:数据id
 * width:宽度
 * height：高度
 */
function pageOpenByParent(tittle, content, id, width, height) {
	if(id == null || id == undefined) {
		id = 0;
	}
	parent.layer.open({
		type: 2,
		title: tittle,
		area: [width, height],
		shift: 0,
		fix: false, //不固定
		maxmin: true,
		skin: 'layui-layer-rim', //加上边框
		content: content + "?timestamp=" + new Date().getTime() + "&id=" + id
	});
}

/*
 *弹出页面（父页面）只有关闭按钮
 * tittle：标题
 * content：内容页url
 * id:数据id
 * width:宽度
 * height：高度
 */
function pageOpenByParentnotitle(tittle, content, id, width, height) {
	if(id == null || id == undefined) {
		id = 0;
	}
	parent.layer.open({
		type: 2,
		title: "",
		area: [width, height],
		shift: 0,
		fix: false, //不固定
		maxmin: false,
		skin: 'layui-layer-rim', //加上边框
		content: content + "?timestamp=" + new Date().getTime() + "&id=" + id
	});
}

/* 直接关闭弹出框
 */
function closePop() {
	// 获取当前页面引索
	var indexmain = parent.layer.getFrameIndex(window.name); //获取窗口索引
	// 关闭当前页面
	parent.layer.close(indexmain);
}

/**
 * 关闭Pop页面并且刷新主页面
 *
 * @param {Object}
 *            title 提示信息
 */
function saveAndClosePopAndRefreshParent(title) {
	// 提示保存成功
	parent.layer.alert(title || '保存成功！', {
		icon: 1,
		closeBtn: 0
	}, function(index) {
		// 关闭保存成功页面
		parent.layer.close(index);
		// 获取当前页面引索
		var indexmain = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		// 触发主页面按钮事件,根据不同业务可以在自己页面修改该方法
		if(parent.getData && typeof(parent.getData) == "function") parent.getData();
		// 关闭当前页面
		parent.layer.close(indexmain);
	});
}

/**************************页面操作相关END**************************/

/**************************页面提示相关START**************************/

// 出错信息
function layer_msg(str) {
	layer.msg(str, function() {});
}

/* 弹出成功信息
 */
function alertSuccess(str) {
	// 弹出提示框
	layer.alert(str, {
		icon: 1
	});
}

/* 弹出全局成功信息
 */
function alertParentSuccess(str) {
	// 弹出提示框
	parent.layer.alert(str, {
		icon: 1
	});
}
/* 弹出警告信息
 * str：警告信息
 */
function alertWarning(str) {
	// 弹出提示框
	layer.alert(str, {
		icon: 5
	});
}
/* 弹出全局警告信息
 */
function alertParentWarning(str) {
	// 弹出提示框
	parent.layer.alert(str, {
		icon: 5
	});
}

/* 弹出失败信息
 */
function alertError(str) {
	// 弹出提示框
	layer.alert(str, {
		icon: 2
	});
}
/* 弹出全局失败信息
 */
function alertParentError(str) {
	// 弹出提示框
	parent.layer.alert(str, {
		icon: 2
	});
}

/* 确认删除
 * id:删除id
 * title：删除标题
 * content：删除提醒内容
 */
function deleteAndConfirm(id, title, content) {
	// 弹出提示框
	layer.confirm(content || '您确定要删除这些信息吗?', {
		icon: 3,
		title: title || '删除'
	}, function(index) {
		deleteData(id);
		// 关闭提示框
		layer.close(index);
	});
}

function saveSuccess(title) {
	// 提示保存成功
	layer.alert(title || '保存成功！', {
		icon: 1,
		closeBtn: 0
	}, function(index) {
		// 关闭保存成功页面
		layer.close(index);
	});
}

/**************************页面提示相关END**************************/

/**************************判断为空等状态START**************************/

/*提示不能为空
 *    name:元素名称
 *tip: 提示内容

 */
function showNotEmpty(name, tip) {
	if($("#" + name).val().toString().trim() == "") {
		layer.tips(tip || '该项目不能为空！', '#' + name, {
			tipsMore: true,
			tips: 3
		});
		$('#' + name).focus();
		return true;
	} else {
		return false;
	}
}
/*提示不能为空
 *    name:元素名称
 *tip: 提示内容
 */
function showErrorFormat(name, tip) {
	layer.tips(tip || '该项目格式不正确！', '#' + name, {
		tipsMore: true,
		tips: 3
	});
}

/*
两值相比较
*_val1:比较值1
*_val2:默认值2
*tip:提示内容
*/
function showNotEmptyWithDDL(_val1, _val2, name, tip) {

	if(_val1 == _val2) {
		layer.tips(tip, '#' + name, {
			tipsMore: true,
			tips: 3
		});
		return true;
	} else {
		return false;
	}
}

/**************************判断为空等状态END**************************/

/**************************日期相关操作START**************************/
/******************************************
 ** 格式化日期
 ** new Date().Format("yyyy-MM-dd")
 ** new Date().Format("yyyy-MM-dd hh:mm:ss")
 ******************************************/
Date.prototype.Format = function(fmt) {
	var o = {
		"M+": this.getMonth() + 1, //月份
		"d+": this.getDate(), //日
		"h+": this.getHours(), //小时
		"m+": this.getMinutes(), //分
		"s+": this.getSeconds(), //秒
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度
		"S": this.getMilliseconds() //毫秒
	};
	if(/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
/**
 * 时分比较大小，有秒忽略
 * param value1 格式 hh:mm 或 hh:mm:ss
 * param value2 格式 hh:mm 或 hh:mm:ss
 * return -1:value1<value2 0:value1=value2 1:value1>value2
 */
function compareHoursAndMinutes(value1, value2) {
	var reg = /^([01][0-9]|2[0-3])(:[0-5][0-9]){1,2}$/;
	var re = new RegExp(reg);
	if(!re.test(value1) || !re.test(value2)) {
		//layer.msg('时分秒格式不正确');
		return 'error';
	}
	var time1 = value1.split(":");
	var time2 = value2.split(":");
	// 小时比较
	if(parseInt(time1[0]) < parseInt(time2[0])) {
		return -1;
	} else if(parseInt(time1[0]) == parseInt(time2[0])) {
		// 分钟比较
		if(parseInt(time1[1]) < parseInt(time2[1])) {
			return -1;
		} else if(parseInt(time1[1]) == parseInt(time2[1])) {
			return 0;
		} else {
			return 1;
		}
	} else {
		return 1;
	}
};

/**
 * 年月日时分比较大小，有秒忽略
 * param value1 格式 yyyy-MM-dd hh:mm 或 yyyy-MM-dd hh:mm:ss
 * param value2 格式 yyyy-MM-dd hh:mm 或 yyyy-MM-dd hh:mm:ss
 * return -1:value1<value2 0:value1=value2 1:value1>value2
 */
function compareFormatTime(value1, value2) {
	var reg = /^\d{4}-(0\d|1[0-2])-([0-2]\d|3[01])( ([01]\d|2[0-3])\:[0-5]\d\:[0-5]\d)$/;
	var re = new RegExp(reg);
	if(!re.test(value1) || !re.test(value2)) {
		//layer.msg('时间格式不正确');
		return 'error';
	}
	var time1 = value1.substr(0, 10).split("-");
	var time2 = value2.substr(0, 10).split("-");
	// 年份比较
	if(parseInt(time1[0]) < parseInt(time2[0])) {
		return -1;
	} else if(parseInt(time1[0]) == parseInt(time2[0])) {
		// 月份比较
		if(parseInt(time1[1]) < parseInt(time2[1])) {
			return -1;
		} else if(parseInt(time1[1]) == parseInt(time2[1])) {
			// 日期比较
			if(parseInt(time1[2]) < parseInt(time2[2])) {
				return -1;
			} else if(parseInt(time1[2]) == parseInt(time2[2])) {
				// 时间比较
				return compareHoursAndMinutes(value1.substr(11, 10), value2.substr(11, 10))
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	} else {
		return 1;
	}
};
/*
 * 判断是否闰平年
 * 
 */
function IsPinYear(year) {
	return(0 == year % 4 && (year % 100 != 0 || year % 400 == 0));
}
/* 
 * 日期加减
 * date：日期
 * days：天数
 */
function addDate(date, days) {
	var d = new Date(date);
	d.setDate(d.getDate() + parseInt(days));
	var month = d.getMonth() + 1;
	var day = d.getDate();
	if(month < 10) {
		month = "0" + month;
	}
	if(day < 10) {
		day = "0" + day;
	}
	var val = d.getFullYear() + "-" + month + "-" + day;
	return val;
}

/*
 * 月份加减
 * date：日期
 * months：月数 
 */
function addMonth(date, months) {
	var d = new Date(date);
	d.setMonth(d.getMonth() + parseInt(months));
	var month = d.getMonth() + 1;
	var day = d.getDate();
	if(month < 10) {
		month = "0" + month;
	}
	if(day < 10) {
		day = "0" + day;
	}
	var val = d.getFullYear() + "-" + month + "-" + day;
	return val;
}

/**************************日期相关操作END**************************/

/**************************金额相关操作START**************************/
/*
 * 金钱正则表达式
 * elem：元素id
 * integerlength：整数长度
 * decimallength：小数长度
 */
//function checkPrice(elem, integerlength, decimallength) {
//	if(!integerlength) integerlength = 6;
//	if(!decimallength) decimallength = 2;
//	var reg = /^(\d{0,/ + integerlength + /})(\.\d{1,/ + decimallength + /})?$/;
//	var re = new RegExp(reg);
//	var value = $("#" + elem).val();
//	if(!re.test(value)) {
//		layer.tips('金额格式不正确！', "#" + elem, {
//			tipsMore: true,
//			tips: 3
//		});
//		$("#" + elem).focus();
//	}
//	return re.test(value);
//}
/**
 * 录入完成后，输入模式失去焦点后对录入进行判断并强制更改，并对小数点进行0补全
 * arg1 inputObject
 * 这个函数写得很傻，是我很早以前写的了，没有进行优化，但功能十分齐全，你尝试着使用
 * 其实有一种可以更快速的JavaScript内置函数可以提取杂乱数据中的数字：
 * parseFloat('10');
 **/
function moneyBlur(th) {
	var v = th.value;
	if(v === '') {
		v = '0.00';
	} else if(v === '0') {
		v = '0.00';
	} else if(v === '0.') {
		v = '0.00';
	} else if(/^0+\d+\.?\d*.*$/.test(v)) {
		v = v.replace(/^0+(\d+\.?\d*).*$/, '$1');
		v = inp.getRightPriceFormat(v).val;
	} else if(/^0\.\d$/.test(v)) {
		v = v + '0';
	} else if(!/^\d+\.\d{2}$/.test(v)) {
		if(/^\d+\.\d{2}.+/.test(v)) {
			v = v.replace(/^(\d+\.\d{2}).*$/, '$1');
		} else if(/^\d+$/.test(v)) {
			v = v + '.00';
		} else if(/^\d+\.$/.test(v)) {
			v = v + '00';
		} else if(/^\d+\.\d$/.test(v)) {
			v = v + '0';
		} else if(/^[^\d]+\d+\.?\d*$/.test(v)) {
			v = v.replace(/^[^\d]+(\d+\.?\d*)$/, '$1');
		} else if(/\d+/.test(v)) {
			v = v.replace(/^[^\d]*(\d+\.?\d*).*$/, '$1');
			ty = false;
		} else if(/^0+\d+\.?\d*$/.test(v)) {
			v = v.replace(/^0+(\d+\.?\d*)$/, '$1');
			ty = false;
		} else {
			v = '0.00';
		}
	}
	th.value = v;
}
/**
 * 格式化折扣
 * arg1 inputObject
 **/
function discountKeyUp(th) {
	if(th.value.length == 1 && th.value != "0") {
		th.value = 0;
	}

	var regStrs = [
		['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
		['[^\\d\\.]+$', ''], //禁止录入任何非数字和点
		['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点
		['^(\\d+\\.\\d{2}).+', '$1'] //禁止录入小数点后两位以上
	];
	for(i = 0; i < regStrs.length; i++) {
		var reg = new RegExp(regStrs[i][0]);
		th.value = th.value.replace(reg, regStrs[i][1]);
	}
}

/**************************金额相关操作END**************************/
/**************************数字相关操作START**************************/
// 判断是否为数字和第一位不能为小数点,可用于文本框上
function checkDecimal(_val) {
	var proportion = _val;
	if(proportion.value != "") {
		proportion.value = proportion.value.replace(/[^\d\.]/g, '');
		if(proportion.value.slice(0, 1) == '.') {
			proportion.value = "";
			return false;
		}
	}
	return true;
}
/*
 * onkeydown事件:只能输入数字
 */
function onlyNum() {
	var event = (event) ? event : window.event;
	var kc = event.keyCode || event.charCode;
	// 48到57~96到105数字，8退格键，46delete键,37左移，39右移,9 tab键
	if(!((kc >= 48 && kc <= 57) || (kc >= 96 && kc <= 105) || kc == 8 || kc == 46 || kc == 37 || kc == 39 || kc == 9)) {
		kc = 0;
		event.returnValue = false;
		return false;
	}
}
/*
 * onkeydown事件:只能输入浮点数字
 */
function onlyFloat() {
	var event = (event) ? event : window.event;
	var kc = event.keyCode || event.charCode;
	// 48到57~96到105数字，8退格键，46delete键,37左移，39右移 110和190小数点,9 tab键
	if(!((kc >= 48 && kc <= 57) || (kc >= 96 && kc <= 105) || kc == 8 || kc == 46 || kc == 37 || kc == 39 || kc == 110 || kc == 190 || kc == 9)) {
		kc = 0;
		event.returnValue = false;
		return false;
	}
}

/**
 * 排序字段用，验证只能输入0-9的数字
 *
 * @param {Object}
 *            obj
 */
function isNum(obj) {
	var flag = false;
	// 只能输入0-9的数字
	var reg = /^[0-9]*$/;
	if(!reg.test(obj)) {
		flag = true;
	}
	return flag;
};
/**
 * 正浮点数
 *
 * @param {Object}
 *            obj
 */
function isDecimal(obj) {
	var flag = false;
	// 正浮点数
	var reg = /^\d+(\.\d+)?$/;
	if(!reg.test(obj)) {
		flag = true;
	}
	return flag;
};
//数字、.(dot)以外过滤(onKeyPress调用)
function checkNumDot() {
	if((event.keyCode != 46) && // .之外
		(event.keyCode < 48 || event.keyCode > 57)) // 0-9之外
	{
		event.keyCode = 0;
		event.returnValue = false;
	}
}
/**
 * 格式化数字 th inputObject
 */
function intKeyUp(th) {
	var regStrs = [
		['[^\\d\\.]+$', ''] // 禁止录入任何非数字和点
	];
	for(i = 0; i < regStrs.length; i++) {
		var reg = new RegExp(regStrs[i][0]);
		th.value = th.value.replace(reg, regStrs[i][1]);
	}
}
/**
 * 格式化金额 arg1 inputObject
 */
function moneyKeyUp(th) {
	var regStrs = [
		['^0(\\d+)$', '$1'], // 禁止录入整数部分两位以上，但首位为0
		['[^\\d\\.]+$', ''], // 禁止录入任何非数字和点
		['\\.(\\d?)\\.+', '.$1'], // 禁止录入两个以上的点
		['^(\\d+\\.\\d{2}).+', '$1'] // 禁止录入小数点后两位以上
	];
	for(i = 0; i < regStrs.length; i++) {
		var reg = new RegExp(regStrs[i][0]);
		th.value = th.value.replace(reg, regStrs[i][1]);
	}
}

/**************************数字相关操作END**************************/

/**************************共通操作START**************************/

/**
 * js全部置换
 * @param {Object} s1
 * @param {Object} s2
 */
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
}

/*
 *初始化checkbox只能单个选中
 */
function initCheckbox() {
	$('form').find('input[type=checkbox]').bind('click', function() {

		$('form').find('input[type=checkbox]').not(this).attr("checked", false);

	});
}

/*
 * 获取浏览器地址栏id
 */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return decodeURIComponent(r[2]);
	return null;
}

//处理键盘事件
//禁止后退键（Backspace）密码或单行、多行文本框除外

function banBackSpace(e) {
	var ev = e || window.event; //获取event对象
	var obj = ev.target || ev.srcElement; //获取事件源
	var t = obj.type || obj.getAttribute('type'); //获取事件源类型
	//获取作为判断条件的事件类型
	var vReadOnly = obj.readOnly;
	var vDisabled = obj.disabled;
	//处理undefined值情况
	vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
	vDisabled = (vDisabled == undefined) ? true : vDisabled;
	//当敲Backspace键时，事件源类型为密码或单行、多行文本的，
	//并且readOnly属性为true或disabled属性为true的，则退格键失效
	var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea" || t == "number") && (vReadOnly == true || vDisabled == true);
	//当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
	var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea" && t != "number";
	//判断
	if(flag2 || flag1)
		return false;
}

//禁止退格键
//作用于Firefox、Opera
document.onkeypress = banBackSpace;

//禁止退格键
//作用于IE、Chrome
document.onkeydown = banBackSpace;

function doKey(e) { //处理键盘事件jiangkun
	var ev = e || window.event; //获取event对象
	var obj = ev.target || ev.srcElement; //获取事件源
	var t = obj.type || obj.getAttribute('type'); //获取事件源类型
	var condition = false;
	if(obj.getAttribute('readonly') == 'readonly' || obj.getAttribute('readonly') == true) {
		condition = (ev.keyCode == 8);
	}
	if(condition) {
		return false;
	}
}

/**
 * 获取图片路径
 */
function createObjectURL(blob) {
	if(window.URL) {
		return window.URL.createObjectURL(blob);
	} else if(window.webkitURL) {
		return window.webkitURL.createObjectURL(blob);
	} else {
		return null;
	}
};

function stopDefault(e) {
	//如果提供了事件对象，则这是一个非IE浏览器
	if(e && e.preventDefault) {
		//阻止默认浏览器动作(W3C)
		e.preventDefault();
	} else {
		//IE中阻止函数器默认动作的方式
		window.event.returnValue = false;
	}
	return false;
}

/*
 * 判断textArea输入的字符
 * oInObj:元素id
 * length：长度
 */
function checkTextareaLength(oInObj, length) {
	var iCurLen = oInObj.value.length;

	if(oInObj != null && iCurLen > length) {
		oInObj.value = oInObj.value.substring(0, length);
		return false;
	}
}

/**
 *创建上传通道
 */
function createXHR() {
	if(typeof XMLHttpRequest != "undefined") {
		return new XMLHttpRequest();
	} else if(typeof ActiveXObject != "undefined") {
		if(typeof arguments.callee.activeXString != "string") {
			var versions = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0",
				"MSXML2.XMLHttp"
			];
			for(var i = 0, len = versions.length; i < len; i++) {
				try {
					var xhr = new ActiveXObject(versions[i]);
					arguments.callee.activeXString = versions[i];
					return xhr;
				} catch(ex) {
					//跳过
				}
			}
		}
		return new ActiveXObject(arguments.callee.activeXString);
	} else {
		throw new Error("NO XHR object available.")
	}
};
/**************************共通操作END**************************/

/**************************LOG操作Start**************************/
/*
 * 写入log
 * str：字符串或者json对象
 */
function log(str) {
	if(typeof(str) == "string") {
		console.log(window.location.pathname + " log(String): " + str);
	} else {
		console.log(window.location.pathname + " log(JSON): " + JSON.stringify(str));
	}
}
/**************************共通操作END**************************/

/**************************基础参数START**************************/
// 显示页数
var pageIndex = 1;
// 每页大小
var pageSize = 50;
// layui使用全局参数
var layer, form, element, laydate, laypage, laytpl, table, layedit;

/**************************基础参数END**************************/

/**
 * 设置默认打印机，报表直接打印
 * @param printValue
 */
function DepositPrint(printValue, FileValue) {

	var ie = isIE();

	if(!ie) {
		layer_msg("请使用IE浏览器打印！");
		return;
	}
	var ParaName, ParaValue;
	try {
		var ObjPrintMange = new ActiveXObject('WebPrint.WebPrintUnit');
	} catch(e) {
		if(confirm('打印控件未安装，现在下载吗?')) {
			window.location = '../PrintActivex.exe';
		}
		return;
	}

	var OldVersion = ObjPrintMange.Version;
	NewVerion = '5.0(2011-08-01)';
	if(OldVersion < NewVerion) {
		ObjPrintMange = null;
		alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。');
		window.location = '../PrintActivex.exe';
		return;
	}
	ObjPrintMange.CheckReg('朱志炎', 'F7F83570BAF3756CE17A6E3E4FA65BBB');
	ObjPrintMange.LeftMargin = 0;
	ObjPrintMange.RightMargin = 0;
	ObjPrintMange.TopMargin = 0;
	ObjPrintMange.BottomMargin = 0;
	ObjPrintMange.PrintReport(FileValue, '', '', printValue, '', '', '', '', '');
	//				ObjPrintMange.DesignReport(FileValue, '', '', printValue, '', '', '', '', '');
	ObjPrintMange = null;
}

function isIE() { //ie?
	if(!!window.ActiveXObject || "ActiveXObject" in window)
		return true;
	else
		return false;
}

//转意符换成普通字符
function escape2Html(str) {
	var arrEntities = {
		'lt': '<',
		'gt': '>',
		'nbsp': ' ',
		'amp': '&',
		'quot': '"'
	};
	return str.replace(/&(lt|gt|nbsp|amp|quot);/ig, function(all, t) {
		return arrEntities[t];
	});
}

// 登入验证码
var _loginCode;

function createCode() {
	_loginCode = "";
	var codeLength = 4; //验证码的长度
	var checkCode = document.getElementById("checkCode");
	var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', '$', 'j', 'k', '@', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', '!', 'J', 'K', '#', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
	for(var i = 0; i < codeLength; i++) {
		var charNum = Math.floor(Math.random() * 52);
		_loginCode += codeChars[charNum];
	}
	if(checkCode) {
		checkCode.className = "code";
		checkCode.innerHTML = _loginCode;
	}
}

/*
 * 下载文件
 * url：文件路径
 * name：下载后文件名
 */
function downloadFile(url, name) {
	if(!name) name = new Date().getTime();

	if(isIE()) {
		window.open(url, "_blank");
	} else {

		var temp = document.createElement("a");
		if($("#downPdf").length == 0) {
			$("body").append('<a download="" href="" target="blank" id="downPdf" style="display:none"></a> ');
		}

		var a = document.getElementById("downPdf");
		a.href = url;
		a.download = name;
		a.click();

	}

}

// 分页
function createPage(totalCount) {

	laypage.render({
		elem: 'page',
		count: totalCount,
		limit: pageSize,
		limits: [10, 20, 50, 100],
		layout: ['count', 'limit', 'prev', 'page', 'next', 'skip'],
		curr: pageIndex || 1,
		prev: '<i class="layui-icon">&#xe603;</i>',
		next: '<i class="layui-icon">&#xe602;</i>',
		first: "首页",
		last: "尾页",
		skip: true,
		jump: function(obj, first) {
			if(!first) {
				// obj.curr 点击页数
				pageIndex = obj.curr;
				// obj.limit 每页条数
				pageSize = obj.limit;
				// 获取数据
				getData();
			}
		}
	});

}

// 分页
function createPageCompany(totalCount, pageSize) {

	laypage.render({
		elem: 'page',
		count: totalCount,
		limit: pageSize,
		layout: ['count', 'prev', 'page', 'next', 'skip'],
		curr: pageIndex || 1,
		prev: '<i class="layui-icon">&#xe603;</i>',
		next: '<i class="layui-icon">&#xe602;</i>',
		first: "首页",
		last: "尾页",
		skip: true,
		jump: function(obj, first) {
			if(!first) {
				// obj.curr 点击页数
				pageIndex = obj.curr;
				// obj.limit 每页条数
				pageSize = obj.limit;
				// 获取数据
				getData();
			}
		}
	});

}

/**
 * 其他分页（有多个分页存在时使用）
 * @param {Object} totalCount 总条数
 * @param {Object} pageid 分页id
 * @param {Object} callback 回调
 */
function createPageOther(totalCount, pageid, callback) {

	laypage.render({
		elem: pageid,
		count: totalCount,
		limit: 50,
		layout: ['prev', 'page', 'next', 'skip', 'count'],
		curr: pageIndex || 1,
		prev: '<i class="layui-icon">&#xe603;</i>',
		next: '<i class="layui-icon">&#xe602;</i>',
		first: "首页",
		last: "尾页",
		skip: true,
		jump: function(obj, first) {
			if(!first) {
				// obj.curr 点击页数
				pageIndex = obj.curr;
				// 获取数据
				if(callback && typeof(callback) == 'function') callback();
			}
		}
	});

}

// 验证失败提示
function validateTips(_obj) {
	var flg = false;
	if(_obj.key) {
		flg = true;
	} else {
		layer.tips(_obj.msg, _obj.object, {
			tipsMore: true,
			tips: 3
		});
		$(_obj.object).focus();
	}
	return flg;
}

/**
 * 数字前面补0
 * @param {Object} num 传入的数字
 * @param {Object} n 需要的字符串长度
 */
function PrefixInteger(num, n) {
	return(Array(n).join(0) + num).slice(-n);
}

/**
 * 检查Decimal是否符合要求 （Decimal(alllength,sublength)）
 *
 * @param {Object}
 *            itemObj 组件对象
 * @param {Object}
 *            alllength 总长度
 * @param {Object}
 *            sublength 小数长度
 */
function checkDecimalFullObj(itemObj, alllength, sublength) {
	var value = $(itemObj).val().toString().trim();
	// 判断是否为空
	if(value == null || value == undefined || value == "") {
		layer_msg("请输入数字!");
		$(itemObj).focus();
		return false;
	}

	// 判断是否是数字
	var renumber = /^[0-9]+.?[0-9]*$/; // 判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
	if(!renumber.test(value)) {
		layer_msg("格式不正确,请输入数字!");
		$(itemObj).focus();
		return false;
	}

	// 判断整数长度
	var tempvalue = value.split(".");
	if(tempvalue[0] && tempvalue[0].length > (alllength - sublength)) {
		layer_msg("整数位过长,长度不能超过" + (alllength - sublength) + "位!");
		$(itemObj).focus();
		return false;
	}

	if(tempvalue.length > 1 && tempvalue[1].length > sublength) {
		layer_msg("小数位过长,长度不能超过" + sublength + "位!");
		$(itemObj).focus();
		return false;
	}
	return true;
}

// 异地登入
function errorLogin(XMLHttpRequest, textStatus, errorThrown) {
	if(XMLHttpRequest.readyState == 4 && XMLHttpRequest.status == 200 && XMLHttpRequest.responseText == "") {
		goLogin("ajaxerror");
	} else {
		alertError("系统连接失败，请确保互联网访问正常！");
	}
}

// 检查是否有权限
function checkLogin() {
	var loginkey = getCookie("adminkey");
	if(loginkey == "" || loginkey == undefined || loginkey == "undefined") {
		goLogin("keyerror");
	}
}

// 月份转英文
function monthToEng(month) {
	var monthEng = "";
	if(month == 1) {
		monthEng = "Jan";
	} else if(month == 2) {
		monthEng = "Feb";
	} else if(month == 3) {
		monthEng = "Mar";
	} else if(month == 4) {
		monthEng = "Apr";
	} else if(month == 5) {
		monthEng = "May";
	} else if(month == 6) {
		monthEng = "Jun";
	} else if(month == 7) {
		monthEng = "Jul";
	} else if(month == 8) {
		monthEng = "Aug";
	} else if(month == 9) {
		monthEng = "Sep";
	} else if(month == 10) {
		monthEng = "Oct";
	} else if(month == 11) {
		monthEng = "Nov";
	} else if(month == 12) {
		monthEng = "Dec";
	}

	return monthEng;
}

/**
 * 检查是否有错误
 */
function checkError(data) {

	// 判断参数是否错误
	if(data != null && data && data.key == "PARAMETER_ERROR") {
		alertError(data.msg);
		return true;
	}

	// 判断是否有请求权限
	if(data != null && data && data.key == "NO_AUTHORITY") {
		alertError(data.msg);
		return true;
	}

	// 判断是否非法访问
	if(data != null && data && data.key == "ILLEGAL_ACCESSES") {
		goLogin("ILLEGAL_ACCESSES");
		return true;
	}

	// 判断服务是否已到期
	if(data != null && data && data.key == "SERVICE_EXPIRATION") {
		goLogin("SERVICE_EXPIRATION");
		return true;
	}

	return false;
}

/**
 * 跳转至首页
 * @param {Object} type
 */
function goLogin(type) {
	var temp = "";
	if(window.location.href.indexOf("/ZCF") != -1) {
		temp = "/ZCF"
	}
	window.top.location = "http://" + window.location.host + temp + "/login.html?error=" + type;
}

/*
 * ajax 共通请求
 * url：请求路径
 * formdata：数据
 * type：请求类型
 * async：是否异步
 * callback：回调函数
 */
function commonAjax(url, formdata, type, async, callback) {
	log("AJAX请求开始。");
	log("AJAX请求地址：" + url);
	//	for(var entry of formdata.entries()) {
	//		log("AJAX请求参数：" + "[name]:" + entry[0] + ",[value]:" + entry[1]);
	//	};
	$.ajax({
		url: url,
		type: type,
		dataType: "json",
		data: formdata,
		// 告诉jQuery不要去处理发送的数据
		processData: false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType: false,
		async: async,
		cache: false,
		success: function(data) {
			log("AJAX请求成功。");
			log(data);
			if(checkError(data)) return false;
			if(data != null) {
				// 判断是否成功
				if(data.key == "SUCCESS") {
					// 回调
					if(callback && typeof(callback) == 'function') callback(data);
				} else {
					alertWarning(data.msg);
				}
			} else {
				alertWarning("服务器没有返回数据，请联系管理员！");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			log("AJAX请求失败。");
			errorLogin(XMLHttpRequest, textStatus, errorThrown);
		}
	});
}
/*
 * ajax 共通请求
 * url：请求路径
 * formdata：数据
 * type：请求类型
 * async：是否异步
 * callback：回调函数
 */
function commonAjax2(url, formdata, type, async, callback) {
	log("AJAX请求开始。");
	log("AJAX请求地址：" + url);
	//	for(var entry of formdata.entries()) {
	//		log("AJAX请求参数：" + "[name]:" + entry[0] + ",[value]:" + entry[1]);
	//	};
	$.ajax({
		url: url,
		type: type,
		dataType: "json",
		data: formdata,
		// 告诉jQuery不要去处理发送的数据
		processData: false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType: false,
		async: async,
		cache: false,
		success: function(data) {
			log("AJAX请求成功。");
			log(data);
			if(checkError(data)) return false;
			if(data != null) {
				// 回调
				if(callback && typeof(callback) == 'function') callback(data);

			} else {
				alertWarning("服务器没有返回数据，请联系管理员！");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			log("AJAX请求失败。");
			errorLogin(XMLHttpRequest, textStatus, errorThrown);
		}
	});
}
//// 请求地址
//var url = "../../SELECT";
//// 参数
//var formData = new FormData();
//formData.append(name, element);
//formData.append('myfile', $('input[name=myfile]')[0].files[0]);
//// 开始请求
// commonAjax(url, formData, "post", true, function(data){
// 	// 数据
// 	temp = data[1];
// });
var pageSize = 50;

/*获取基础路径Start*/
var COMMONPATH = "..";

var tempCOMMONPATH = "http://" + top.window.location.host;
if(top.window.location.href.indexOf("/ZCF") != -1) {
	tempCOMMONPATH = tempCOMMONPATH + "/ZCF"
}

COMMONPATH = tempCOMMONPATH;
/*获取基础路径END*/

function strToJson(str) {
	var json = eval('(' + str + ')');
	return json;
}

//快递单号查询
function Express(express_name, express_no) {
	window.open("https://www.kuaidi100.com/chaxun?com=" + express_name + "&nu=" + express_no);
}

/*
 * 14位编号按6,3,5加空格分隔
 */
function splitBySpace(number) {
	return number;
	if(number.length == 14) {
		var firstnum = number.substr(0, 6);
		var secondnum = number.substr(6, 3);
		var thirdnum = number.substr(9, 5);
		return(firstnum + " " + secondnum + " " + thirdnum);
	} else {
		return number;
	}
}
/*
 * 去除空格
 */
function trim(str) {
	return str.replace(/\s|\xA0/g, "");
}