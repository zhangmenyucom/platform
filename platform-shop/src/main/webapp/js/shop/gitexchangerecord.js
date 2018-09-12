$(function () {
    $("#jqGrid").Grid({
        url: '../gitexchangerecord/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '用户id', name: 'userId', index: 'user_id', width: 80},
			{label: '礼品名称', name: 'giftName', index: 'giftName', width: 80},
			{label: '使用积分', name: 'usePoint', index: 'use_point', width: 80},
			{label: '订单id', name: 'orderId', index: 'order_id', width: 80},
			{label: '兑换时间', name: 'createTime', index: 'create_time', width: 80},
			{label: '更新时间', name: 'updateTime', index: 'update_time', width: 80,hidden:true}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		gitExchangeRecord: {},
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
			vm.gitExchangeRecord = {};
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
            let url = vm.gitExchangeRecord.id == null ? "../gitexchangerecord/save" : "../gitexchangerecord/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.gitExchangeRecord),
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
				    url: "../gitexchangerecord/delete",
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
                url: "../gitexchangerecord/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.gitExchangeRecord = r.gitExchangeRecord;
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