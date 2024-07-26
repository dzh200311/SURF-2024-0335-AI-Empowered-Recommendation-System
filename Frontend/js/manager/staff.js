
function showPopup() {
    $('#popup').removeClass('hidden');
}
// Function to hide the popup
function closePopup() {
    $('#popup').addClass('hidden');
}
function showDeletePopup() {
    $('#deletePopup').removeClass('hidden');
}
// Function to hide the delete popup
function closeDeletePopup() {
    $('#deletePopup').addClass('hidden');
}
// Add event listener to the 'Add Employee' button
$('#addEmployeeBtn').click(showPopup);

// Add event listener to the 'Delete Employee' button
$('#deleteEmployeeBtn').click(showDeletePopup);


$("#addButton").click(function (event) {
    var staff_name = document.getElementById("staff_name").value;
    var position = document.getElementById("position").value;
    var shift = document.getElementById("shift").value;
    var salary = document.getElementById("salary").value;
    var store_id = document.getElementById("store_id").value;
    var role = document.getElementById("role").value;

    console.log(staff_name, position, shift, salary, store_id, role)

    var token1 = localStorage.getItem('tempToken')
    $.ajax({

        type: 'POST',
        url: "http://120.26.136.194:8000/api/staff/add_staff",
        data: { staff_name: staff_name, position: position, shift: shift,  salary: salary, store_id: store_id, role: role},
        headers: {
            "Authorization": "Bearer " + token1,
        },
        success: function(response) {
            console.log(response);
        },
        error: function(error) {
            alert("error calling");
            console.log("unknown error:", error);
        }
    });
});
$(document).ready(function() {
    var currentPage = 1;
    var currentRole = $('#roleSelect').val();

    function fetchStaffData(page, role) {
        var token1 = localStorage.getItem('tempToken');

        $.ajax({
            url: 'http://localhost:8000/api/staff/get_all_staff',
            type: 'GET',
            data: { page: page, role: role },
            headers: {
                "Authorization": "Bearer " + token1
            },
            success: function(data) {
                console.log(data); // 输出员工数据到控制台
                var tbody = $('#staffTable tbody');
                tbody.empty(); // 清空当前的表格内容

                data.forEach(function(staff) {
                    var tr = $('<tr>'); // 创建一个新的行元素
// 按顺序创建单元格并填充数据
                    tr.append($('<td>').text(staff.address));
                    tr.append($('<td>').text(staff.date_birth));
                    tr.append($('<td>').text(staff.email));

                    // 将 employment_status 转换为百分比，并创建进度条
                    var employmentStatusPercentage = parseFloat(staff.employment_status) * 100;
                    var progressTd = $('<td>');
                    var progressBarContainer = $('<div>').addClass('progress-container');
                    var progressBar = $('<div>').addClass('progress-bar').width(employmentStatusPercentage + '%').text(employmentStatusPercentage.toFixed(0) + '%');
                    progressBarContainer.append(progressBar);
                    progressTd.append(progressBarContainer);
                    tr.append(progressTd);

                    // 继续添加其他单元格
                    tr.append($('<td>').text(staff.enable));
                    tr.append($('<td>').text(staff.name));
                    tr.append($('<td>').text(staff.phone));
                    tr.append($('<td>').text(staff.position));
                    tr.append($('<td>').text(staff.role));
                    tr.append($('<td>').text(staff.salary));
                    tr.append($('<td>').text(staff.shift));
                    tr.append($('<td>').text(staff.staff_ID));
                    tr.append($('<td>').text(staff.store_id));

                    tbody.append(tr); // 将填充好数据的行添加到表格中
                });
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
            }
        });
    }

    $('#roleSelect').change(function() {
        currentRole = $(this).val();
        console.log("Current page updated to:", currentRole); // 添加这行来检查页码是否更新
        fetchStaffData(currentPage, currentRole);
    });

    // 新增监听分页选择的变化
    $('#pageSelect').change(function() {
        currentPage = $(this).val(); // 更新当前页码
        console.log("Current page updated to:", currentPage); // 添加这行来检查页码是否更新
        fetchStaffData(currentPage, currentRole); // 根据新的页码重新获取并显示员工数据
    });

    // 页面加载时加载第一页数据
    fetchStaffData(currentPage, currentRole);
});

// $(document).ready(function() {
//     // 初始化页面时默认的页码和职责
//     var currentPage = 1;
//     var currentRole = $('#roleSelect').val(); // 获取下拉选择框中当前选定的职位值
//
//     // 发送 AJAX 请求获取员工数据的函数
//     function fetchStaffData(page, role) {
//         var token1 = localStorage.getItem('tempToken');
//
//         $.ajax({
//             url: 'http://localhost:8000/api/staff/get_all_staff',
//             type: 'GET',
//             data: { page: page, role: role },
//             headers: {
//                 "Authorization": "Bearer " + token1
//             },
//             success: function(data) {
//                 console.log(data); // 输出员工数据到控制台
//                 var tbody = $('#staffTable tbody');
//                 tbody.empty(); // 清空当前的表格内容
//
//                 // 循环遍历每个员工的数据
//                 data.forEach(function(staff) {
//                     var tr = $('<tr>'); // 创建一个新的行元素
// // 按顺序创建单元格并填充数据
//                     tr.append($('<td>').text(staff.address));
//                     tr.append($('<td>').text(staff.date_birth));
//                     tr.append($('<td>').text(staff.email));
//
//                     // 将 employment_status 转换为百分比，并创建进度条
//                     var employmentStatusPercentage = parseFloat(staff.employment_status) * 100;
//                     var progressTd = $('<td>');
//                     var progressBarContainer = $('<div>').addClass('progress-container');
//                     var progressBar = $('<div>').addClass('progress-bar').width(employmentStatusPercentage + '%').text(employmentStatusPercentage.toFixed(0) + '%');
//                     progressBarContainer.append(progressBar);
//                     progressTd.append(progressBarContainer);
//                     tr.append(progressTd);
//
//                     // 继续添加其他单元格
//                     tr.append($('<td>').text(staff.enable));
//                     tr.append($('<td>').text(staff.name));
//                     tr.append($('<td>').text(staff.phone));
//                     tr.append($('<td>').text(staff.position));
//                     tr.append($('<td>').text(staff.role));
//                     tr.append($('<td>').text(staff.salary));
//                     tr.append($('<td>').text(staff.shift));
//                     tr.append($('<td>').text(staff.staff_ID));
//                     tr.append($('<td>').text(staff.store_id));
//
//                     tbody.append(tr); // 将填充好数据的行添加到表格中
//                 });
//             },
//             error: function(xhr, status, error) {
//                 console.error('Error:', error); // Log any errors to the console
//             }
//         });
//     }
//
//
//
//     // 监听下拉选择框的变化
//     $('#roleSelect').change(function() {
//         // 更新当前职责
//         currentRole = $(this).val();
//         // 重新获取并显示员工数据
//         fetchStaffData(currentPage, currentRole);
//     });
//
//     // 新增监听分页选择的变化
//     $('#pageSelect').change(function() {
//         currentPage = $(this).val(); // 更新当前页码
//         fetchStaffData(currentPage, currentRole); // 根据新的页码重新获取并显示员工数据
//     });
//
//
// // 初始化页面时加载第一页和第一个职责的员工数据
// fetchStaffData(currentPage, currentRole);
//
// });