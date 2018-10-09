$(function () {
    $("#jqGrid").Grid({
        url: '../activity/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '活动标题', name: 'title', index: 'title', width: 80},
            {label: '作者', name: 'author', index: 'author', width: 80},
            {label: '活动内容', name: 'content', index: 'content', width: 80},
            {
                label: '图标地址', name: 'banner', index: 'banner', width: 80, formatter: function (value) {
                return transImg(value);
            }
            },
            {
                label: '开始时间', name: 'startDate', index: 'startDate', width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
            {
                label: '截至日期', name: 'endDate', index: 'endDate', width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
            {label: '活动地址', name: 'position', index: 'position', width: 80}]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        activity: {
            title: "",
            author: "",
            content: "",
            banner: "",
            position: ""
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
            vm.activity = {
                title: "",
                author: "",
                content: "",
                banner: "",
                position: ""
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
            var url = vm.activity.id == null ? "../activity/save" : "../activity/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.activity),
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
                    url: "../activity/delete",
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
                url: "../activity/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.activity = r.activity;
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
        handleView: function (name) {
            this.imgName = name;
            this.visible = true;
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        handleProgress: function () {
            this.$Message.loading({
                content: '上传中....',
                duration: 0
            });
        },
        handleSuccessPicUrl: function (res, file) {
            this.$Message.destroy();
            this.$Message.success('上传成功');
            vm.activity.banner = file.response.url;
        },
        eyeImagePicUrl: function () {
            var url = vm.activity.banner;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        }
    }
});