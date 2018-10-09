$(function () {
    $("#jqGrid").Grid({
        url: '../withdraworder/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '流水号', name: 'orderSn', index: 'order_sn', width: 80},
            {label: '提现人id', name: 'userId', index: 'user_id', width: 40},
            {label: '昵称', name: 'nickname', index: 'nickname', width: 80},
            {label: '真实姓名', name: 'realName', index: 'real_name', width: 80},
            {label: '提现金额', name: 'withdrawAmount', index: 'withdraw_amount', width: 80},
            {label: '提现账户', name: 'withdrawAccount', index: 'withdraw_account', width: 80},
            {
                label: '账户类型', name: 'accountType', index: 'account_type', width: 80, formatter: function (value) {
                if (value == 0) {
                    return '微信';
                } else if (value == 1) {
                    return '支付宝';
                } else if (value == 3) {
                    return '银行卡';
                }
                return value;
            }
            },
            {
                label: '提现状态', name: 'status', index: 'status', width: 80, formatter: function (value) {
                if (value == 0) {
                    return '提交审核';
                } else if (value == 1) {
                    return '审核通过';
                } else if (value == 2) {
                    return '审核不通过';
                } else if (value == 3) {
                    return '提现成功';
                } else if (value == 4) {
                    return '提现失败';
                }
                return value;
            }
            },
            {label: '备注', name: 'comment', index: 'comment', width: 80},
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
        withdrawOrder: {},
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
            vm.withdrawOrder = {};
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
            var url = vm.withdrawOrder.id == null ? "../withdraworder/save" : "../withdraworder/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.withdrawOrder),
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
                    url: "../withdraworder/delete",
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
                url: "../withdraworder/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.withdrawOrder = r.withdrawOrder;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function () {
            vm.q = {
                name: ''
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