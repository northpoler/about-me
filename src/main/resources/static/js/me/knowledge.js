/*layui.use('util', function(){
        var util = layui.util;

        //执行
        util.fixbar({
            bar1: "<i style=\"font-size: larger;font-weight: bolder\" class=\"layui-icon layui-icon-home\"></i>"
            ,css: {right: "10%", bottom: "10%"}
            ,click: function(type){
                if(type === 'bar1'){
                    window.open("/search",'_self');
                }
            }
        });
    });*/

var E = window.wangEditor;
var editor = new E('#editor');
// 使用 base64 保存图片
editor.customConfig.uploadImgShowBase64 = true;
// 自定义配置颜色（字体颜色、背景色）
editor.customConfig.colors = [
    '#000000',
    '#666666',
    '#aaaaaa',
    '#ffffff',
    '#ff0000',
    '#ff6600',
    '#ffaa00',
    '#ffff00',
    '#003300',
    '#00aa00',
    '#00ff00',
    '#003300',
    '#000066',
    '#0000aa',
    '#0000ff',
    '#1c487f',
    '#4d80bf',
    '#c24f4a',
    '#8baa4a',
    '#7b5ba1',
    '#46acc8',
    '#f9963b'
];
// 表情面板可以有多个 tab ，因此要配置成一个数组。数组每个元素代表一个 tab 的配置
editor.customConfig.emotions = [
    {
        // tab 的标题
        title: '默认',
        // type -> 'emoji' / 'image'
        type: 'image',
        // content -> 数组
        content: [{
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7a/shenshou_thumb.gif",
            alt : "[草泥马]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif",
            alt : "[神马]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/fuyun_thumb.gif",
            alt : "[浮云]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c9/geili_thumb.gif",
            alt : "[给力]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f2/wg_thumb.gif",
            alt : "[围观]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/vw_thumb.gif",
            alt : "[威武]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6e/panda_thumb.gif",
            alt : "[熊猫]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/81/rabbit_thumb.gif",
            alt : "[兔子]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/otm_thumb.gif",
            alt : "[奥特曼]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/15/j_thumb.gif",
            alt : "[囧]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/89/hufen_thumb.gif",
            alt : "[互粉]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c4/liwu_thumb.gif",
            alt : "[礼物]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/ac/smilea_thumb.gif",
            alt : "[呵呵]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/0b/tootha_thumb.gif",
            alt : "[嘻嘻]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6a/laugh.gif",
            alt : "[哈哈]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/14/tza_thumb.gif",
            alt : "[可爱]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/af/kl_thumb.gif",
            alt : "[可怜]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a0/kbsa_thumb.gif",
            alt : "[挖鼻屎]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f4/cj_thumb.gif",
            alt : "[吃惊]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6e/shamea_thumb.gif",
            alt : "[害羞]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c3/zy_thumb.gif",
            alt : "[挤眼]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/29/bz_thumb.gif",
            alt : "[闭嘴]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/71/bs2_thumb.gif",
            alt : "[鄙视]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/lovea_thumb.gif",
            alt : "[爱你]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/9d/sada_thumb.gif",
            alt : "[泪]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/19/heia_thumb.gif",
            alt : "[偷笑]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/8f/qq_thumb.gif",
            alt : "[亲亲]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/b6/sb_thumb.gif",
            alt : "[生病]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/58/mb_thumb.gif",
            alt : "[太开心]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/17/ldln_thumb.gif",
            alt : "[懒得理你]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/98/yhh_thumb.gif",
            alt : "[右哼哼]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/zhh_thumb.gif",
            alt : "[左哼哼]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a6/x_thumb.gif",
            alt : "[嘘]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/af/cry.gif",
            alt : "[衰]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/73/wq_thumb.gif",
            alt : "[委屈]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/9e/t_thumb.gif",
            alt : "[吐]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f3/k_thumb.gif",
            alt : "[打哈欠]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/27/bba_thumb.gif",
            alt : "[抱抱]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7c/angrya_thumb.gif",
            alt : "[怒]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/5c/yw_thumb.gif",
            alt : "[疑问]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a5/cza_thumb.gif",
            alt : "[馋嘴]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/88_thumb.gif",
            alt : "[拜拜]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/e9/sk_thumb.gif",
            alt : "[思考]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/24/sweata_thumb.gif",
            alt : "[汗]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7f/sleepya_thumb.gif",
            alt : "[困]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6b/sleepa_thumb.gif",
            alt : "[睡觉]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/90/money_thumb.gif",
            alt : "[钱]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/0c/sw_thumb.gif",
            alt : "[失望]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/cool_thumb.gif",
            alt : "[酷]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/8c/hsa_thumb.gif",
            alt : "[花心]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/49/hatea_thumb.gif",
            alt : "[哼]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/36/gza_thumb.gif",
            alt : "[鼓掌]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d9/dizzya_thumb.gif",
            alt : "[晕]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/1a/bs_thumb.gif",
            alt : "[悲伤]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/62/crazya_thumb.gif",
            alt : "[抓狂]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/91/h_thumb.gif",
            alt : "[黑线]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/yx_thumb.gif",
            alt : "[阴险]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/89/nm_thumb.gif",
            alt : "[怒骂]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/hearta_thumb.gif",
            alt : "[心]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/ea/unheart.gif",
            alt : "[伤心]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/58/pig.gif",
            alt : "[猪头]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d6/ok_thumb.gif",
            alt : "[ok]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d9/ye_thumb.gif",
            alt : "[耶]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d8/good_thumb.gif",
            alt : "[good]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c7/no_thumb.gif",
            alt : "[不要]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d0/z2_thumb.gif",
            alt : "[赞]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/come_thumb.gif",
            alt : "[来]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d8/sad_thumb.gif",
            alt : "[弱]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/91/lazu_thumb.gif",
            alt : "[蜡烛]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6a/cake.gif",
            alt : "[蛋糕]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d3/clock_thumb.gif",
            alt : "[钟]"
        }, {
            src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/1b/m_thumb.gif",
            alt : "[话筒]"
        }
        ]
    },
    {
        // tab 的标题
        title: 'emoji',
        // type -> 'emoji' / 'image'
        type: 'emoji',
        // content -> 数组
        content:"😀 😃 😄 😁 😆 😅 😂 😊 😇 🙂 🙃 😉 😓 😪 😴 🙄 🤔 😬 🤐".split(/\s/)
    }
];

$(function () {
    getContent();
});

function getContent(){
    if ($("#username").length != 0){
        asyncGet('/knowledge/get',{},function (data) {
            if (data.result){
                $("#editor").html(data.data);
            }
        })
    } else {
        $("#editor").html("<p>在这里，可以随时记录<b>任何你想记录的信息</b> .......</p>");
    }
}

$("#edit").click(function () {
    if ($("#username").length === 0){
        alertMsg("请先登录！");
        return false;
    }
    editor.create();
    /*autoSave();*/
    $("#show").removeClass("layui-hide");
    $("#save").removeClass("layui-hide");
    $("#edit").addClass("layui-hide");
});

$("#show").click(function () {
    dangerInquiry('确定直接退出','是的','稍等',true,function () {
        window.location.reload();
    });
});

//每半分钟自动保存
function autoSave() {
    setInterval(function () {
        save();
    }, 30000);
}

$("#save").click(function () {
    save();
});

//保存
function save() {
    asyncPost('/knowledge/save',{content:editor.txt.html()},function (data) {
        if (data.result){
            layer.msg(data.message, {offset: '200px', anim: 0, time: 666},function () {
                window.location.reload();
            });
        } else {
            alertMsg(data.message);
        }
    });
}

/*// 禁用编辑功能
editor.$textElem.attr('contenteditable', false)

// 开启编辑功能
editor.$textElem.attr('contenteditable', true)*/