// 记录当次浏览页面献花的次数
var userLikes = 0;

// 添加时间线的按钮点击事件
$("#add").click(function () {
    layer.open({
        type : 2,
        title : "添加",
        area : [ '400px', '330px' ],
        shade : 0,
        offset : "5px",
        shadeClose : false,
        content : "/timeline/add",
        btn:['确定','取消'],
        yes:function(index,layero){
            var body = top.layer.getChildFrame('body',index);
            var content = body.find('#content').val();
            if (content==''){
                layer.msg(
                    '内容还未填写',
                    {
                        offset: '100px',
                        anim: 0,
                        time: 1000,
                        icon:2
                    }
                );
                return false;
            }
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            var data = {
                'occurTime':body.find('#occurTime').val(),
                'content':body.find('#content').val(),
                'contributor':body.find('#contributor').val(),
                'mark':'2'
            };
            asyncPost('/timeline/insert',data,function (data) {
                if (data.result) {
                    layer.msg(data.message,{
                        offset: '10px',
                        icon:1,
                        time:1000
                    },function(){
                        parent.layer.close(index)
                    });
                } else {
                    layer.msg(data.message,{
                        icon:2,
                        time:1500
                    },function(){});
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

// 纠错按钮点击事件
$("#correct").click(function () {
    layer.open({
        type : 2,
        title : "纠错",
        area : [ '400px', '300px' ],
        shade : 0,
        offset : "5px",
        shadeClose : false,
        content : "/timeline/correct",
        btn:['确定','取消'],
        yes:function(index,layero){
            var body = top.layer.getChildFrame('body',index);
            var content = body.find('#content').val();
            if (content==''){
                layer.msg(
                    '内容还未填写',
                    {
                        offset: '100px',
                        anim: 0,
                        time: 1000,
                        icon:2
                    }
                );
                return false;
            }
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            var data = {
                'content':body.find('#content').val(),
                'contributor':body.find('#contributor').val(),
                'mark':'3'
            };
            asyncPost('/timeline/insert',data,function (data) {
                if (data.result) {
                    layer.msg(data.message,{
                        offset: '10px',
                        icon:1,
                        time:1000
                    },function(){
                        parent.layer.close(index)
                    });
                } else {
                    layer.msg(data.message,{
                        icon:2,
                        time:1500
                    },function(){});
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
            /*location.reload();*/
        }
    });
});

// 记录点击献花按钮的时间，以控制点击频率
var clickTime = new Date().getTime();

// 点击献花后执行的方法
function like() {
    userLikes++;
    layer.msg(
        '向李贺臣同志献了'+userLikes+'朵鲜花！',
        {
            offset: '100px',
            anim: 0,
            time: 1000,
            icon:1
        }
    );
    var test = $("#like").text();
    var before = parseInt(test);
    asyncGet('/like/insert',{"count": before},function (data) {
        if (data.result){
            $("#like").text(data.data);
        }
    });
    var className = document.getElementById("portrait").className;
    if (className.indexOf("portrait")==-1){
        $("#portrait").addClass("portrait");
        setTimeout(function () {
            $("#portrait").removeClass("portrait");
        },4800)
    }
}

// 献花按钮点击事件
$("#like_btn").click(function(e){
    var now = new Date().getTime();
    if (now-clickTime>1000){
        var tem="<p class='show'>💐</p>";
        var x=e.pageX;
        var y=e.pageY;
        $(this).append(tem);
        $(".show").css({
            "z-index": 9999,
            'position':'absolute',
            'top':y-20,
            'left':x,
            'color':'yellow'
        });
        $(".show").animate({
            'top':y-300,
            'opacity':0,
            'fontSize':'50px'
        },999,function(){
            $(".show").remove();
        });
        clickTime = now;
        like();
    }
});

// 查看更多细节
function showMore() {
    var text = $("#show-more").html();
    if (text.indexOf("更多")!=-1){
        $("#show-more").html("收起 <<");
    } else {
        $("#show-more").html("更多 >>");
    }
    $("#info-more").slideToggle();
}

// 为锚点跳转添加过渡动画
$(".anchor").click(function(){
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '')
        && location.hostname == this.hostname) {
        var $target = $(this.hash);
        $target = $target.length && $target || $('[name=' + this.hash.slice(1) + ']');
        if ($target.length) {
            var targetOffset = $target.offset().top;
            $('html,body').animate({scrollTop: targetOffset},800);
            return false;
        }
    }
});