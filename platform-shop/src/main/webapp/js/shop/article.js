$(function () {
    $("#jqGrid").Grid({
        url: '../article/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '标题', name: 'title', index: 'title', width: 80},
			{label: '作者', name: 'author', index: 'author', width: 80},
			{label: '内容', name: 'content', index: 'content', width: 80},
			{label: '出处', name: 'sourceUrl', index: 'source_url', width: 80},
			{label: '顺序', name: 'sortOrder', index: 'sort_order', width: 80},
			{label: '是否上架', name: 'status', index: 'status', width: 80,formatter: function (value) {
                return transIsNot(value);
            }}]
    });
    $('#article_content').editable({
        inlineMode: false,
        alwaysBlank: true,
        height: '500px', //高度
        minHeight: '200px',
        language: "zh_cn",
        spellcheck: false,
        plainPaste: true,
        enableScript: false,
        imageButtons: ["floatImageLeft", "floatImageNone", "floatImageRight", "linkImage", "replaceImage", "removeImage"],
        allowedImageTypes: ["jpeg", "jpg", "png", "gif"],
        imageUploadURL: '../sys/oss/upload',
        imageUploadParams: {id: "edit"},
        imagesLoadURL: '../sys/oss/queryAll'
    })
});

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		article: {},
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
            $('#article_content').editable('setHTML', '');
			vm.showList = false;
			vm.title = "新增";
			vm.article = {};
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
            var url = vm.article.id == null ? "../article/save" : "../article/update";
            vm.article.content = $('#article_content').editable('getHTML');
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.article),
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
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
				    url: "../article/delete",
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
                url: "../article/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.article = r.article;
                    $('#article_content').editable('setHTML', vm.article.content);
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