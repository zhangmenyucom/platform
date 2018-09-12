$(function () {
    $("#jqGrid").Grid({
        url: '../gift/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '礼品类型 1：线下商品 2：线上商品', name: 'type', index: 'type', width: 80},
            {label: '礼品图片', name: 'picUrl', index: 'pic_url', width: 80},
            {label: '兑换所需要积分', name: 'pointValue', index: 'point_value', width: 80},
            {label: '0:下架 1：上架', name: 'status', index: 'status', width: 80},
            {label: '排序', name: 'sortOrder', index: 'sort_order', width: 80},
            {label: '线上商品id', name: 'goodsId', index: 'goods_id', width: 80},
            {label: '创建时间', name: 'createTime', index: 'create_time', width: 80},
            {label: '更新时间', name: 'updateTime', index: 'update_time', width: 80, hidden: true}]
    });
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        gift: {},
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
            vm.gift = {};
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
            let url = vm.gift.id == null ? "../gift/save" : "../gift/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.gift),
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
            let ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../gift/delete",
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
                url: "../gift/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.gift = r.gift;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
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