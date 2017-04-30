var EditableTable = function () {

    return {

        //main function to initiate the module
        init: function () {
            /*        function restoreRow(oTable, nRow) {
             var aData = oTable.fnGetData(nRow);
             var jqTds = $('>td', nRow);

             for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
             oTable.fnUpdate(aData[i], nRow, i, false);
             }

             oTable.fnDraw();
             }

             function editRow(oTable, nRow) {
             var aData = oTable.fnGetData(nRow);
             var jqTds = $('>td', nRow);
             jqTds[0].innerHTML = '<input type="text" class="form-control small" value="' + aData[0] + '">';
             jqTds[1].innerHTML = '<input type="text" class="form-control small" value="' + aData[1] + '">';
             jqTds[2].innerHTML = '<input type="text" class="form-control small" value="' + aData[2] + '">';
             jqTds[3].innerHTML = '<input type="text" class="form-control small" value="' + aData[3] + '">';
             jqTds[4].innerHTML = '<a class="edit" href="">Save</a>';
             jqTds[5].innerHTML = '<a class="cancel" href="">Cancel</a>';
             }

             function saveRow(oTable, nRow) {
             var jqInputs = $('input', nRow);
             oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
             oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
             oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
             oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
             oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 4, false);
             oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow, 5, false);
             oTable.fnDraw();
             }

             function cancelEditRow(oTable, nRow) {
             var jqInputs = $('input', nRow);
             oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
             oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
             oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
             oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
             oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 4, false);
             oTable.fnDraw();
             }*/

            var oTable = $('#editable-sample').dataTable({
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row'<'col-lg-6'l><'col-lg-6'f>r>t<'row'<'col-lg-6'i><'col-lg-6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sProcessing": "正在获取数据，请稍后...",
                    "sLengthMenu": "_MENU_每页记录",
                    "sZeroRecords": "没有您要搜索的内容",
                    "sInfo": "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
                    "sInfoEmpty": "记录数为0",
                    "sInfoFiltered": "(全部记录数 _MAX_ 条)",
                    "sInfoPostFix": "",
                    "sSearch": "搜索",
                    "sUrl": "",
                    "oPaginate": {
                        "sPrevious": "上一页",
                        "sNext": "下一页"
                    }
                },
                "aoColumns": [
                    {
                        "mDataProp": "USERID",
                        "sDefaultContent": "", //此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
                        "bVisible": false //此列不显示
                    }, {
                        "mDataProp": "USERNAME",
                        "sTitle": "用户名",
                        "sDefaultContent": "",
                        "sClass": "center"
                    }, {
                        "mDataProp": "EMAIL",
                        "sTitle": "电子邮箱",
                        "sDefaultContent": "",
                        "sClass": "center"
                    }, {
                        "mDataProp": "MOBILE",
                        "sTitle": "手机",
                        "sDefaultContent": "",
                        "sClass": "center"
                    }, {
                        "mDataProp": "PHONE",
                        "sTitle": "座机",
                        "sDefaultContent": "",
                        "sClass": "center"
                    }, {
                        "mDataProp": "NAME",
                        "sTitle": "姓名",
                        "sDefaultContent": "",
                        "sClass": "center"
                    }, {
                        "mDataProp": "ISADMIN",
                        "sTitle": "用户权限",
                        "sDefaultContent": "",
                        "sClass": "center"
                    }]
            });
            /*
             jQuery('#editable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search input
             jQuery('#editable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify table per page dropdown*/

            /*            var nEditing = null;

             $('#editable-sample_new').click(function (e) {
             e.preventDefault();
             var aiNew = oTable.fnAddData(['', '', '', '',
             '<a class="edit" href="">Edit</a>', '<a class="cancel" data-mode="new" href="">Cancel</a>'
             ]);
             var nRow = oTable.fnGetNodes(aiNew[0]);
             editRow(oTable, nRow);
             nEditing = nRow;
             });

             $('#editable-sample a.delete').live('click', function (e) {
             e.preventDefault();

             if (confirm("Are you sure to delete this row ?") == false) {
             return;
             }

             var nRow = $(this).parents('tr')[0];
             oTable.fnDeleteRow(nRow);
             alert("Deleted! Do not forget to do some ajax to sync with backend :)");
             });

             $('#editable-sample a.cancel').live('click', function (e) {
             e.preventDefault();
             if ($(this).attr("data-mode") == "new") {
             var nRow = $(this).parents('tr')[0];
             oTable.fnDeleteRow(nRow);
             } else {
             restoreRow(oTable, nEditing);
             nEditing = null;
             }
             });

             $('#editable-sample a.edit').live('click', function (e) {
             e.preventDefault();

             /!* Get the row as a parent of the link that was clicked on *!/
             var nRow = $(this).parents('tr')[0];

             if (nEditing !== null && nEditing != nRow) {
             /!* Currently editing - but not this row - restore the old before continuing to edit mode *!/
             restoreRow(oTable, nEditing);
             editRow(oTable, nRow);
             nEditing = nRow;
             } else if (nEditing == nRow && this.innerHTML == "Save") {
             /!* Editing this row and want to save it *!/
             saveRow(oTable, nEditing);
             nEditing = null;
             alert("Updated! Do not forget to do some ajax to sync with backend :)");
             } else {
             /!* No edit in progress - let's start one *!/
             editRow(oTable, nRow);
             nEditing = nRow;
             }
             });*/
        }

    };

}();