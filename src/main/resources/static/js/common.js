$(function() {
	/* 开始订餐,打开扫码窗口 */
	$("#j-start-order").on("click",doLogin);
	
	$("#j-logout").click(logout);
	/*
	 * function doLogin(){ $("#j-dialog").load("/login/login.html");
	 * $(".ui-dialog-overlay").show(); $(".ui-dialog").show();
	 * $(".scanQrcode").show(); }
	 */
})

function doLogin() {
	$("#j-dialog").load("/login/login.html");
	$(".ui-dialog-overlay").show();
	$(".ui-dialog").show();
	$(".scanQrcode").show();
}

//退出登录
function logout() {

	$.get("/doLogout",function (result) {
		if (result.state==1){
			window.location.href="../index.html";
		}
	});
}