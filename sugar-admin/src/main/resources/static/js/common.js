
function ajaxGet(url,params,success,failure) {
    $.ajax({
        url:url,
        type:"get",
        dataType:"json",
        data:params,
        success: function (result) {
            if(result.code == 200){
                success(result.data);
            }else{
                failure(result.message);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if(XMLHttpRequest.status == 404){
                failure("接口不存在");
            }else{
                failure("操作失败：错误异常状态码【"+XMLHttpRequest.status+"】错误信息：【"+textStatus+"】")
            }
            console.log(errorThrown);
        }
    });
}

function ajaxPost(url,params,success,failure) {
    $.ajax({
        url:url,
        type:"post",
        dataType:"json",
        data:params,
        success: function (result) {
            if(result.code == 200){
                success(result.data);
            }else{
                failure(result.message);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if(XMLHttpRequest.status == 404){
                failure("接口不存在");
            }else{
                failure("操作失败：错误异常状态码【"+XMLHttpRequest.status+"】错误信息：【"+textStatus+"】")
            }
            console.log(errorThrown);
        }
    });
}