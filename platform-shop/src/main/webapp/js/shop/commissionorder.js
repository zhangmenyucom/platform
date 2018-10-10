$(function () {
    $("#jqGrid").Grid({
        url: '../commissionorder/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '用户名', name: 'nickname', index: 'nickname', width: 80},
            {
                label: '头像', name: 'avatar', index: 'avatar', width: 40, formatter: function (value) {
                return transImg(value);
            }
            },
            {label: '佣金订单号', name: 'orderSn', index: 'order_sn', width: 80},
            {label: '佣金数额', name: 'gainBalance', index: 'gain_balance', width: 40},
            {label: '订单来源人', name: 'sourceUserName', index: 'source_user_name', width: 40},
            {
                label: '来源人头像', name: 'sourceAvatar', index: 'source_avatar', width: 40, formatter: function (value) {
                return transImg(value);
            }
            },
            {label: '佣金说明', name: 'detail', index: 'detail', width: 120},
            {label: '来源商品', name: 'goodsName', index: 'goods_name', width: 40},
            {
                label: '创建时间', name: 'createTime', index: 'create_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        commissionOrder: {},
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.commissionOrder = {};
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.commissionOrder.id == null ? "../commissionorder/save" : "../commissionorder/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.commissionOrder),
                type: "POST",
                contentType: "application/json",
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../commissionorder/delete",
                    params: JSON.stringify(ids),
                    type: "POST",
                    contentType: "application/json",
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../commissionorder/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.commissionOrder = r.commissionOrder;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'orderSn': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function () {
            vm.q = {
                orderSn: ''
            }
            vm.reload();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        }
    }
});