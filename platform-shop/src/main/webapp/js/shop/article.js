$(function () {
    $("#jqGrid").Grid({
        url: '../article/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '标题', name: 'title', index: 'title', width: 80},
            {label: '作者', name: 'author', index: 'author', width: 80},
            {
                label: '头图', name: 'bannerPic', index: 'banner_pic', width: 80, formatter: function (value) {
                return transImg(value);
            }
            },
            {label: '内容', name: 'content', index: 'content', width: 80, hidden: true},
            {label: '出处', name: 'sourceUrl', index: 'source_url', width: 80},
            {label: '顺序', name: 'sortOrder', index: 'sort_order', width: 80},
            {
                label: '是否上架', name: 'status', index: 'status', width: 80, formatter: function (value) {
                return transIsNot(value);
            }
            }]
    });
    $('#content').editable({
        inlineMode: false,
        alwaysBlank: true,
        height: 'auto', //高度
        language: "zh_cn",
        minHeight: '200px',
        spellcheck: false,
        plainPaste: true,
        enableScript: false,
        imageButtons: ["floatImageLeft", "floatImageNone", "floatImageRight", "linkImage", "replaceImage", "removeImage"],
        allowedImageTypes: ["jpeg", "jpg", "png", "gif"],
        imageUploadURL: '../sys/oss/upload',//上传到本地服务器
        imageUploadParams: {id: "edit"},
        imagesLoadURL: '../sys/oss/queryAll'//管理图片
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        article: {
            title:"",
            author:"",
            bannerPic:"",
            content:"",
            sourceUrl:""
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
            vm.article = {
                title:"",
                author:"",
                bannerPic:"",
                content:"",
                sourceUrl:""
            };
            $('#content').editable('setHTML', '');
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
            //编辑器内容
            vm.article.content = $('#content').editable('getHTML');
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.article),
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
                    url: "../article/delete",
                    params: JSON.stringify(ids),
                    type: "POST",
                    contentType: "application/json;charset=UTF-8",
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
                url: "../article/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.article = r.article;
                    $('#content').editable('setHTML', vm.article.content);
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
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        handleSuccessPicUrl: function (res, file) {
            vm.article.bannerPic = file.response.url;
        },
        eyeImagePicUrl: function () {
            var url = vm.article.bannerPic;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        }
    }
});