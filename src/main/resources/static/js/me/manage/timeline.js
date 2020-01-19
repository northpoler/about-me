var table;
layui.use('table', function () {
    table = layui.table;
    renderReleasedData();
    table.on('edit(test)', function (obj) {
        layer.open({
            type: 1
            ,title: false //不显示标题栏
            ,closeBtn: false
            ,area: '300px;'
            ,offset: '100px'
            ,shade: 0.8
            ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
            ,btn: ['保存', '不了']
            ,yes:function(index){
                var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段
                var data = {
                    "id": data.id,
                    "field": field,
                    "value": value
                };
                asyncPost('timeline/update', data, function (data) {
                    if (data.result) {
                        layer.msg(data.message, {
                            offset: '10px',
                            icon: 1,
                            time: 1000
                        }, function () {
                            parent.layer.close(index)
                        });
                    }
                });
            }
            ,btn2:function () {
                location.reload();
            }
            ,btnAlign: 'c'
            ,moveType: 1 //拖拽模式，0或者1
            ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;text-align:center;font-size:20px;">确定更改吗~!</div>'

        });
    });

    //监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                var post_data = {
                    "id": data.id,
                    "field": 'mark',
                    "value": '1'
                };
                updateTimeline(post_data);
                obj.del();
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            layer.prompt({
                formType: 2
                , value: data.content
            }, function (value, index) {
                obj.update({
                    content: value
                });
                var post_data = {
                    "id": data.id,
                    "field": 'content',
                    "value": value
                };
                updateTimeline(post_data)
                layer.close(index);
            });
        } else if (obj.event === 'turn') {
            var occurTime = data.occurTime;
            var reg = /^19[4-9]{2}年$/;
            if(!reg.test(occurTime)){
                alertMsg("时间格式不对，不能转正，请检查！");
                return false;
            }
            var post_data = {
                "id": data.id,
                "field": 'mark',
                "value": '0'
            };
            updateTimeline(post_data);
            obj.del();
            layer.close(index);
        }
    });
});

layui.use('element', function(){
    var element = layui.element;
    //一些事件监听
    element.on('tab(nav)', function(data){
        changeNav(data.index);
    });
});


function changeNav(index) {
    renderTable(index);
}

function renderTable(index) {
    if (0 == index) {
        renderReleasedData();
    } else if (1 == index) {
        renderAddedData();
    } else if (2==index) {
        renderCorrectedData();
    }
}

function renderReleasedData() {
    table.render({
        elem: '#data_release'
        , url: 'timeline/table/0'
        , toolbar: '#tool_bar_release'
        , title: '时间线'
        , cols: [
            [{type: 'checkbox'}
                , {field: 'id', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true, hide: true}
                , {field: 'occurTime', title: '时间', width: 80, edit: 'text', template: '{{occurTime}}'}
                , {field: 'content', title: '内容', minWidth: 100, edit: 'text', sort: true, template: '{{content}}'}
                , {field: 'contributor', title: '提供者', width: 80, edit: 'text', sort: true, hide: true}
                , {fixed: 'right', title:'操作', toolbar: '#bar_release', width:150}
            ]
        ]
    });
}

function renderAddedData() {
    table.render({
        elem: '#data_add'
        , url: 'timeline/table/2'
        , toolbar: '#tool_bar_add'
        , title: '时间线'
        , cols: [
            [{type: 'checkbox'}
                , {field: 'id', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true, hide: true}
                , {field: 'occurTime', title: '时间', width: 80, edit: 'text', template: '{{occurTime}}'}
                , {field: 'content', title: '内容', minWidth: 100, edit: 'text', sort: true, template: '{{content}}'}
                , {field: 'contributor', title: '提供者', width: 80, edit: 'text', sort: true, hide: true}
                , {fixed: 'right', title:'操作', toolbar: '#bar_add', width:200}
            ]
        ]
    });
}

function renderCorrectedData() {
    table.render({
        elem: '#data_correct'
        , url: 'timeline/table/3'
        , toolbar: '#tool_bar_correct'
        , title: '时间线'
        , cols: [
            [{type: 'checkbox'}
                , {field: 'id', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true, hide: true}
                , {field: 'content', title: '内容', minWidth: 100, edit: 'text', sort: true, template: '{{content}}'}
                , {field: 'contributor', title: '提供者', width: 80, edit: 'text', sort: true, hide: true}
                , {fixed: 'right', title:'操作', toolbar: '#bar_correct', width:150}
            ]
        ]
    });
}

function tips() {
    layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '300px;'
        ,offset: '100px'
        ,shade: 0.8
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,btn: ['确定']
        ,yes:function(){
            layer.closeAll();
        }
        ,btnAlign: 'c'
        ,moveType: 1 //拖拽模式，0或者1
        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;text-align:center;font-size:20px;">' +
            '换行：--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>加粗：/&nbsp;/&nbsp;&nbsp;+&nbsp;~~<br>粉色：## + ~~<br>粗粉：/&nbsp;# + ~~<br>' +
            '</div>'

    });
}

function updateTimeline(data) {
    asyncPost('timeline/update', data, function (data) {
        if (data.result) {
            layer.msg(data.message, {
                offset: '10px',
                icon: 1,
                time: 1000
            }, function () {
            });
        }
    });
}