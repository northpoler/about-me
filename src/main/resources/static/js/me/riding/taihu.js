distinguishClientType();

var width = $("#head_pic_div").width();
$("#head_pic_div").css("height",width/1920*1079+"px");
$("#map_pic_div").css("height",width/1032*911+"px");
$("#start_pic_div").css("height",width/1920*906+"px");
$("#distance_pic_div").css("height",width/1920*1791+"px");
$("#flag_pic_div").css("height",width/1920*1381+"px");
$("#together_pic_div").css("height",width/1920*1184+"px");
$(".plan_pic_div").css("height",width/1080*720+"px");

layui.use('element', function(){
    var element = layui.element;
});

var status = $("#status").val();
if (status == 0) {
    getCountdown();
} else if (status == 1) {
    $("#count_down").html('骑行活动进行中！');
} else {
    $("#count_down").html('骑行计划已完结！');
}

function getCountdown() {
    var now = new Date();
    var holiday_start = new Date("OCT 2 2020 08:30:00");
    var holiday_end = new Date("OCT 8 2020 00:00:00");
    if (holiday_start.getTime() > now.getTime()) {
        end = holiday_start;
        isEnd = false;
        countdown();
    } else if (holiday_end.getTime() > now.getTime()) {
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
    /*var remain = time_end.getTime() - time_start.getTime(),//倒计时毫秒数
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
    $("#count_down").html("即将启程："+days + "天" + hours + "小时" + minutes + "分" + seconds + "秒"/!* + milliseconds + "毫秒"*!/);*/
    var remain = time_end.getTime() - time_start.getTime(),//倒计时毫秒数
        days = remain / (60 * 60 * 24 * 1000);
    /*days = String(days).replace(/^(.*\..{6}).*$/,"$1");
    days = Number(days);*/
    days = Number(days).toFixed(6);
    let index_dot = days.indexOf(".");
    let days_head = days.substr(0,index_dot+1);
    let days_tail = days.substr(index_dot+1);
    $("#count_down").html("即将启程 ：" + days_head + "<span style='font-size: large'>" + days_tail + "</span>" +" 天");
    setTimeout(countdown, 33);
}

function getFormedStyle(source, len) {
    return (Array(len).join('0') + source).slice(-len);
}

var adjustSliders = $(".adjust-slider");
var lineSlider;
adjustSliders.mousedown(function(e){
    var distance = parseInt($(this).html());
    timer=setInterval(function(){
        var value = $(".layui-slider-tips").html();
        if (value === '' || value == null) {
            value = '0km';
        }
        var sumDistance = distance + parseInt(value.substr(0,value.length-2));
        if (sumDistance <0 || sumDistance > 452) {
            return false;
        } else {
            sildeValueAdjust(distance);
        }
        //按住期间执行的代码
    },200)
});
adjustSliders.mouseup(function(e){
    clearInterval(timer);
});

adjustSliders.click(function () {
    var distance = parseInt($(this).html());
    if ($(this).hasClass("layui-btn-disabled")) {
        return false;
    } else {
        sildeValueAdjust(distance);
    }
});

function sildeValueAdjust(adjustValue) {
    var value = $(".layui-slider-tips").html();
    if (value === '' || value == null) {
        value = '0km';
    }
    var distance = parseInt(value.substr(0,value.length-2));
    distance = distance + parseInt(adjustValue);
    setAdjustBtn(distance);
    lineSlider.setValue(distance);
    changeSlideLine(distance+"km");
}

function setAdjustBtn(value) {
    adjustSliders.each(function () {
        var distance = parseInt($(this).html());
        if (distance + value < 0 || distance + value > 452) {
            $(this).addClass("layui-btn-disabled");
        } else {
            $(this).removeClass("layui-btn-disabled");
        }
    });
}

function changeSlideLine(value) {
    var distance = parseInt(value.substr(0,value.length-2));
    $("#slideLine_distance").html(value+'/452km<span class="me-more-info">'
        +Math.round(distance*100/452*Math.pow(10, 2))/Math.pow(10, 2) +'%')+'</span>';
    setAdjustBtn(distance);
    if (distance<=82){
        $("#slideLine_time").html('2020-10-02 <span class="me-more-info">1/6</span>');
        if (distance<=5) {
            $("#slideLine_place").html('湖州市吴兴区<span class="me-more-info">浙江省</span>');
        } else if (distance <= 39) {
            $("#slideLine_place").html('湖州市长兴县<span class="me-more-info">浙江省</span>');
        } else {
            $("#slideLine_place").html('无锡市宜兴市<span class="me-more-info">江苏省</span>');
        }
    } else if (82 < distance && distance <=175) {
        $("#slideLine_time").html('2020-10-03<span class="me-more-info">2/6</span>');
        if (distance<=87) {
            $("#slideLine_place").html('常州市武进区<span class="me-more-info">江苏省</span>');
        } else {
            $("#slideLine_place").html('无锡市滨湖区<span class="me-more-info">江苏省</span>');
        }
    } else if (175 < distance && distance <= 252) {
        $("#slideLine_time").html('2020-10-04<span class="me-more-info">3/6</span>');
        if (distance<=179) {
            $("#slideLine_place").html('无锡市滨湖区<span class="me-more-info">江苏省</span>');
        } else if (distance <= 184) {
            $("#slideLine_place").html('苏州市相城区<span class="me-more-info">江苏省</span>');
        } else if (distance <= 199) {
            $("#slideLine_place").html('苏州市虎丘区<span class="me-more-info">江苏省</span>');
        } else {
            $("#slideLine_place").html('苏州市吴中区<span class="me-more-info">江苏省</span>');
        }
    } else if (252 < distance && distance <=333) {
        $("#slideLine_time").html('2020-10-05<span class="me-more-info">4/6</span>');
        $("#slideLine_place").html('苏州市吴中区<span class="me-more-info">江苏省</span>');
    } else if (333 < distance && distance <=394) {
        $("#slideLine_time").html('2020-10-06<span class="me-more-info">5/6</span>');
        if (distance<=379) {
            $("#slideLine_place").html('苏州市吴中区<span class="me-more-info">江苏省</span>');
        } else {
            $("#slideLine_place").html('苏州市吴江区<span class="me-more-info">江苏省</span>');
        }
    } else {
        $("#slideLine_time").html('2020-10-07<span class="me-more-info">6/6</span>');
        if (distance<=429) {
            $("#slideLine_place").html('苏州市吴江区<span class="me-more-info">江苏省</span>');
        } else {
            $("#slideLine_place").html('湖州市吴兴区<span class="me-more-info">浙江省</span>');
        }
    }
}

layui.use('slider', function(){
    var slider = layui.slider;
    //渲染
    lineSlider = slider.render({
        elem: '#slideLine'  //绑定元素
        , max: 452
        , setTips: function (value) { //自定义提示文本
            return value + 'km';
        }
        , change: function (value) {
            changeSlideLine(value);
        }
    });
    var defaultDistance = Math.floor(Math.random()*200+22);
    lineSlider.setValue(defaultDistance);
});

function join() {
    layer.open({
        content: '微信或电我：1700 520 9060(李建兵)'
        ,title: '欢迎'
        ,btn: ['复制号码','确定']
        ,yes: function(index, layero){
            var oInput = document.createElement('input');
            oInput.value = '17005209060';
            document.body.appendChild(oInput);
            oInput.select(); // 选择对象
            document.execCommand("Copy");
            oInput.className = 'oInput';
            oInput.style.display='none';
            layer.msg('复制成功！', {offset: 'auto', anim: 0, time: 999});
            layer.close(index);
        }
        ,btn2: function(index, layero){
            layer.close(index);
        }
    });
}

function advise() {
    layer.prompt({
        formType: 2,
        value: '',
        title: '对这次行程的建议或其他想说的话'
    }, function(value, index, elem){
        showloading(true);
        asyncPost('/riding/taihu/advise',{'advise':value},function (data) {
            showloading(false);
            if (data.result) {
                layer.msg(data.message,{
                    icon:1,
                    time:1500
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
    });
}

var initPhotoSwipeFromDOM = function(gallerySelector) {

    // 解析来自DOM元素幻灯片数据（URL，标题，大小...）
    // (children of gallerySelector)
    var parseThumbnailElements = function(el) {
        var thumbElements = el.childNodes,
            numNodes = thumbElements.length,
            items = [],
            figureEl,
            linkEl,
            size,
            item;

        for(var i = 0; i < numNodes; i++) {

            figureEl = thumbElements[i]; // <figure> element

            // 仅包括元素节点
            if(figureEl.nodeType !== 1) {
                continue;
            }
            linkEl = figureEl.children[0]; // <a> element

            size = linkEl.getAttribute('data-size').split('x');

            // 创建幻灯片对象
            item = {
                src: linkEl.getAttribute('href'),
                w: parseInt(size[0], 10),
                h: parseInt(size[1], 10)
            };



            if(figureEl.children.length > 1) {
                // <figcaption> content
                item.title = figureEl.children[1].innerHTML;
            }

            if(linkEl.children.length > 0) {
                // <img> 缩略图节点, 检索缩略图网址
                item.msrc = linkEl.children[0].getAttribute('src');
            }

            item.el = figureEl; // 保存链接元素 for getThumbBoundsFn
            items.push(item);
        }

        return items;
    };

    // 查找最近的父节点
    var closest = function closest(el, fn) {
        return el && ( fn(el) ? el : closest(el.parentNode, fn) );
    };

    // 当用户点击缩略图触发
    var onThumbnailsClick = function(e) {
        e = e || window.event;
        e.preventDefault ? e.preventDefault() : e.returnValue = false;

        var eTarget = e.target || e.srcElement;

        // find root element of slide
        var clickedListItem = closest(eTarget, function(el) {
            return (el.tagName && el.tagName.toUpperCase() === 'FIGURE');
        });

        if(!clickedListItem) {
            return;
        }

        // find index of clicked item by looping through all child nodes
        // alternatively, you may define index via data- attribute
        var clickedGallery = clickedListItem.parentNode,
            childNodes = clickedListItem.parentNode.childNodes,
            numChildNodes = childNodes.length,
            nodeIndex = 0,
            index;

        for (var i = 0; i < numChildNodes; i++) {
            if(childNodes[i].nodeType !== 1) {
                continue;
            }

            if(childNodes[i] === clickedListItem) {
                index = nodeIndex;
                break;
            }
            nodeIndex++;
        }



        if(index >= 0) {
            // open PhotoSwipe if valid index found
            openPhotoSwipe( index, clickedGallery );
        }
        return false;
    };

    // parse picture index and gallery index from URL (#&pid=1&gid=2)
    var photoswipeParseHash = function() {
        var hash = window.location.hash.substring(1),
            params = {};

        if(hash.length < 5) {
            return params;
        }

        var vars = hash.split('&');
        for (var i = 0; i < vars.length; i++) {
            if(!vars[i]) {
                continue;
            }
            var pair = vars[i].split('=');
            if(pair.length < 2) {
                continue;
            }
            params[pair[0]] = pair[1];
        }

        if(params.gid) {
            params.gid = parseInt(params.gid, 10);
        }

        return params;
    };

    var openPhotoSwipe = function(index, galleryElement, disableAnimation, fromURL) {
        var pswpElement = document.querySelectorAll('.pswp')[0],
            gallery,
            options,
            items;

        items = parseThumbnailElements(galleryElement);

        // 这里可以定义参数
        options = {
            barsSize: {
                top: 100,
                bottom: 100
            },
            fullscreenEl : false, // 是否支持全屏按钮
            shareButtons: [
                /*{id:'wechat', label:'分享微信', url:'#'},
                {id:'weibo', label:'新浪微博', url:'#'},*/
                {id:'download', label:'保存图片', url:'{{raw_image_url}}', download:true}
            ], // 分享按钮
            tapToClose: true, //默认关闭
            // define gallery index (for URL)
            galleryUID: galleryElement.getAttribute('data-pswp-uid'),

            getThumbBoundsFn: function(index) {
                // See Options -> getThumbBoundsFn section of documentation for more info
                var thumbnail = items[index].el.getElementsByTagName('img')[0], // find thumbnail
                    pageYScroll = window.pageYOffset || document.documentElement.scrollTop,
                    rect = thumbnail.getBoundingClientRect();

                return {x:rect.left, y:rect.top + pageYScroll, w:rect.width};
            }

        };

        // PhotoSwipe opened from URL
        if(fromURL) {
            if(options.galleryPIDs) {
                // parse real index when custom PIDs are used
                for(var j = 0; j < items.length; j++) {
                    if(items[j].pid == index) {
                        options.index = j;
                        break;
                    }
                }
            } else {
                // in URL indexes start from 1
                options.index = parseInt(index, 10) - 1;
            }
        } else {
            options.index = parseInt(index, 10);
        }

        // exit if index not found
        if( isNaN(options.index) ) {
            return;
        }

        if(disableAnimation) {
            options.showAnimationDuration = 0;
        }

        // Pass data to PhotoSwipe and initialize it
        gallery = new PhotoSwipe( pswpElement, PhotoSwipeUI_Default, items, options);
        gallery.init();
    };

    // loop through all gallery elements and bind events
    var galleryElements = document.querySelectorAll( gallerySelector );

    for(var i = 0, l = galleryElements.length; i < l; i++) {
        galleryElements[i].setAttribute('data-pswp-uid', i+1);
        galleryElements[i].onclick = onThumbnailsClick;
    }

    // Parse URL and open gallery if it contains #&pid=3&gid=1
    var hashData = photoswipeParseHash();
    if(hashData.pid && hashData.gid) {
        openPhotoSwipe( hashData.pid ,  galleryElements[ hashData.gid - 1 ], true, true );
    }
};

// execute above function
initPhotoSwipeFromDOM('.my-gallery');
$(document).ready(function () {
    var mySwiper = new Swiper ('.swiper-container', {
        direction: 'horizontal', // 垂直切换选项
        loop: true, // 循环模式选项

        // 如果需要分页器
        pagination: {
            el: '.swiper-pagination',
            /*type: 'bullets',*/
            //type: 'fraction',
            //type : 'progressbar',
            //type : 'custom',
            clickable :true,
            /*renderBullet: function (index, className) {
                return '<span class="' + className + '">' + (index + 1) + '</span>';
            },*/
        },

        // 如果需要前进后退按钮
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        autoplay: true,//自动播放
        grabCursor : true,//显示抓手
        //autoHeight: true, //高度随内容变化
        //preloadImages:false,//是否加载所有图片才初始化
        //updateOnImagesReady : true,//加载完图片后是否重新初始化
        // 如果需要滚动条
        scrollbar: {
            el: '.swiper-scrollbar',
            hide: true
        },
        //zoom : true,// 图片可缩放
    })
});

$(function () {
    // 发送心跳
    heartbeat();
});

function heartbeat() {
    var pageName = $("#page_name").val();
    if (pageName == null || pageName == '') {
        pageName = 'RIDING_TAIHU';
    }
    startSendHeartbeat(pageName);
    setInterval(function () {
        startSendHeartbeat(pageName);
    },7500);
}

function startSendHeartbeat(pageName) {
    sendHeartbeat(pageName,function (data) {
        if ($("#visit_count_now").size()==1) {
            $("#visit_count_now").html(data.data);
        }
    })
}