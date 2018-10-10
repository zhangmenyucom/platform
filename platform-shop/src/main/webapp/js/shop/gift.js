$(function () {
    $("#jqGrid").Grid({
        url: '../gift/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '礼品名称', name: 'name', index: 'name', width: 80},
            {
                label: '礼品类型', name: 'type', index: 'type', width: 80, formatter: function (value) {
                if (value == 1) {
                    return '线下礼品';
                } else if (value == 2) {
                    return '线上礼品';
                } else {
                    return '-';
                }
                return value;
            }
            },
            {
                label: '礼品图片', name: 'picUrl', index: 'pic_url', width: 80, formatter: function (value) {
                return transImg(value);
            }
            },
            {label: '所需积分', name: 'pointValue', index: 'point_value', width: 40},
            {
                label: '上下架', name: 'status', index: 'status', width: 40, formatter: function (value) {
                if (value == 0) {
                    return '下架';
                } else if (value == 1) {
                    return '上架';
                } else {
                    return '-';
                }
                return value;
            }
            },
            {label: '说明', name: 'giftDesc', index: 'gift_desc', width: 120},
            {label: '排序', name: 'sortOrder', index: 'sort_order', width: 40},
            {label: '线上礼品id', name: 'goodsId', index: 'goods_id', width: 80},
            {
                label: '创建时间', name: 'createTime', index: 'create_time', width: 100, formatter: function (value) {
                return transDate(value);
            }
            },
            {label: '更新时间', name: 'updateTime', index: 'update_time', width: 80, hidden: true}]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        gift: {
            picUrl:""
        },
        ruleValidate: {
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
            vm.getGoodss();
            vm.gift = {
                picUrl:"",
                type:1,
                status:1
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
            var url = vm.gift.id == null ? "../gift/save" : "../gift/update";
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
            var ids = getSelectedRows("#jqGrid");
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
        getGoodss: function () {
            Ajax.request({
                url: "../goods/queryAll/",
                async: true,
                successCallback: function (r) {
                    vm.goodss = r.list;
                }
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
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
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
            vm.gift.picUrl = file.response.url;
        },
        eyeImagePicUrl: function () {
            var url = vm.gift.picUrl;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        }
    }
});