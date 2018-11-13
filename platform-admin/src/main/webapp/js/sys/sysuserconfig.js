$(function () {
    $("#jqGrid").Grid({
        url: '../sys/userconfig/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '商户id', name: 'merchantId', index: 'merchant_id', width: 40},
            {label: '小程序id', name: 'appId', index: 'app_id', width: 80},
            {label: '密钥', name: 'secret', index: 'secret', width: 80},
            {label: '支付商户号', name: 'mchId', index: 'mch_id', width: 80},
            {label: '支付密钥', name: 'paySignKey', index: 'pay_sign_key', width: 80},
            {label: '证书地址', name: 'certAddress', index: 'cert_address', width: 80},
            {label: '门店名称', name: 'storeName', index: 'store_name', width: 80},
            {label: '门店地址', name: 'storeAddress', index: 'store_address', width: 120},
            {label: '门店头图', name: 'storeBanner', index: 'store_banner', width: 120},
            {label: '营业执照', name: 'businessLicence', index: 'business_licence', width: 120},
            {label: '法人身份证', name: 'idCard', index: 'id_card', width: 120},
            {label: '是否加入质保', name: 'isQualityGuarantee', index: 'is_quality_guarantee', width: 120},
            {label: '电话', name: 'phone', index: 'cert_address', width: 80},
            {
                label: '', name: 'createTime', index: 'create_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            }
        ]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        sysUserConfig: {
            certAddress:"",
            storeAddress:"",
            storeName:"",
            phone:""
        },
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
            vm.sysUserConfig = {
                certAddress:"",
                storeAddress:"",
                storeName:"",
                phone:""
            };
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
            var url = vm.sysUserConfig.id == null ? "../sys/userconfig/save" : "../sys/userconfig/update";
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
                    url: "../sys/userconfig/delete",
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
                url: "../sys/userconfig/info/" + id,
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
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传p12格式的证书。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        handleSuccessPicUrl: function (res, file) {
            this.$Message.success('上传成功');
            vm.sysUserConfig.certAddress = file.response.url;
        }
    }
});