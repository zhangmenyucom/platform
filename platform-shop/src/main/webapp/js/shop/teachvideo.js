$(function () {
    $("#jqGrid").Grid({
        url: '../teachvideo/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '名称', name: 'title', index: 'title', width: 80},
            {label: '简介', name: 'brief', index: 'brief', width: 80},
            {
                label: '封面', name: 'wrapper', index: 'wrapper', width: 80, formatter: function (value) {
                return transImg(value);
            }
            },
            {label: '视频地址', name: 'videoUrl', index: 'video_url', width: 80},
            {
                label: '是否上架', name: 'status', index: 'status', width: 80, formatter: function (value) {
                return transIsNot(value);
            }
            }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        teachVideo: {
            videoUrl: "",
            wrapper: ""
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
            vm.teachVideo = {
                videoUrl: "",
                wrapper: ""
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
            var url = vm.teachVideo.id == null ? "../teachvideo/save" : "../teachvideo/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.teachVideo),
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
                    url: "../teachvideo/delete",
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
                url: "../teachvideo/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.teachVideo = r.teachVideo;
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
        handleRemove: function (file) {
            // 从 upload 实例删除数据
            const fileList = this.uploadList;
            this.uploadList.splice(fileList.indexOf(file), 1);
        },
        handleSuccess: function (res, file) {
            this.$Message.destroy();
            this.$Message.success('上传成功');
            // 因为上传过程为实例，这里模拟添加 url
            file.imgUrl = res.url;
            file.name = res.url;
            vm.uploadList.add(file);
        },
        handleBeforeUpload: function () {
            const check = this.uploadList.length < 5;
            if (!check) {
                this.$Notice.warning({
                    title: '最多只能上传 5 张图片。'
                });
            }
            return check;
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
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
                desc: '文件 ' + file.name + ' 太大，不能超过 2G。'
            });
        },
        handleProgress: function () {
            this.$Message.loading({
                content: '上传中....',
                duration: 0
            });
        },
        handleSuccessVideoUrl: function (res, file) {
            this.$Message.destroy();
            this.$Message.success('上传成功');
            vm.teachVideo.videoUrl = file.response.url;
        },
        eyeVideoUrl: function () {
            var url = vm.teachVideo.videoUrl;
            eyeVideo(url);
        },
        eyeVideo: function (e) {
            eyeVideo($(e.target).attr('src'));
        },
        handleSuccessPicUrl: function (res, file) {
            this.$Message.destroy();
            this.$Message.success('上传成功');
            vm.teachVideo.wrapper = file.response.url;
        },
        eyeImagePicUrl: function () {
            var url = vm.teachVideo.wrapper;
            eyeImage(url);

        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        }
    }
});