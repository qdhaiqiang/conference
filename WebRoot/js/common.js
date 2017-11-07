String.prototype.Trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.LTrim = function() {
	return this.replace(/(^\s*)/g, "");
}
String.prototype.RTrim = function() {
	return this.replace(/(\s*$)/g, "");
}

//阻止button 回车点击
function eventaction(evt) {

	var isie = (document.all) ? true : false;
	var key;
	var srcobj;
	if (isie) // IE下
	{
		key = event.keyCode;
		srcobj = event.srcElement;
	} else // 火狐浏览器下
	{
		key = evt.which;
		srcobj = evt.target;
	}

	if (key == 13 && srcobj.tagName == "INPUT" && srcobj.type == "text") {
		evt.preventDefault();
	}
};


$(".max-len-100").blur(function(){
	//alert(this.value);
	if(this.value.length>100){
		$(this).after("<label class='tip error'><spring:message code='textTooLong100'/></label>");
		$(this).val(this.value.substr(0,100));
	}else{
		$(".tip").remove();
	}
});

$(".max-len-1024").blur(function(){
	//alert(this.value);
	if(this.value.length>1024){
		$(this).after("<label class='tip error'><spring:message code='textTooLong1024'/></label>");
		$(this).val(this.value.substr(0,1024));
	}else{
		$(".tip").remove();
	}
});



function isTel(e) {
    var k = window.event ? e.keyCode : e.which;
    if (((k >= 48) && (k <= 57)) || k == 8 || k == 0 || k == 40 || k == 41 || k == 43 || k==45) {
    } else {
        if (window.event) {
            window.event.returnValue = false;
        }
        else {
            e.preventDefault(); //for firefox 
        }
    }
}

function isNum(e) {
    var k = window.event ? e.keyCode : e.which;
    if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {
    } else {
        if (window.event) {
            window.event.returnValue = false;
        }
        else {
            e.preventDefault(); //for firefox 
        }
    }
} 

function isNumReg(testStr){
	var reg = new RegExp(/^[0-9]*$/);
	
	//alert(reg.test(testStr));
	return reg.test(testStr);
}

function isTeleReg(testStr){
	//var reg = new RegExp(/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/);
	var reg = new RegExp(/^\s*\+?\s*(\(\s*\d+\s*\)|\d+)(\s*-?\s*(\(\s*\d+\s*\)|\s*\d+\s*))*\s*$/);
	
	//alert(reg.test(testStr));
	return reg.test(testStr);
}

function dateLargeThan(date1,date2){
	//alert(date1>date2);
	return date1>date2;
}

function isEmailReg(str){
	var reg = new RegExp(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/);
	return reg.test(str);
}

