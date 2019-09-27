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