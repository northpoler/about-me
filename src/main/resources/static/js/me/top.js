layui.use('element', function () {
    var element = layui.element;
});

layui.use('form', function(){
    var form = layui.form;

    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});

$("#personalCenter").click(function () {
    window.open("/personal/index","_blank");
});

$("#logout").click(function () {
    dangerInquiry("确定注销吗",'去意已决', '我再考虑一下',false,function () {
        asyncGet("/logout",{},function (data) {
            if (data.result) {
                layer.msg(data.message,{
                    icon:1,
                    time:1500
                },function(){
                    parent.layer.close(index)
                });
                location.reload();
            } else {
                layer.msg(data.message,{
                    icon:2,
                    time:1500
                },function(){});
            }
        });
    });
});

$("#loginBtn").click(function () {
    toLogin();
});

$("#registerBtn").click(function () {
    toRegister();
});

function toRegister(target) {
    layer.closeAll();
    layer.open({
        type : 2,
        title : "注册",
        area : [ '400px', '400px' ],
        shade : 0,
        offset : "5px",
        shadeClose : false,
        content : "/register",
        btn:['注册'],
        yes:function(index,layero){
            var body = top.layer.getChildFrame('body',index);
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();

            var isChecked = true;
            var message = '';
            var username = body.find('#username').val();
            var password = body.find('#password').val();
            var repassword = body.find('#repassword').val();
            var staticSalt = body.find('#staticSalt').val();
            var phone = body.find('#phone').val();

            if (username == ''){
                isChecked = false;
                message = '请填写用户名'
            }
            if (password == ''){
                isChecked = false;
                message = '请填写密码'
            }
            if (password != repassword){
                isChecked = false;
                message = '密码须输入一致'
            }

            if (!isChecked){
                layer.msg(
                    message,
                    {
                        offset: '100px',
                        anim: 0,
                        time: 1000,
                        icon:2
                    }
                );
                return false;
            }

            var data = {
                'username':username,
                'password':hex_md5(password+staticSalt),
                'phone':phone
            };
            asyncPost('/register/save',data,function (data) {
                if (data.result) {
                    layer.msg(data.message,{
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
                } else {
                    layer.msg(data.message,{
                        icon:2,
                        time:1500
                    },function(){});
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
        }
    });
}

function changeInfo() {
    layer.open({
        type : 2,
        title : "修改个人信息",
        area : [ '400px', '300px' ],
        shade : 0,
        offset : "5px",
        shadeClose : false,
        content : "/user/edit",
        btn:['确定修改'],
        yes:function(index,layero){
            var body = top.layer.getChildFrame('body',index);
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            var data = {
                'username':body.find('#username').val(),
                'password':body.find('#password').val()
            };
            asyncPost('/user/edit',data,function (data) {
                if (data.result) {
                    layer.msg(data.message,{
                        icon:1,
                        time:1500
                    },function(){
                        parent.layer.close(index)
                        location.reload();
                    });
                } else {
                    layer.msg(data.message,{
                        icon:2,
                        time:1500
                    },function(){});
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
        }
    });
}