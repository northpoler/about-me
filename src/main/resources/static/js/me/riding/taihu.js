var visitCount = $("#visit_count").val();
layui.use('layer', function () {
    var layer = layui.layer;
    layer.msg(
        '欢迎，你是第' + visitCount + '位访客！',
        {
            offset: 'auto',
            anim: 4,
            time: 2000,
            icon: 6
        }
    );
});

getCountdown();

function getCountdown() {
    var now = new Date();
    var holiday_start = new Date("OCT 1 2020 00:00:00");
    var holiday_end = new Date("OCT 8 2020 00:00:00");
    if (holiday_start.getTime() > now.getTime()) {
        $("#main_title").removeClass('layui-hide');
        end = holiday_start;
        isEnd = false;
        countdown();
    } else if (holiday_end.getTime() > now.getTime()) {
        $("#main_title").addClass('layui-hide');
        $("#count_down").html('骑行活动进行中！');
    } else {
        $("#count_down").html('骑行计划已完结！');
    }
}

function countdown() {
    var time_start;
    var time_end;
    if (isEnd) {
        time_start = end;
        time_end = new Date();
    } else {
        time_start = new Date();
        time_end = end;
    }
    var remain = time_end.getTime() - time_start.getTime(),//倒计时毫秒数
        days = parseInt(remain / (60 * 60 * 24 * 1000)),//转换为天
        D = parseInt(remain) - parseInt(days * 60 * 60 * 24 * 1000),//除去天的毫秒数
        hours = parseInt(D / (60 * 60 * 1000)),//除去天的毫秒数转换成小时
        H = D - hours * 60 * 60 * 1000,//除去天、小时的毫秒数
        minutes = parseInt(H / (60 * 1000));//除去天的毫秒数转换成分钟
    if (remain <= 0) {
        getCountdown();
        return;
    }
    var seconds = parseInt((remain - days * 60 * 60 * 24 * 1000 - hours * 60 * 60 * 1000 - minutes * 60 * 1000) / 1000);
    var milliseconds = parseInt(remain - days * 60 * 60 * 24 * 1000 - hours * 60 * 60 * 1000 - minutes * 60 * 1000 - seconds * 1000);
    hours = getFormedStyle(hours, 2);
    minutes = getFormedStyle(minutes, 2);
    seconds = getFormedStyle(seconds, 2);
    milliseconds = getFormedStyle(milliseconds, 3);
    $("#count_down").html(days + "天" + hours + "小时" + minutes + "分" + seconds + "秒" + milliseconds/* + "毫秒"*/);
    setTimeout(countdown, 33);
}

function getFormedStyle(source, len) {
    return (Array(len).join('0') + source).slice(-len);
}