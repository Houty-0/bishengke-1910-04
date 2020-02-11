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
	$(".m-pop-detail .close").click(function(){
		$(".ui-dialog-overlay").hide();
		$(".m-pop-detail").hide();
	});
	
	
	/*$(".m-main").waypoint(function(){
		$(".m-main .m-menu ul").removeAttr("style","top");
		$(".m-main .m-menu ul").css({"position":"static","bottom":0});
		},{ offset: '-60%'});
		
	$(".m-main").waypoint(function(){
		$(".m-main .m-menu ul").removeAttr("style","bottom");
		$(".m-main .m-menu ul").css({"position":"fixed","top":"80px"});
		},{ offset: -200});*/
	
	
});
