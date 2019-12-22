layui.use('form', function(){
    var form = layui.form;

    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});

$("#editBtn").mouseover(function () {
    $("#editBtn").removeClass("layui-anim layui-anim-rotate layui-anim-loop")
});

$("#editBtn").mouseout(function () {
    $("#editBtn").addClass("layui-anim layui-anim-rotate layui-anim-loop")
});

function changeLogo() {
    if ($("#username").length === 0){
        alertMsg("登录后方可更换LOGO！");
        return false;
    }
    layer.open({
        type : 2,
        title : "更换LOGO",
        area : [ '400px', '500px' ],
        shade : 0,
        offset : "5px",
        shadeClose : false,
        content : "/logo/edit",
        btn:['确定','取消'],
        yes:function(index,layero){
            var body = top.layer.getChildFrame('body',index);
            var src = body.find('#logo_url').val();
            if (src==''){
                alertMsg('请上传图片');
                return false;
            }
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            console.log(body.find('#classId').val());
            $.ajax({
                url:"/logo/save",
                type:'post',
                data:{
                    'src':src
                },
                dataType:'json',
                success:function(data){
                    if (data.code == 0) {
                        layer.msg(data.msg,{
                            offset: '10px',
                            icon:1,
                            time:1000
                        },function(){
                            parent.layer.close(index)
                            window.location.reload();
                        });
                    } else if (data.code == 500) {
                        layer.msg(data.msg,{
                            icon:2,
                            time:1500
                        },function(){});
                    }
                }
            });
        },
        btn2 : function() {
            dangerInquiry('确定关闭吗','是的关闭它','我再考虑一下',true,function () {

            });
            return false;
        },
        closeBtn : 1,
        btnAlign:'c',
        success : function(layero, index) {

        },
        cancel : function() {
            dangerInquiry('确定关闭吗','是的关闭它','我再考虑一下',true,function () {

            });
            return false;
        },
        end : function() {
            layer.closeAll();
        }
    });
}

$("#editCountdown").click(function () {
    if ($("#username").length === 0){
        alertMsg("请先登录！");
        return false;
    }
    layer.open({
        type : 2,
        title : "编辑倒计时",
        area : [ '400px', '500px' ],
        shade : 0,
        offset : "5px",
        shadeClose : false,
        content : "/countdown/edit",
        btn:['确定','取消'],
        yes:function(index,layero){
            var body = top.layer.getChildFrame('body',index);
            var title = body.find('#title').val();
            var date = body.find('#date').val();
            var time = body.find('#time').val();
            if (title==''||date==''||time==''){
                alertMsg('内容填写不完整');
                return false;
            }
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            console.log(body.find('#classId').val());
            $.ajax({
                url:"/countdown/update",
                type:'post',
                data:{
                    'id': body.find('#id').val(),
                    'title':title,
                    'date':date,
                    'time':time
                },
                dataType:'json',
                success:function(data){
                    if (data.code == 0) {
                        layer.msg(data.msg,{
                            offset: '10px',
                            icon:1,
                            time:1000
                        },function(){
                            parent.layer.close(index)
                            window.location.reload();
                        });
                    } else if (data.code == 500) {
                        layer.msg(data.msg,{
                            icon:2,
                            time:1500
                        },function(){});
                    }
                }
            });
        },
        btn2 : function() {
            dangerInquiry('确定关闭吗','是的关闭它','我再考虑一下',true,function () {

            });
            return false;
        },
        closeBtn : 1,
        btnAlign:'c',
        success : function(layero, index) {

        },
        cancel : function() {
            dangerInquiry('确定关闭吗','是的关闭它','我再考虑一下',true,function () {

            });
            return false;
        },
        end : function() {
            layer.closeAll();
        }
    });
});

function check(){
    var keyword = $("#searchBody").val();
    $.ajax({
        type: 'POST',
        dataType: 'json',
        url: '/keyword/insert',
        data: {"keyword": keyword},
        cache: false,
        async: true,
        success: function () {
            $("#searchBody").val();
        }
    });
    return true;
}

$("#searchBody").focus();

window.onload = function () {
    getCountdown();
    // 如果是移动端进行的调整
    if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){
        // 更改搜索引擎
        document.searchForm.action="http://m.baidu.com/s";
        // 调整logo大小
        $("#logo").style.width='125px';
    }
};

function getCountdown() {
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: '/countdown/get',
        data: {},
        cache: false,
        async: true,
        success: function (data) {
            countdown_title = data.title;
            endTime = data.endTime;
            if (endTime.length<6){
                endTime[5] = 0;
            }
            end = new Date(endTime[0],endTime[1]-1,endTime[2],endTime[3],endTime[4],endTime[5]);
            countdown();
        }
    });
}

function countdown() {
    var now = new Date();
    var title = countdown_title;
    var today = now;
    var stopTime = end;
    var remain = stopTime.getTime() - today.getTime(),//倒计时毫秒数
        days = parseInt(remain / (60 * 60 * 24 * 1000)),//转换为天
        D = parseInt(remain) - parseInt(days * 60 * 60 * 24 * 1000),//除去天的毫秒数
        hours = parseInt(D / (60 * 60 * 1000)),//除去天的毫秒数转换成小时
        H = D - hours * 60 * 60 * 1000,//除去天、小时的毫秒数
        minutes = parseInt(H / (60 * 1000));//除去天的毫秒数转换成分钟
    var seconds = parseInt((remain - days*60*60*24*1000 - hours*60*60*1000 - minutes*60*1000) / 1000);
    var milliseconds = parseInt(remain - days*60*60*24*1000 - hours*60*60*1000 - minutes*60*1000 - seconds*1000);
    hours = getFormedStyle(hours,2);
    minutes = getFormedStyle(minutes,2);
    seconds = getFormedStyle(seconds,2);
    milliseconds = getFormedStyle(milliseconds,3);
    $("#countDown").html(days + "天" + hours + "小时" + minutes + "分" + seconds + "秒" + milliseconds/* + "毫秒"*/);
    $("#countDownTitle").html('• '+title);
    setTimeout(countdown, 33);
}
function getFormedStyle(source,len) {
    return (Array(len).join('0') + source).slice(-len);
}