$(function () {
    $("#jqGrid").Grid({
        url: '../giftexchangerecord/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '用户id', name: 'userId', index: 'user_id', width: 80, hidden: true},
            {label: '用户', name: 'nickName', index: 'nickName', width: 80},
            {label: '礼品名称', name: 'giftName', index: 'giftName', width: 80},
            {
                label: '礼品图', name: 'giftPic', index: 'giftPic', width: 80, formatter: function (value) {
                return transImg(value);
            }
            },
            {label: '兑换数量', name: 'number', index: 'number', width: 80},
            {label: '使用积分', name: 'usePoint', index: 'use_point', width: 80},
            {label: '订单号', name: 'orderSn', index: 'orderSn', width: 80},
            {
                label: '兑换时间', name: 'createTime', index: 'create_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
            {label: '更新时间', name: 'updateTime', index: 'update_time', width: 80, hidden: true}]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        giftExchangeRecord: {},
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
            vm.giftExchangeRecord = {};
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
            var url = vm.giftExchangeRecord.id == null ? "../giftexchangerecord/save" : "../giftexchangerecord/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.giftExchangeRecord),
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
                    url: "../giftexchangerecord/delete",
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
                url: "../giftexchangerecord/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.giftExchangeRecord = r.giftExchangeRecord;
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