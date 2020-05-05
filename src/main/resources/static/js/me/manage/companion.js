var table;
layui.use('table', function () {
    table = layui.table;
    renderTable();
    table.on('edit(test)', function (obj) {
        layer.open({
            type: 1
            ,
            title: false //不显示标题栏
            ,
            closeBtn: false
            ,
            area: '300px;'
            ,
            offset: '100px'
            ,
            shade: 0.8
            ,
            id: 'LAY_layuipro' //设定一个id，防止重复弹出
            ,
            btn: ['保存', '不了']
            ,
            yes: function (index) {
                var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段
                var data = {
                    "id": data.id,
                    "field": field,
                    "value": value
                };
                asyncPost('companion/update', data, function (data) {
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
            ,
            btn2: function () {
                location.reload();
            }
            ,
            btnAlign: 'c'
            ,
            moveType: 1 //拖拽模式，0或者1
            ,
            content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;text-align:center;font-size:20px;">确定更改吗~!</div>'

        });
    });

    //监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除么', function (index) {
                var post_data = {
                    "id": data.id,
                    "field": 'mark',
                    "value": '1'
                };
                updateCompanion(post_data);
                obj.del();
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            layer.prompt({
                formType: 2
                , value: data.note
            }, function (value, index) {
                obj.update({
                    content: value
                });
                var post_data = {
                    "id": data.id,
                    "field": 'note',
                    "value": value
                };
                updateCompanion(post_data);
                layer.close(index);
            });
        }
    });
});

layui.use('element', function () {
    var element = layui.element;
});

function renderTable() {
    table.render({
        elem: '#data'
        , url: 'companion/table'
        , toolbar: '#tool_bar'
        , title: '参与人'
        , cols: [
            [{type: 'checkbox'}
                , {field: 'id', title: 'ID', fixed: 'left', unresize: true, sort: true, hide: true}
                , {field: 'name', title: '姓名', edit: 'text'}
                , {field: 'note', title: '备注', edit: 'text'}
                , {fixed: 'right', title: '操作', toolbar: '#bar'}
            ]
        ]
    });
}

function updateCompanion(data) {
    asyncPost('companion/update', data, function (data) {
        if (data.result) {
            layer.msg(data.message, {
                offset: '10px',
                icon: 1,
                time: 1000
            }, function () {
                renderTable();
            });
        }
    });
}

function add() {
    layer.prompt({
        formType: 2,
        value: '格式：姓名##说明。如：李建兵##本人',
        title: '添加'
    }, function (value, index, elem) {
        showloading(true);
        asyncPost('companion/add', {'value': value}, function (data) {
            showloading(false);
            if (data.result) {
                layer.msg(data.message, {
                    icon: 1,
                    time: 1500
                }, function () {
                    parent.layer.close(index)
                    renderTable();
                });
            } else {
                layer.msg(data.message, {
                    icon: 2,
                    time: 1500
                }, function () {
                });
            }
        });
    });
}