$(function () {
    $("#jqGrid").Grid({
        url: '../sys/userconfig/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '商户id', name: 'merchantId', index: 'merchant_id', width: 80},
            {label: '小程序id', name: 'appId', index: 'app_id', width: 80},
            {label: '密钥', name: 'secret', index: 'secret', width: 80},
            {label: '支付商户号', name: 'mchId', index: 'mch_id', width: 80},
            {label: '支付密钥', name: 'paySignKey', index: 'pay_sign_key', width: 80},
            {label: '证书地址', name: 'certAddress', index: 'cert_address', width: 80},
            {label: '门店名称', name: 'storeName', index: 'store_name', width: 80},
            {label: '门店地址', name: 'storeAddress', index: 'store_address', width: 120},
            {label: '电话', name: 'phone', index: 'cert_address', width: 80},
            {
                label: '', name: 'createTime', index: 'create_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            }
        ]
    });
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        sysUserConfig: {},
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
            vm.sysUserConfig = {};
        },
        update: function (event) {
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.sysUserConfig.id == null ? "../sysuserconfig/save" : "../sysuserconfig/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.sysUserConfig),
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
                    url: "../sysuserconfig/delete",
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
                url: "../sysuserconfig/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.sysUserConfig = r.sysUserConfig;
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