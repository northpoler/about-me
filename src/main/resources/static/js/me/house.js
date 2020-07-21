distinguishClientType();

layui.use('element', function(){
    var element = layui.element;
});

$(function () {
  countdown();
});

function countdown() {
    var time_start = new Date();
    var time_end = new Date($("#beginTime").val());
    var remain = time_end.getTime() - time_start.getTime(),
        days = remain / (60 * 60 * 24 * 1000);
    days = Number(days).toFixed(6);
    let index_dot = days.indexOf(".");
    let days_head = days.substr(0,index_dot+1);
    let days_tail = days.substr(index_dot+1);
    $("#count_down").html(days_head + "<span style='font-size: large'>" + days_tail + "</span>" +" 天");
    setTimeout(countdown, 33);
}