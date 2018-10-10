$(function () {
    var userId = getQueryString("userId");
    var url = '../usercoupon/list';
    if (userId) {
        url += '?userId=' + userId;
    }
    $("#jqGrid").Grid({
        url: url,
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '会员', name: 'nickname', index: 'nickname', width: 80},
            {label: '优惠券', name: 'couponName', index: 'coupon_id', width: 80},
            {label: '优惠券序号', name: 'couponNumber', index: 'coupon_number', width: 80},
            {
                label: '状态', name: 'couponStatus', index: 'coupon_status', width: 80, formatter: function (value) {
                if (value == 1) {
                    return '正常';
                } else if (value == 2) {
                    return '已使用';
                } else if (value == 3) {
                    return '过期';
                }
                return '-';
            }
            },
            {
                label: '下发时间', name: 'addTime', index: 'add_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
            {
                label: '使用时间', name: 'usedTime', index: 'used_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
            {label: '订单号', name: 'orderSn', index: 'order_sn', width: 80}]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        userCoupon: {},
        q: {
            userName: '',
            couponName: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.userCoupon = {};
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
            var url = vm.userCoupon.id == null ? "../usercoupon/save" : "../usercoupon/update";

            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.userCoupon),
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
                    type: "POST",
                    url: "../usercoupon/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function (r) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../usercoupon/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.userCoupon = r.userCoupon;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'userName': vm.q.userName, 'couponName': vm.q.couponName},
                page: page
            }).trigger("reloadGrid");
        }
    }
});