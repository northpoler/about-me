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
            var iframeWin = window[layero.find('iframe')[0]['name']];
            var data = {
                'occurTime':body.find('#occurTime').val(),
                'content':body.find('#content').val(),
                'contributor':body.find('#contributor').val(),
                'mark':'2'
            };
            showloading(true);
            asyncPost('/timeline/insert',data,function (data) {
                showloading(false);
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
            var iframeWin = window[layero.find('iframe')[0]['name']];
            var data = {
                'content':body.find('#content').val(),
                'contributor':body.find('#contributor').val(),
                'mark':'3'
            };
            showloading(true);
            asyncPost('/timeline/insert',data,function (data) {
                showloading(false);
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

function downloadPic() {
    window.location.href="download/picture/li_hechen/jpg";
}

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

var visitCount = $("#visit_count").val();
layui.use('layer', function () {
    var layer = layui.layer;
    layer.msg(
        '欢迎，你是第' + visitCount + '位访客！',
        {
            offset: 'auto',
            anim: 4,
            time: 2000,
            icon:6
        }
    );
});
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