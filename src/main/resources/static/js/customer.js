$(function () {
    doGetUserMessage();


    $("#j-addr-save-btn").on("click",saveOrderAddr);
});

function doGetUserMessage() {
    $.ajax({
        type:"get",
        url:"/user/getMessage",
        success:function (result) {
            console.log(result.data);
            var data=result.data;
            var coupon=data.coupon;
            $("#j-top-coupon-size").html(coupon);
        }
    });
}

function saveOrderAddr() {
    var addr=$("#j-mainaddress").val()+$("#j-mainaddr11").val();
    var name=$("#j-desc-input-name").val();
    var gender=$("#order-gender").val();
    var phone=$("#j-desc-input-phone").val();

    var params={"orderAddr":addr,"orderName":name,"orderGender":gender,"orderPhone":phone};
    $.ajax({
        type:"post",
        url:"/addr/saveOrderAddr",
        data:params,
        success:function (result) {
            console.log(result);
            if (result.state==1){
                alert(result.data.data);
            } else{
                alert("服务器正忙!");
            }
        }
    });
}