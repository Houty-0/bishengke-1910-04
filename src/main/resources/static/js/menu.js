$(function() {
	/* 点击切换类型说明,尺寸说明图片 */
	$(".m-pizza-tip .nav .tap").click(function() {
		$(this).prop("class", "tap on");
		$(this).siblings().prop("class", "tap");
		var n = $(this).index();
		var chose = $(".m-condition .m-pizza-tip .img img:eq(" + n + ")");
		chose.prop("class", "");
		chose.siblings().prop("class", "hidden")
	});

	/* 类型,尺寸说明关闭开关 */
	$(".m-condition .m-pizza-tip .close").click(function() {
		$(".m-condition .m-pizza-tip").hide();
		$(".empty-container").hide();
		// $("#j-empty-container").slideToggle(500);
	});

	/* 点击类型,尺寸让隐藏区域显示 */
	$(".m-condition .title .info").click(function() {
		$(".m-condition .m-pizza-tip").show();
		$(".empty-container").show();
		var n = $(this).parent().parent().index();
		var image = $(".m-condition .m-pizza-tip .img img:eq(" + n + ")");
		image.prop("class", "");
		image.siblings().prop("class", "hidden")

		var tap = $(".m-pizza-tip .nav .tap:eq(" + n + ")");
		tap.prop("class", "tap on");
		tap.siblings().prop("class", "tap");
	});

	/* 商品详细介绍弹出层关闭 */
	$(".m-pop-detail .close").click(function() {
		$(".ui-dialog-overlay").hide();
		$(".m-pop-detail").hide();
	});

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

/*$(function() {
	 弹出商品详细介绍层 
	$(".m-product-list").on("click", ".img", popupProductDesc);

})*/

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

	$(".m-pop-detail .right-content .price-info .price").html(
			price + "元" + baoZ);

	$(".m-pop-detail").show();
}

/* 选规格加载订单详情 */
function toOrderDetail() {
	$(".m-menu-content.right").load("other/orderDetail.html");
}