layui.use('layer', function () {
    var layer = layui.layer;
});

// 警示询问框 msg:询问的消息; yes:确认按钮的名字; no:取消按钮的名字; isCloseAll:是否关闭全部弹出层; callback:回调函数
function dangerInquiry(msg,yes,no,isCloseAll,callback){
    layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '300px;'
        ,offset: '100px'
        ,shade: 0.8
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,btn: [yes, no]
        ,yes:function(index){
            callback();
            if (isCloseAll){
                layer.closeAll();
            } else {
                layer.close(index);
            }
        }
        ,btnAlign: 'c'
        ,moveType: 1 //拖拽模式，0或者1
        ,content: '<div style="padding: 50px; line-height: 22px; background-color: red; color: white; font-weight: 300;text-align:center;font-size:20px;">'+msg+'</div>'
    });
}

// 常规询问框 msg:询问的消息; yes:确认按钮的名字; no:取消按钮的名字; isCloseAll:是否关闭全部弹出层; callback:回调函数
function normalInquiry(msg,yes,no,isCloseAll,callback){
    layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '300px;'
        ,offset: '100px'
        ,shade: 0.8
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,btn: [yes, no]
        ,yes:function(index){
            callback();
            if (isCloseAll){
                layer.closeAll();
            } else {
                layer.close(index);
            }
        }
        ,btnAlign: 'c'
        ,moveType: 1 //拖拽模式，0或者1
        ,content: '<div style="padding: 50px; line-height: 22px; background-color: darkgreen; color: white; font-weight: 300;text-align:center;font-size:20px;">'+msg+'</div>'
    });
}


// 常规询问框 msg:询问的消息; yes:确认按钮的名字; no:取消按钮的名字; isCloseAll:是否关闭全部弹出层; callback:回调函数
function normalLoginInquiry(msg,btn1,btn2,no){
    layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '300px;'
        ,offset: '100px'
        ,shade: 0.8
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,btn: [btn1, btn2, no]
        ,btn1:function(){
            toLogin('/personal/index')
        }
        ,btn2:function(){
            toRegister('/personal/index')
        }
        ,btnAlign: 'c'
        ,moveType: 1 //拖拽模式，0或者1
        ,content: '<div style="padding: 50px; line-height: 22px; background-color: lightcyan; color: deeppink; font-weight: 300;text-align:center;font-size:20px;">'+msg+'</div>'
    });
}

// 错误提示框
function alertMsg(msg) {
    layer.msg(
        msg,
        {
            offset: '100px',
            anim: 0,
            time: 1000,
            icon:2
        }
    );
}

// 成功提示框
function successMsg(msg) {
    layer.msg(
        msg,
        {
            offset: '100px',
            anim: 0,
            time: 1000,
            icon:1
        }
    );
}

function toLogin(target){
    layer.open({
        type : 2,
        title : "登录",
        area : [ '400px', '400px' ],
        shade : 0,
        offset : "5px",
        shadeClose : false,
        content : "/login",
        btn:['登录'],
        yes:function(index,layero){
            var body = top.layer.getChildFrame('body',index);
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            console.log(body.find('#classId').val());
            $.ajax({
                url:"/login/check",
                type:'post',
                data:{
                    'username':body.find('#username').val(),
                    'password':body.find('#password').val()
                },
                dataType:'json',
                success:function(data){
                    if (data.code == 0) {
                        layer.msg(data.msg,{
                            icon:1,
                            time:1500
                        },function(){
                            parent.layer.close(index)
                            if (target) {
                                location.reload();
                                open(target);
                            } else {
                                location.reload();
                            }
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
        closeBtn : 1,
        btnAlign:'c',
        success : function(layero, index) {

        },
        cancel : function() {
        },
        end : function() {
            layer.closeAll();
            /*location.reload();*/
        }
    });
}