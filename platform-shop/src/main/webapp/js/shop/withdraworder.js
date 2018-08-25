$(function () {
    $("#jqGrid").Grid({
        url: '../withdraworder/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '流水号', name: 'orderSn', index: 'order_sn', width:80},
			{label: '提现人id', name: 'userId', index: 'user_id', width: 80},
			{label: '提现金额', name: 'withdrawAmount', index: 'withdraw_amount', width: 80},
			{label: '提现账户', name: 'withdrawAccount', index: 'withdraw_account', width: 80},
			{label: '账户类型 0:微信 1：支付宝 3：银行卡', name: 'accountType', index: 'account_type', width: 80},
			{label: '提现状态 0:提交审核 1：审核通过 2：审核不通过 3：提现成功', name: 'status', index: 'status', width: 80},
			{label: '备注', name: 'comment', index: 'comment', width: 80},
			{label: '创建时间', name: 'createTime', index: 'create_time', width: 80},
			{label: '更新时间', name: 'updateTime', index: 'update_time', width: 80}]
    });
});

let vm = new Vue({
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
            let id = getSelectedRow("#jqGrid");
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
            let url = vm.withdrawOrder.id == null ? "../withdraworder/save" : "../withdraworder/update";
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
            let ids = getSelectedRows("#jqGrid");
			if (ids == null){
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
		getInfo: function(id){
            Ajax.request({
                url: "../withdraworder/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.withdrawOrder = r.withdrawOrder;
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
        reloadSearch: function() {
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