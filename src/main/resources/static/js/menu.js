$(function() {
	/* 点击切换类型说明,尺寸说明图片 */
	$(".m-pizza-tip .nav .tap").on("click",changeImg);

	/* 点击类型,尺寸让隐藏区域显示 */
	$(".m-condition .title .info").on("click",showPizzaTip);

	/* 类型,尺寸说明关闭开关 */
	$(".m-condition .m-pizza-tip .close").on("click",closePizzaTip);

	/* 商品详细介绍弹出层关闭 */
	$(".m-pop-detail .close").on("click",closeGoodDetail);

	// 检查用户是否登陆
	checkLogin();
	
	/*
	 * $(".m-main").waypoint(function(){ $(".m-main .m-menu
	 * ul").removeAttr("style","top"); $(".m-main .m-menu
	 * ul").css({"position":"static","bottom":0}); },{ offset: '-60%'});
	 * 
	 * $(".m-main").waypoint(function(){ $(".m-main .m-menu
	 * ul").removeAttr("style","bottom"); $(".m-main .m-menu
	 * ul").css({"position":"fixed","top":"80px"}); },{ offset: -200});
	 */

});

function checkLogin(){
	var _id = $.cookie('id');
	if(!_id){
		return;
	}
	$("#j-start-order").hide();
	$("#j-top-coupon").removeClass("hidden");
	$("#j-top-nav .user").removeClass("hidden");
}

/* 点击类型,尺寸让隐藏区域显示 */
function showPizzaTip() {
	$(".m-condition .m-pizza-tip").show();
	$(".empty-container").show();
	var n = $(this).parent().parent().index();
	var image = $(".m-condition .m-pizza-tip .img img:eq(" + n + ")");
	image.prop("class", "");
	image.siblings().prop("class", "hidden")

	var tap = $(".m-pizza-tip .nav .tap:eq(" + n + ")");
	tap.prop("class", "tap on");
	tap.siblings().prop("class", "tap");
}

/* 点击切换类型说明,尺寸说明图片 */
function changeImg() {
	$(this).prop("class", "tap on");
	$(this).siblings().prop("class", "tap");
	var n = $(this).index();
	var chose = $(".m-condition .m-pizza-tip .img img:eq(" + n + ")");
	chose.prop("class", "");
	chose.siblings().prop("class", "hidden")
}

/* 类型,尺寸说明关闭开关 */
function closePizzaTip() {
	$(".m-condition .m-pizza-tip").hide();
	$(".empty-container").hide();
	// $("#j-empty-container").slideToggle(500);
}

/* 商品详细介绍弹出层关闭 */
function closeGoodDetail() {
	$(".ui-dialog-overlay").hide();
	$(".m-pop-detail").hide();
}

/* 弹出商品详细介绍层 */
function popupProductDesc() {
	$(".ui-dialog-overlay").show();
	/* 获取商品描述详细信息 */
	var idesccn = $(this).parent().attr("value");
	/* 获取图片src地址 */
	var imgSrc = $(this).children().attr("src");
	/* 获取商品的名称 */
	var title = $(this).siblings(".title").html();
	/* 获取商品价格和包装类型 */
	var price = $(this).siblings(".price").children().eq(0).html();
	var baoZ = $(this).siblings(".price").children().eq(1).html();
	if (baoZ == null)
		baoZ = "";

	/* 給弹出层的商品添加详细信息内容 */
	$(".m-pop-detail .right-content .menu-desc").html(idesccn);
	/* 給弹出层的商品添加图片地址 */
	$(".m-pop-detail .left-content").children().attr("src", imgSrc);
	/* 給弹出层的商品添加名称 */
	$(".m-pop-detail .right-content .menu-info .name").html(title);
	/* 給弹出层的商品添加价格和包装类型 */

	$(".m-pop-detail .right-content .price-info .price").html(price + "元" + baoZ);

	$(".m-pop-detail").show();
}

/* 选规格加载单个商品页面 */
function toOrderDetail() {
	var obj = $(this);
	$(".m-menu-content.right").load("other/goodDetail.html");
	getGoogImgUrl(obj);
}

function getGoogImgUrl(obj) {
	var imgUrl = obj.parent().siblings(".img").children().attr("src");
	var productId = obj.parent().attr("productid");
	
	$("#j-menu-content").data("productid",productId);
	$("#j-menu-content").data("imgUrl",imgUrl);
}

function doHandleResult(data) {
	var html = "";
	var text = "开始订餐";
	var _id=$.cookie("id");
	var cls = "start";
	if(_id!=null){
		text="选规格";
		cls = "btn";
	}
	
	$(data).each(function(index, good) {
				var baoZ = good.baoZhuang;
				if (baoZ == null) {
					baoZ = "";
				} else {
					baoZ = "/" + baoZ;
				}
				html += '<div class="product" value="' + good.note + '">'
						+ '<div class="img cursor" style="height: 139px;">'
						+ '<img src="' + good.imgUrl + '">' + '</div>'
						+ '<div class="title">' + good.name + '</div>'
						+ '<div class="desc grey">'
						+ '<div class="menu-heat-desc menu-heat-desc0"></div>'
						+ '</div><div class="price red">'
						+ '<span class="font16 font-weight">' + good.price
						+ '</span>元' + '<span style="color: #7f7f7f;">' + baoZ
						+ '</span>' + '</div>'
						+ '<div class="order j-menu-order" productid='+good.id+'>'
						+ '<div class="'+cls+' ui-bgbtn-green">'+text+'</div>'
						+ '</div>' + '</div>';

			});
	return html;
	
}

//点击菜单项查询对应菜单商品列表
function doFindObjectsByMenuId(menuId) {
	if(menuId==null) menuId=1;
	$("#j-menu-left").data("menuId",menuId);// 保存当前展示的是哪个菜单Id对应的商品列表
	var url = "/goods/doFindObjects";
	var params = {
		"menuId" : menuId
	};

	if (menuId != 1) {
		$(".m-condition").addClass("hidden");
		$(".m-product-list").css("margin-top", 0);
		$(".empty-container").hide();
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

