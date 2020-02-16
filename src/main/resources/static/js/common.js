$(function() {
	/* 开始订餐,打开扫码窗口 */
	$("#j-start-order").click(function() {
		$("#j-dialog").load("/login/login.html");
		$(".ui-dialog-overlay").show();
		$(".ui-dialog").show();
		$(".scanQrcode").show();
	});

	/*
	 * function doLogin(){ $("#j-dialog").load("/login/login.html");
	 * $(".ui-dialog-overlay").show(); $(".ui-dialog").show();
	 * $(".scanQrcode").show(); }
	 */
})

function doHandleResult(data) {
	var products = "";
	$(data).each(
			function(index, good) {
				var baoZ = good.baoZhuang;
				if (baoZ == null) {
					baoZ = "";
				} else {
					baoZ = "/" + baoZ;
				}
				products += '<div class="product" value="' + good.note + '">'
						+ '<div class="img cursor" style="height: 139px;">'
						+ '<img src="' + good.imgUrl + '">' + '</div>'
						+ '<div class="title">' + good.name + '</div>'
						+ '<div class="desc grey">'
						+ '<div class="menu-heat-desc menu-heat-desc0"></div>'
						+ '</div><div class="price red">'
						+ '<span class="font16 font-weight">' + good.price
						+ '</span>元' + '<span style="color: #7f7f7f;">' + baoZ
						+ '</span>' + '</div>'
						+ '<div class="order j-menu-order">'
						+ '<div class="start ui-bgbtn-green" onclick="toOrderDetail()">开始订餐</div>'
						+ '</div>' + '</div>';

			});
	return products;
	
}

//点击菜单项查询对应菜单商品列表
function doFindObjects(menuId) {
	//var _menuId = $("#j-menu-left").data("menuId");
	// $(".m-menu-content.right").empty();
	//var menuId = $(this).prop("id");
	/* if(menuId==null){
		menuId = _menuId;
	} */
	//debugger
	$("#j-menu-left").data("menuId",menuId);// 保存当前展示的是哪个菜单Id对应的商品列表
	var url = "/goods/doFindObjects";
	var params = {
		"menuId" : menuId
	};


	if (menuId != 1) {
		$(".m-condition").addClass("hidden");
		$(".m-product-list").css("margin-top", 0);
	} else {
		$(".m-condition").removeClass("hidden");
		$(".m-product-list").css("margin-top", 88);
	}

	$.get(url, params, function(result) {
		if (result.state == 1) {
			if ($(".m-product-list").empty()) {
				var products = doHandleResult(result.data);
				$(".m-product-list").append(products);
			}
		}
	});

}