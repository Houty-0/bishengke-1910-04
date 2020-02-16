$(function() {
	/* 开始订餐,打开扫码窗口 */
	$("#j-start-order").click(function() {
		$("#j-dialog").load("/login/login.html");
		$(".ui-dialog-overlay").show();
		$(".ui-dialog").show();
		$(".scanQrcode").show();
	});

})