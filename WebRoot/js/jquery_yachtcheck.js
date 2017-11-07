function typeChange(){
	var user_type=Document.get;
	
	
	
}

function infoCheck(){
	var user_type = document.getElementsByName("user.user_type").value;
	var email = document.getElementsByName("user.email").value;
	var user_type = document.getElementsByName("user.user_type").value;
	var user_type = document.getElementsByName("user.user_type").value;
	var user_type = document.getElementsByName("user.user_type").value;
	var user_type = document.getElementsByName("user.user_type").value;
	var user_type = document.getElementsByName("user.user_type").value;
	var user_type = document.getElementsByName("user.user_type").value;
	var user_type = document.getElementsByName("user.user_type").value;
	var user_type = document.getElementsByName("user.user_type").value;
	var user_type = document.getElementsByName("user.user_type").value;
	var user_type = document.getElementsByName("user.user_type").value;
	var user_type = document.getElementsByName("user.user_type").value;
	var user_type = document.getElementsByName("user.user_type").value;
	
	return true;
}

/**
*邮箱验证
*/      
function checkEmail(){

		var a = document.form1.email.value;

		var i = a.indexOf('@');

    	var j = a.lastIndexOf('@');

		var g = a.indexOf('.');

		var k = a.substr(g+1).toLowerCase();
		alert("haha");
		if(i==-1||i!=j||i==0||g==(a.length-1)||g==-1||g<i||i == g-1||k != "com"){

			umail.innerHTML = "<font color='red' size = 2>输入的邮箱错了</font>";

			return false;
 
		}else{

			umail.innerHTML = "<font color='green' size = 2>可以使用邮箱</font>";

			return true;

		}

	}
