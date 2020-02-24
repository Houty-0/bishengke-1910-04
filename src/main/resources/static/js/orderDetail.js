$(function(){
	$("#j-box-product-area").on("click",".j-plus",addShopBoxNum);
	$("#j-box-product-area").on("click",".j-minus",decrShopBoxNum);
})

function addToCart(){
    	var num = getGoodNum();
    	var quantity = parseInt($("#j-shopping-box-quantity").html());
    	var newNum = quantity + num;
    	$("#j-shopping-box-quantity").html(newNum);
    	/* 获取商品总计价格 */
    	var total = getTotal();
    	//console.log("total",total);
    	/* 获取商品单价 */
    	var price = getGoodPrice();
    	//console.log("price",price);
    	
    	total = total + (price * num);
    	//console.log("newTotal",total);
    	$("#j-shopping-box-total").html(total);
    	
    	addToShoppingContent();
    }
    
    // 判断购物盒中是否已有本次要加入的商品
    function getGoodProductId(productid){
    	var productIds = $("#j-shopping-box-quantity").data("productIds");
    	//console.log("productIds----",productIds);
    	
    	var flag = 0;
    	
    	$(productIds).each(function(index) {
        	if(productIds[index]==productid){
        		flag = 1;
        	}
        });
    	if(flag==0){
    		productIds.push(productid);
        	$("#j-shopping-box-quantity").data("productIds",productIds);
    	}
    	return flag;
    }
    
    function addToShoppingContent(){
    	var productid = $("#j-menu-content").data("productid");
    	//debugger
    	var flag = getGoodProductId(productid);
    	var num = getGoodNum();
    	if(flag==1) {
    		var val = parseInt($('#j-box-product-area .item[productid='+ productid +'] .num input').val());
    		$('#j-box-product-area .item[productid='+ productid +'] .num input').val(val + num);
    		return;
    	}
    	var imgUrl = $("#j-menu-banner .banner-item img").attr("src");
    	var price = getGoodPrice();
    	var name = $(".right-content .menu-info .name").html();
    	var html = '<div class="item clear-fix" productid="'+productid+'" sizeid="000">'+
					'<div class="m-p-img left">' +
					'<img src="'+ imgUrl +'"></div>' +
					'<div class="m-p-desc left"><div class="middle">' +
					'<span>'+name+'</span><br /> <span class="price red">'+price+'</span><span class="red">元</span>' +
					'</div></div>' +
					'<div class="m-p-calc right">' +
					'<div class="minus ui-minus-red j-minus"></div>'+
					'<div class="num">' +
					'<input type="text" value="'+num+'" readonly="readonly" />' +
					'</div><div class="j-plus plus ui-plus-grey"></div></div>' +
					'<div class="m-p-tip" style="display: block;">' +
					'<span>新奥尔良风情烤肉比萨(纯珍普通装)*1</span> <span>热柠檬红茶((标准杯))*1</span> <span>冰柠檬红茶((标准杯))*1</span>'+
					'</div></div>';
		$("#delivery").before(html);
    }
    
    // 获取购物盒对应商品的productid
    function getProductId(obj){
    	var productid = obj.parent().parent().attr("productid");
    	return productid;
    }
    
    // 获取购物盒对应商品价格
    function getShopBoxGoodPrice(obj){
    	var price = parseFloat(obj.parent().siblings(".m-p-desc").find(".price").html());
    	return price;
    }
    
    // 购物盒商品数量增加方法
    function addShopBoxNum(e) {
    	var obj = $(this);
    	// 1.获取原有商品数量
    	var val = parseInt($(this).prev(".num").children().val());
    	var newVal = val+1;
    	$(this).prev(".num").children().val(newVal);
    	var price = getShopBoxGoodPrice(obj);
    	var total = getTotal();
    	var totalNum = getTotalNum();
    	var newTotalNum = totalNum+1;
    	var newTotal = total + price;
    	$("#j-shopping-box-total").html(newTotal);
    	$("#j-shopping-box-quantity").html(newTotalNum);
    }
    
    // 购物盒商品数量减少方法
    function decrShopBoxNum(e) {
    	var obj=$(this);
    	// 1.获取原有商品数量
    	var val = parseInt($(this).next(".num").children().val());
    	//debugger
    	var price = getShopBoxGoodPrice(obj);
    	
    	var total = getTotal();
    	var totalNum = getTotalNum();
    	var newTotalNum = totalNum-1;
    	var newTotal = total - price;
    	
    	$("#j-shopping-box-total").html(newTotal);
    	$("#j-shopping-box-quantity").html(newTotalNum);
    	
    	if(val==1){
    		var productIds = $("#j-shopping-box-quantity").data("productIds");
    		
    		var productId = getProductId(obj);
    		productIds.splice($.inArray(productId,productIds),1);
    		
    		$("#j-shopping-box-quantity").data("productIds",productIds);
    		obj.parent().parent().remove();
    		return;
    	}
    	
    	var newVal= val-1;
    	$(this).next(".num").children().val(newVal);
    }
    
    // 获取购物盒总计商品数量
    function getTotalNum(){
    	var totalNum = parseInt($("#j-shopping-box-quantity").html());
    	return totalNum;
    }
    
//==============================    
    /* 获取商品单价 */
    function getGoodPrice(){
    	var price = parseFloat($("#goodPrice").html());
    	return price;
    }
    
    /* 获取商品总计价格 */
    function getTotal(){
    	var total = parseFloat($("#j-shopping-box-total").html());
    	//console.log("total",total);
    	return total;
    }
    
    /* 获取要添加到购物盒的商品数量 */
    function getGoodNum(){
    	var num = parseInt($(".right-content .mealeal-add .add-num input").val());
    	return num;
    }
    
    function decrNum(){
    	var num = getGoodNum();
    	if(num == 1) return;
    	$(".right-content .mealeal-add .add-num input").val(--num);
    }
    
    function addNum(){
    	var num = getGoodNum();
    	$(".right-content .mealeal-add .add-num input").val(++num);
    }
    
    function getGoogInfo() {
    	var productid = $("#j-menu-content").data("productid");
    	//console.log("productid",productid);
    	var params = {"id":productid};
    	var url="/goods/doFindObjectById";
    	$.ajax({
    		type: "GET",
    		url: url,
			data: params,    		
    		success: function(result){
    			//console.log("GoodInfo",result);
    			doHandleGoodInfo(result.data);
    		}
    	})
    }
    
    function doHandleGoodInfo(good){
    	$("#j-menu-banner .banner-item img").attr("src",good.imgUrl);
    	$(".right-content .menu-info .name").html(good.name);
    	$(".right-content .price-info .price").html(good.price);
    }
    
    function flyToShoppingBox(){
    	var offset = $("#j-shopping-box-quantity").offset();  //结束的地方的元素
        $(".add").click(function(event){   //是$(".addcar")这个元素点击促发的 开始动画的位置就是这个元素的位置为起点
          var addcar = $(this);
          //var img = addcar.parent().find('img').attr('src');
          var img = $(".banner-item img").attr('src');
          var flyer = $('<img class="u-flyer" src="'+img+'" width="160" height="160" style="border-radius: 8px;z-index:9999">');
          flyer.fly({
            start: {
              left: event.pageX-540,
              top: event.pageY+50
            },
            end: {
              left: offset.left+40,
              top: offset.top,
              width: 0,
              height: 0
            },
            onEnd: function(){
              //$("#j-shopping-box-quantity").show().animate({width: '250px'}, 200).fadeOut(1000);
              this.destory();
            }
          });
        });
    }