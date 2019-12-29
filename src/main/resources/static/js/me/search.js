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

function editCountdown() {
    $("#countdown_edit_div").slideToggle();
}

function cancleEditCountdown() {
    $("#countDownTitle").html(title);
    $("#countdown_title").val(title);
    $("#countdown_edit_div").slideToggle();
}

function changeTitle() {
    $("#countDownTitle").html($('#countdown_title').val());
}

function saveCountdown() {
    if ($("#username").length === 0){
        end = new Date($('#countdown_date').val()+" "+$('#countdown_time').val());
        layer.msg("编辑成功！<br>登录后可以永久保存~~",{
            icon:1,
            time:2000
        },function(){
            $("#countdown_edit_div").slideToggle();
        });
    } else {
        $.ajax({
            url:"/countdown/update",
            type:'post',
            data:{
                'id': $('#countdown_id').val(),
                'title':$('#countdown_title').val(),
                'date':$('#countdown_date').val(),
                'time':$('#countdown_time').val()
            },
            dataType:'json',
            success:function(data){
                if (data.result) {
                    end = new Date($('#countdown_date').val()+" "+$('#countdown_time').val());
                    layer.msg(data.msg,{
                        icon:1,
                        time:1000
                    },function(){
                        $("#countdown_edit_div").slideToggle();
                    });
                } else {
                    layer.msg(data.msg,{
                        icon:2,
                        time:1500
                    },function(){});
                }
            }
        });
    }
}

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
    // 获取倒计时
    getCountdown();
    // 如果是移动端进行的调整
    if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){
        // 更改搜索引擎
        document.searchForm.action="http://m.baidu.com/s";
    }
    // 获取天气
    getWeather();
};

function getWeather() {
    $.ajax({
        type: 'POST',
        dataType: 'json',
        url: '/weather/get',
        data: {"ip":returnCitySN["cip"]},
        cache: false,
        async: true,
        success: function (data) {
            if (data.result){
                var weather = data.weather;
                $("#weatherPic").attr('src',weather.weatherPic);
                $("#weatherPic").attr('src');
                $("#weather").html(weather.weather);
                $("#temperature").html(weather.temperature);
                $("#city").html(weather.city);
                $("#publish_time").html(weather.publishTime + "【" + weather.timeZone + "】");
                $("#location").html(weather.country + " • " + weather.province + " • " + weather.city);
                $("#longitude").html(weather.longitude);
                $("#latitude").html(weather.latitude);
                $("#feeling").html(weather.feeling);
                $("#relative_humidity").html(weather.relativeHumidity);
                $("#precipitation").html(weather.precipitation);
                $("#visibility").html(weather.visibility);
                $("#atmos").html(weather.atmos);
                $("#wind_direction").html(weather.windDirection + "【" + weather.windAngle + "】");
                $("#wind_force").html(weather.windForce + "【"+ weather.windSpeed + "】");
                $("#weather_div").removeClass("layui-hide")
            }
        }
    });
    // 每三分钟更新
    setTimeout(getWeather, 1000 * 60 * 3);
}

function showMore() {
    $("#weather_detail_div").slideToggle();
}

function getCountdown() {
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: '/countdown/get',
        data: {},
        cache: false,
        async: true,
        success: function (data) {
            title = data.title;
            $("#countDownTitle").html(title);
            endTime = data.endTime;
            if (endTime.length<6){
                endTime[5] = 0;
            }
            end = new Date(endTime[0],endTime[1]-1,endTime[2],endTime[3],endTime[4],endTime[5]);
            $("#countdown_id").val(data.id);
            $("#countdown_title").val(data.title);
            $("#countdown_date").val(data.date);
            $("#countdown_time").val(data.time);
            countdown();
        }
    });
}

function countdown() {
    var now = new Date();
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
    setTimeout(countdown, 33);
}
function getFormedStyle(source,len) {
    return (Array(len).join('0') + source).slice(-len);
}