$(function () {
    $("#jqGrid").Grid({
        url: '../smslog/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '发送人', name: 'nickName', index: 'nickName', width: 80},
            {label: '手机', name: 'phone', index: 'phone', width: 80},
            {
                label: '发送时间', name: 'logDate', index: 'log_date', width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
            {label: '验证码', name: 'smsCode', index: 'sms_code', width: 80},
            {
                label: '发送状态', name: 'sendStatus', index: 'send_status', width: 80, formatter: function (value) {
                if (value == 1) {
                    return '成功';
                } else if (value == 0) {
                    return '失败';
                }
                return '-';
            }
            },
            {label: '内容', name: 'smsText', index: 'sms_text', width: 80}]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        smsLog: {},
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
            vm.smsLog = {};
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
            var url = vm.smsLog.id == null ? "../smslog/save" : "../smslog/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.smsLog),
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
                    url: "../smslog/delete",
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
                url: "../smslog/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.smsLog = r.smsLog;
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