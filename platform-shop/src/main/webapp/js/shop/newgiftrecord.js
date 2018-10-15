$(function () {
    $("#jqGrid").Grid({
        url: '../newgiftrecord/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '会员', name: 'nickName', index: 'nick_name', width: 80},
            {
                label: '头像', name: 'avatar', index: 'avatar', width: 80, formatter: function (value) {
                return transImg(value);
            }
            },
            {
                label: '红包', name: 'hongBao', index: 'hong_bao', width: 80, formatter: function (value) {
                return value + "元";
            }
            },
            {
                label: '状态', name: 'status', index: 'status', width: 80, formatter: function (value) {
                if (value == 1) {
                    return '成功';
                } else if (value == 0) {
                    return '失败';
                }
                return '-';
            }
            },
            {
                label: '领取时间', name: 'createTime', index: 'create_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
            {
                label: '备注', name: 'remark', index: 'remark', width: 80
            }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        newGiftRecord: {},
        ruleValidate: {},
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
            vm.newGiftRecord = {};
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
            var url = vm.newGiftRecord.id == null ? "../newgiftrecord/save" : "../newgiftrecord/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.newGiftRecord),
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
                    url: "../newgiftrecord/delete",
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
                url: "../newgiftrecord/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.newGiftRecord = r.newGiftRecord;
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