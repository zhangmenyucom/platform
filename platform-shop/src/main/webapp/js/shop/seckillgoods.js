$(function () {
    $("#jqGrid").Grid({
        url: '../seckillgoods/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '商品名称', name: 'goodsName', index: 'goods_name', width: 80},
            {label: '商品图', name: 'goodsPic', index: 'goods_pic', width: 80},
            {label: '库存', name: 'stock', index: 'stock', width: 80},
            {label: '已售', name: 'sold', index: 'sold', width: 80},
            {label: '原价', name: 'marketPrice', index: 'market_price', width: 80},
            {label: '秒杀价', name: 'curPrice', index: 'cur_price', width: 80},
            {
                label: '开始日期', name: 'startTime', index: 'start_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
            {
                label: '截止日期', name: 'endTime', index: 'end_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
            {label: '描述', name: 'description', index: 'description', width: 80}]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        seckillGoods: {},
        ruleValidate: {
            name: []
        },
        q: {
            name: ''
        },
        goodss: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.seckillGoods = {};
            vm.getGoodss();
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
            var url = vm.seckillGoods.id == null ? "../seckillgoods/save" : "../seckillgoods/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.seckillGoods),
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
                    url: "../seckillgoods/delete",
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
                url: "../seckillgoods/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.seckillGoods = r.seckillGoods;
                }
            });
        },
        getGoodss: function () {
            Ajax.request({
                url: "../goods/queryAll/",
                async: true,
                successCallback: function (r) {
                    vm.goodss = r.list;
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